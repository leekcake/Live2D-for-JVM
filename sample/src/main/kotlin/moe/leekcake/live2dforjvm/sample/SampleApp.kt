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

    fun handleTouchBegin(x: Float, y: Float) {
        onTouch = true
        handleCursor(x, y)
    }

    fun handleTouchEnd(x: Float, y: Float) {
        handleCursor(0f, 0f)
        onTouch = false
    }

    fun handleCursor(x: Float, y: Float) {
        if(!onTouch) return
        char.setDragging(x, y)
        println("$x $y")
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

        projection.loadIdentity()
        projection.scale(1f, windowWidth / windowHeight.toFloat())

        char.update()
        char.draw(projection)
    }

    fun destroy() {

    }
}