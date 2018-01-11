package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismModelHashTable(val pointer: Long) {
    companion object {
        private fun generateHashTableFromModel(model: CubismModel): Long {
            val size = Live2DCubismFrameworkJNI.getSizeofModelHashTable(model.pointer)
            val pointer = MemoryAccessJNI.allocate( size.toInt() )
            Live2DCubismFrameworkJNI.initializeModelHashTableInPlace(model.pointer, pointer, size)

            return pointer
        }
    }

    constructor(model: CubismModel): this( generateHashTableFromModel(model) )

    inner class ParameterHashGetter {
        operator fun get(hash: Int): Int {
            return Live2DCubismFrameworkJNI.findParameterIndexByHashFAST(pointer, hash)
        }
    }
    val parameterHash = ParameterHashGetter()

    inner class PartHashGetter {
        operator fun get(hash: Int): Int {
            return Live2DCubismFrameworkJNI.findPartIndexByHashFAST(pointer, hash)
        }
    }
    val partHash = PartHashGetter()
}