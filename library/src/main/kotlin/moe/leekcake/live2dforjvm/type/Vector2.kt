package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismCoreJNI

class Vector2(val pointer: Long) {
    companion object {
        fun allocate(x: Float = 0f, y: Float = 0f): Vector2 {
            return Vector2(Live2DCubismCoreJNI.allocateVector2(x, y))
        }
    }

    var x: Float
        get() = Live2DCubismCoreJNI.getVector2X(pointer)
        set(value) = Live2DCubismCoreJNI.setVector2X(pointer, value)

    var y: Float
        get() = Live2DCubismCoreJNI.getVector2Y(pointer)
        set(value) = Live2DCubismCoreJNI.setVector2Y(pointer, value)
}