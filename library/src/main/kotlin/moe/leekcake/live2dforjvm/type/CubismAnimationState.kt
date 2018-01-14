package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI

class CubismAnimationState(pointer: Long = Live2DCubismFrameworkJNI.allocateAnimationState()) : AutoPointer(pointer) {
    init {
        Live2DCubismFrameworkJNI.initializeAnimationState(pointer)
        reset()
    }

    val time: Float
        get() = Live2DCubismFrameworkJNI.getAnimationStateTime(pointer)

    fun reset() = Live2DCubismFrameworkJNI.resetAnimationState(pointer)
    fun update(deltaTime: Float) = Live2DCubismFrameworkJNI.updateAnimationState(pointer, deltaTime)
}