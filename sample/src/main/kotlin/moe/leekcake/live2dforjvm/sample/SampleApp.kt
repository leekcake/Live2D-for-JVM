package moe.leekcake.live2dforjvm.sample

import moe.leekcake.live2dforjvm.framework.jni.CubismFrameworkJNI
import moe.leekcake.live2dforjvm.framework.jni.extend.ExtendPALJNI
import moe.leekcake.live2dforjvm.framework.type.extend.CubismCharacter
import moe.leekcake.live2dforjvm.framework.type.math.CubismMatrix44

abstract class SampleApp : ExtendPALJNI.FileProvider {
    abstract fun generateTexture(path: String): Int

    lateinit var char: CubismCharacter
    val matrix = CubismMatrix44()

    fun init() {
        CubismFrameworkJNI.StartUp()
        CubismFrameworkJNI.Initialize()

        char = CubismCharacter()

        ExtendPALJNI.setFileProvider(this)
        char.loadAssets("", "Hiyori.model3.json")
    }

    fun update() {
        matrix.loadIdentity()
        matrix.scale(1f, 1f)
        char.update()
        char.draw(matrix)
    }

    fun destroy() {

    }
}