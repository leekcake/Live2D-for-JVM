package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismUserData(pointer: Long) : AutoPointer(pointer) {
    companion object {
        private fun generateUserData(json: String): Long {
            val size = Live2DCubismFrameworkJNI.getDeserializedSizeofUserData(json)
            val pointer = MemoryAccessJNI.allocate(size.toInt())
            Live2DCubismFrameworkJNI.deserializeUserDataInPlace(json, pointer, size)

            return pointer
        }
    }

    class Sink(pointer: Long) : AutoPointer(pointer) {
        constructor(): this( Live2DCubismFrameworkJNI.allocateUserDataSink() )

        val hashId: Int = Live2DCubismFrameworkJNI.getUserDataSinkHashId(pointer)
        val value: String = Live2DCubismFrameworkJNI.getUserDataSinkValue(pointer)
    }

    constructor(json: String): this( generateUserData(json) )

    val count = Live2DCubismFrameworkJNI.getUserDataCount(pointer)

    operator fun get(inx: Int): Sink {
        val sink = Sink()

        getDataTo(inx, sink)

        return sink
    }

    fun getDataTo(inx: Int, sink: Sink) {
        Live2DCubismFrameworkJNI.getUserData(pointer, inx, sink.pointer)
    }
}