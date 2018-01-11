package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

class CubismModelHashTable(pointer: Long): AutoPointer(pointer) {
    companion object {
        private fun generateHashTableFromModel(model: CubismModel): Long {
            val size = Live2DCubismFrameworkJNI.getSizeofModelHashTable(model.pointer)
            val pointer = MemoryAccessJNI.allocate( size.toInt() )
            Live2DCubismFrameworkJNI.initializeModelHashTableInPlace(model.pointer, pointer, size)

            return pointer
        }
    }

    constructor(model: CubismModel): this( generateHashTableFromModel(model) )

    val parameter = Parameter()
    inner class Parameter {
        operator fun get(index: Int): Int {
            return Live2DCubismFrameworkJNI.getHashTableParameterHash(pointer, index)
        }

        val size: Int
            get() = Live2DCubismFrameworkJNI.getHashTableParameterCount(pointer)
    }

    val part = Part()
    inner class Part {
        operator fun get(index: Int): Int {
            return Live2DCubismFrameworkJNI.getHashTablePartHash(pointer, index)
        }

        val size: Int
            get() = Live2DCubismFrameworkJNI.getHashTablePartCount(pointer)
    }

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