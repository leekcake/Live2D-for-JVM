package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismGLRenderingJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismGLRenderer(pointer: Long) : AutoPointer(pointer) {
    companion object {
        fun generateGLRenderer(model: CubismModel): Long {
            val size = Live2DCubismGLRenderingJNI.getSizeofGlRenderer(model.pointer)
            val pointer = MemoryAccessJNI.allocate(size.toInt())

            Live2DCubismGLRenderingJNI.makeGlRendererInPlace(model.pointer, pointer, size)
            return pointer
        }
    }

    constructor(model: CubismModel) : this(generateGLRenderer(model))

    override fun release() {
        Live2DCubismGLRenderingJNI.releaseGlRenderer(pointer)
        super.release()
    }

    fun update() {
        Live2DCubismGLRenderingJNI.updateGlRenderer(pointer)
    }

    fun draw(mvp: FloatArray, texture: Long) {
        Live2DCubismGLRenderingJNI.drawGl(pointer, mvp, texture)
    }
}