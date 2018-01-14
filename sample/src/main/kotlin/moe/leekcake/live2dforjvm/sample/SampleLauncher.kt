package moe.leekcake.live2dforjvm.sample

import moe.leekcake.live2dforjvm.Live2DCubismCoreJNI
import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.Live2DCubismGLRenderingJNI
import moe.leekcake.live2dforjvm.type.*
import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import java.io.File

//Quick copy start from https://www.lwjgl.org/guide
class Sample {
    private val windowWidth = 405f
    private val windowHeight = 720f

    private lateinit var moc: CubismMoc
    private lateinit var model: CubismModel
    private lateinit var table: CubismModelHashTable

    private lateinit var animation: CubismAnimation
    private lateinit var animationState: CubismAnimationState

    private lateinit var renderer: CubismGLRenderer

    private lateinit var texture: Texture

    private lateinit var vp: FloatArray

    //For better view, LWJGL Flow is not important for sample
    private fun initCubism() {
        //Load Cubism Example Model
        val sampleFolder = File("../sample-hiyori")

        moc = CubismMoc(File(sampleFolder, "hiyori.moc3"))
        model = CubismModel(moc)
        table = CubismModelHashTable(model)
        val motionJson = File(sampleFolder, "hiyori_m01.motion3.json").readText()
        animation = CubismAnimation(motionJson)
        animationState = CubismAnimationState()

        Live2DCubismGLRenderingJNI.ensureGLAD()
        renderer = CubismGLRenderer(model)
        texture = Texture.loadTexture(File(sampleFolder, "hiyori_texture.png").path)
        vp = generateViewProjection(0.8f)

        lastTime = System.currentTimeMillis()
    }

    private var lastTime: Long = 0

    private fun loopCubism() {
        val delta = (System.currentTimeMillis() - lastTime).toFloat() / 1000f
        lastTime = System.currentTimeMillis();

        animationState.update(delta)
        animation.evaluate(animationState, Live2DCubismFrameworkJNI.getOverrideFloatBlendFunction(),
                1f, model, table, CubismModel.AnimationCurveType.OpacityAnimationCurve, 0)

        model.update()
        renderer.update()

        model.resetDrawableDynamicFlags()

        renderer.draw(vp, texture.id.toLong())
    }

    private fun destroyCubism() {
        texture.delete()

        renderer.release()
        animation.release()
        table.release()
        model.release()
        moc.release()
    }

    private fun generateViewProjection(safeFrame: Float): FloatArray {
        var aspect: Float
        val xScale: Float
        val yScale: Float

        // Compute scale.
        aspect = windowWidth / windowHeight

        if (aspect > 1.0f) {
            yScale = 1.0f / safeFrame * 2.0f
            xScale = yScale * (1.0f / aspect)
        } else {
            aspect = windowHeight / windowWidth

            xScale = 1.0f / safeFrame * 2.0f
            yScale = xScale * (1.0f / aspect)
        }

        val vp = FloatArray(16)
        // Set matrix.
        vp[0] = xScale; vp[1] = 0.0f; vp[2] = 0.0f; vp[3] = 0.0f
        vp[4] = 0.0f; vp[5] = yScale; vp[6] = 0.0f; vp[7] = 0.0f
        vp[8] = 0.0f; vp[9] = 0.0f; vp[10] = 1.0f; vp[11] = 0.0f
        vp[12] = 0.0f; vp[13] = 0.0f; vp[14] = 0.0f; vp[15] = 1.0f

        return vp
    }

    // The window handle
    private var window: Long = 0

    fun run() {
        println("Hello LWJGL " + Version.getVersion() + "!")
        println("Hello Cubism ${Live2DCubismCoreJNI.getVersion()}!")

        init()
        loop()
        destroyCubism()

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        // Terminate GLFW and free the error callback
        glfwTerminate()
        glfwSetErrorCallback(null).free()
    }

    private fun init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set()

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw IllegalStateException("Unable to initialize GLFW")

        // Configure GLFW
        glfwDefaultWindowHints() // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)

        // Create the window
        window = glfwCreateWindow(windowWidth.toInt(), windowHeight.toInt(), "Hello Cubism with LWJGL!", NULL, NULL)
        if (window == NULL)
            throw RuntimeException("Failed to create the GLFW window")

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window) { window, key, scancode, action, mods ->
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true) // We will detect this in the rendering loop
        }

        // Get the thread stack and push a new frame
        stackPush().use { stack ->
            val pWidth = stack.mallocInt(1) // int*
            val pHeight = stack.mallocInt(1) // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight)

            // Get the resolution of the primary monitor
            val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            )
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window)
        // Enable v-sync
        glfwSwapInterval(1)

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities()

        System.out.println("OpenGL version " + glGetString(GL_VERSION))

        // Make the window visible
        glfwShowWindow(window)
        initCubism()
    }

    private fun loop() {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT)
            loopCubism()
            glfwSwapBuffers(window) // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents()
        }
    }
}

//Run on (Project Root)/Native/
fun main(args: Array<String>) {
    Sample().run()
}