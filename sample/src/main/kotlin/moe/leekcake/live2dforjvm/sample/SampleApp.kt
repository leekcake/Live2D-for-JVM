package moe.leekcake.live2dforjvm.sample

import moe.leekcake.live2dforjvm.framework.jni.CubismFrameworkJNI
import moe.leekcake.live2dforjvm.framework.jni.extend.ExtendPALJNI
import moe.leekcake.live2dforjvm.framework.type.extend.CubismCharacter
import moe.leekcake.live2dforjvm.framework.type.math.CubismMatrix44

abstract class SampleApp : ExtendPALJNI.FileProvider, ExtendPALJNI.TextureProvider {
    lateinit var char: CubismCharacter
    val projection = CubismMatrix44()

    abstract val windowWidth: Int
    abstract val windowHeight: Int

    var onTouch = false

    private fun generateViewProjection(safeFrame: Float): FloatArray {
        var aspect: Float
        val xScale: Float
        val yScale: Float

        aspect = windowWidth / windowHeight.toFloat()

        if (aspect > 1.0f) {
            yScale = 1.0f / safeFrame * 2.0f
            xScale = yScale * (1.0f / aspect)
        } else {
            aspect = windowHeight / windowWidth.toFloat()

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

    fun handleTouchBegin(x: Float, y: Float) {
        onTouch = true
        handleCursor(x, y)
    }

    fun handleTouchEnd(x: Float, y: Float) {
        onTouch = false
        char.setDragging(0f , 0f)
        val dx = ((x / windowWidth) - 0.5f) * 2f
        val dy = -((y / windowHeight) - 0.5f) * 2f
        if( char.hitTest("Head", dx , dy) ) {
            println("You just hit cutest hiyori's head !!!")
        }
    }

    fun handleCursor(x: Float, y: Float) {
        if(!onTouch) return
        val dx = ((x / windowWidth) - 0.5f) * 2f
        val dy = -((y / windowHeight) - 0.5f) * 2f
        char.setDragging(dx , dy)
        //println("$dx $dy")
    }

    fun init() {
        ExtendPALJNI.setFileProvider(this)
        ExtendPALJNI.setTextureProvider(this)

        CubismFrameworkJNI.StartUp()
        CubismFrameworkJNI.Initialize()

        char = CubismCharacter()
        char.loadAssets("", "Hiyori.model3.json")

        ExtendPALJNI.updateTime()
    }

    fun update() {
        ExtendPALJNI.updateTime()

        projection.setMatrix( generateViewProjection(1f) )

        if(!char.isInMotion) {
            char.startRandomMotion("Idle", 1)
        }
        char.update()
        char.draw(projection)
    }

    fun destroy() {

    }
}