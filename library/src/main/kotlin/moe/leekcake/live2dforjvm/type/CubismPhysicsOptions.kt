package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI

class CubismPhysicsOptions(pointer: Long): AutoPointer(pointer) {
    constructor(): this(Live2DCubismFrameworkJNI.allocatePhysicsOptions())

    val gravity = Vector2( Live2DCubismFrameworkJNI.getGravitiyPhysicsOptions(pointer) )
    val wind = Vector2( Live2DCubismFrameworkJNI.getWindPhysicsOptions(pointer) )
}