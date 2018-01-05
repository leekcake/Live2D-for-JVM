package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI

/**
 * TODO: I don't sure about this class's doing in now :(
 *
 * Wrapping of csmModel(Core)
 */
class CubismModel {
    object AnimationCurveType {
        val opacityAnimationCurve = 0
        val eyeBlinkAnimationCurve = 1
        val lipSyncAnimationCurve = 2
    }

    class CubismModelHashTable(val table: Long) {
        val parameter = Parameter()
        val part = Part()

        inner class Parameter {
            operator fun get(index: Int): Int {
                return Live2DCubismFrameworkJNI.getHashTableParameterHash(table, index)
            }

            val size: Int
                get() = Live2DCubismFrameworkJNI.getHashTableParameterCount(table)
        }

        inner class Part {
            operator fun get(index: Int): Int {
                return Live2DCubismFrameworkJNI.getHashTablePartHash(table, index)
            }

            val size: Int
                get() = Live2DCubismFrameworkJNI.getHashTablePartCount(table)
        }
    }
}