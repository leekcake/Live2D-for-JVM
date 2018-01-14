package moe.leekcake_live2dforjvm.sample

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.Live2DCubismGLRenderingJNI
import moe.leekcake.live2dforjvm.type.*
import java.io.InputStream

abstract class SampleGLApp {
    abstract val width: Float
    abstract val height: Float
    abstract fun generateTexture(fileName: String): Int
    abstract fun destroyTexture(id: Int)
    abstract fun openFile(fileName: String): InputStream

    private lateinit var moc: CubismMoc
    private lateinit var model: CubismModel
    private lateinit var table: CubismModelHashTable

    private lateinit var physics: CubismPhysics
    private lateinit var physicsOption: CubismPhysicsOptions

    private lateinit var animation: CubismAnimation
    private lateinit var animationState: CubismAnimationState

    private lateinit var renderer: CubismGLRenderer

    private var texture: Int = 0

    private lateinit var vp: FloatArray

    private var lastTime: Long = 0

    fun init() {
        Live2DCubismGLRenderingJNI.ensureGLAD()

        val stream = openFile("hiyori.moc3")
        moc = CubismMoc(stream, stream.available())
        stream.close()

        model = CubismModel(moc)
        table = CubismModelHashTable(model)

        val physicsJsonStream = openFile("hiyori.physics3.json")
        val physicsJson = String(physicsJsonStream.readBytes(physicsJsonStream.available()))
        physicsJsonStream.close()

        physics = CubismPhysics(physicsJson)
        physicsOption = CubismPhysicsOptions()
        physicsOption.gravity.x = 0f
        physicsOption.gravity.y = -1f
        physicsOption.wind.x = 2f
        physicsOption.wind.y = 2f

        val aniJsonStream = openFile("hiyori_m01.motion3.json")
        val motionJson = String(aniJsonStream.readBytes(aniJsonStream.available()))
        aniJsonStream.close()

        animation = CubismAnimation(motionJson)
        animationState = CubismAnimationState()

        renderer = CubismGLRenderer(model)

        texture = generateTexture("hiyori_texture.png")

        vp = generateViewProjection(0.8f)
        lastTime = System.currentTimeMillis()
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

    fun tick() {
        val delta = (System.currentTimeMillis() - lastTime).toFloat() / 1000f
        lastTime = System.currentTimeMillis();

        physics.evaluate(model, physicsOption, delta)

        animationState.update(delta)
        animation.evaluate(animationState, Live2DCubismFrameworkJNI.getOverrideFloatBlendFunction(),
                1f, model, table, CubismModel.AnimationCurveType.OpacityAnimationCurve, 0)

        model.update()
        renderer.update()

        model.resetDrawableDynamicFlags()

        renderer.draw(vp, texture.toLong())
    }

    fun destroy() {
        destroyTexture(texture)

        renderer.release()
        animation.release()
        table.release()
        model.release()
        moc.release()
    }
}