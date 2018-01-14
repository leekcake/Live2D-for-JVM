package moe.leekcake.hellocubism

import android.app.Activity
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.Live2DCubismGLRenderingJNI
import moe.leekcake.live2dforjvm.type.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = GLSurfaceView(this)
        glView.setEGLContextClientVersion(2)
        glView.setPreserveEGLContextOnPause(true)
        glView.setRenderer(Renderer())

        setContentView(glView)
    }

    override fun onPause() {
        super.onPause()
        glView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glView.onResume()
    }

    private fun generateViewProjection(safeFrame: Float): FloatArray {
        var aspect: Float
        val xScale: Float
        val yScale: Float

        val windowWidth = width
        val windowHeight = height
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

    private lateinit var glView: GLSurfaceView

    private lateinit var moc: CubismMoc
    private lateinit var model: CubismModel
    private lateinit var table: CubismModelHashTable

    private lateinit var animation: CubismAnimation
    private lateinit var animationState: CubismAnimationState

    private lateinit var renderer: CubismGLRenderer

    //private lateinit var texture: Texture
    private var texture: Int = 0

    private lateinit var vp: FloatArray

    private var lastTime: Long = 0

    private var width = 720f
    private var height = 1280f

    private inner class Renderer : GLSurfaceView.Renderer {
        override fun onDrawFrame(p0: GL10?) {
            /*
            GLES20.glEnable(GLES20.GL_BLEND)
            GLES20.glDisable(GLES20.GL_DEPTH_TEST)
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1f); // Black Background
            GLES20.glClearDepthf(1.0f); // Depth Buffer Setup
            GLES20.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA)
            */

            val delta = (System.currentTimeMillis() - lastTime).toFloat() / 1000f
            lastTime = System.currentTimeMillis();

            animationState.update(delta)
            animation.evaluate(animationState, Live2DCubismFrameworkJNI.getOverrideFloatBlendFunction(),
                    1f, model, table, CubismModel.AnimationCurveType.OpacityAnimationCurve, 0)

            model.update()
            renderer.update()

            model.resetDrawableDynamicFlags()

            renderer.draw(vp, texture.toLong())
        }

        override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {

        }

        override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
            width = glView.measuredWidth.toFloat()
            height = glView.measuredHeight.toFloat()

            Live2DCubismGLRenderingJNI.ensureGLAD()

            val stream = applicationContext.assets.open("hiyori.moc3")
            moc = CubismMoc(stream, stream.available())
            stream.close()

            model = CubismModel(moc)
            table = CubismModelHashTable(model)

            val jsonStream = application.assets.open("hiyori_m01.motion3.json")
            val motionJson = String(jsonStream.readBytes(jsonStream.available()))
            jsonStream.close()

            animation = CubismAnimation(motionJson)
            animationState = CubismAnimationState()

            renderer = CubismGLRenderer(model)

            val textureStream = application.assets.open("hiyori_texture.png")
            texture = Utils.loadTexture(textureStream)
            textureStream.close()

            vp = generateViewProjection(0.8f)
            GLES20.glViewport(0, 0, width.toInt(), height.toInt())
            lastTime = System.currentTimeMillis()
        }
    }
}