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

    val duration = Live2DCubismFrameworkJNI.getAnimationDuration(pointer)
    val loop = Live2DCubismFrameworkJNI.getAnimationIsLoop(pointer)

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

    val userData = UserData()

    class Sink(pointer: Long) : AutoPointer(pointer) {
        constructor(): this( Live2DCubismFrameworkJNI.allocateAnimationUserDataSink() )

        val time: Float = Live2DCubismFrameworkJNI.getAnimationUserDataSinkTime(pointer)
        val value: String = Live2DCubismFrameworkJNI.getAnimationUserDataValue(pointer)
    }

    inner class UserData {
        val count = Live2DCubismFrameworkJNI.getAnimationUserDataCount(pointer)
        operator fun get(inx: Int): Sink {
            val sink = Sink()

            getDataTo(inx, sink)

            return sink
        }

        fun getDataTo(inx: Int, sink: Sink) {
            Live2DCubismFrameworkJNI.getAnimationUserData(pointer, inx, sink.pointer)
        }
    }
}