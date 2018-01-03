package moe.leekcake.live2dforjvm.type

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

    class CubismModelHashTable {
        @JvmField
        var parameterIdHashes: IntArray? = null

        @JvmField
        var partIdHashes: IntArray? = null
    }
}