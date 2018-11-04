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

    fun init() {
        ExtendPALJNI.setFileProvider(this)
        ExtendPALJNI.setTextureProvider(this)

        CubismFrameworkJNI.StartUp()
        CubismFrameworkJNI.Initialize()

        char = CubismCharacter()

        char.loadAssets("", "Hiyori.model3.json")
    }

    fun update() {
        projection.scale(1.0f, (windowWidth) / (windowHeight).toFloat())

        char.update()
        char.draw(projection)
    }

    fun destroy() {

    }
}