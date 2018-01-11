package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI

class CubismAnimationState(val pointer: Long = Live2DCubismFrameworkJNI.allocateAnimationState()) {
    init {
        Live2DCubismFrameworkJNI.initializeAnimationState(pointer)
    }

    val time: Float
        get() = Live2DCubismFrameworkJNI.getAnimationStateTime(pointer)

    fun reset() = Live2DCubismFrameworkJNI.resetAnimationState(pointer)
    fun update(deltaTime: Float) = Live2DCubismFrameworkJNI.updateAnimationState(pointer, deltaTime)
}