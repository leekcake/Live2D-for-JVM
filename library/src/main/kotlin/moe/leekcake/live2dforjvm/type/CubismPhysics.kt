package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismPhysics(pointer: Long): AutoPointer(pointer) {
    companion object {
        fun generatePhysics(json: String): Long {
            val size = Live2DCubismFrameworkJNI.getDeserializedSizeofPhysics(json)
            val pointer = MemoryAccessJNI.allocate(size.toInt())

            Live2DCubismFrameworkJNI.deserializePhysicsInPlace(json, pointer, size)

            return pointer
        }
    }

    constructor(json: String) : this( generatePhysics(json) )

    fun evaluate(model: CubismModel,
                 option: CubismPhysicsOptions,
                 deltaTime: Float) {
        Live2DCubismFrameworkJNI.physicsEvaluate(model.pointer, pointer, option.pointer, deltaTime)
    }
}