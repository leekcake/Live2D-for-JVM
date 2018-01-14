package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismAnimation(pointer: Long) : AutoPointer(pointer) {
    companion object {
        private fun generateAnimation(json: String): Long {
            val size = Live2DCubismFrameworkJNI.getDeserializedSizeofAnimation(json)
            val pointer = MemoryAccessJNI.allocate(size.toInt())
            Live2DCubismFrameworkJNI.deserializeAnimationInPlace(json, pointer, size)

            return pointer
        }
    }

    constructor(json: String) : this(generateAnimation(json))

    fun evaluate(state: CubismAnimationState,
                 blendFunction: Long,
                 weight: Float,
                 model: CubismModel,
                 table: CubismModelHashTable,
                 modelCurveHandler: CubismModel.AnimationCurveType,
                 userData: Long = 0) {
        return Live2DCubismFrameworkJNI.evaluateAnimationFAST(
                pointer, state.pointer, blendFunction, weight, model.pointer, table.pointer,
                modelCurveHandler.value, userData)
    }
}