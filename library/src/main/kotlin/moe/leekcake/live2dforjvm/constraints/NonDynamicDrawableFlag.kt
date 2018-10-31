package moe.leekcake.live2dforjvm.constraints

/**
 * BitMask for non-dynamic drawable flags.
 *
 * Wrapping of /** Bit masks for non-dynamic drawable flags. */ part
 */
object NonDynamicDrawableFlag {
    /** Additive blend mode mask. */
    val blendAdditive = 1 shl 0

    /** Multiplicative blend mode mask. */
    val blendMultiplicative = 1 shl 1

    /** Double-sidedness mask. */
    val isDoubleSided = 1 shl 2
}