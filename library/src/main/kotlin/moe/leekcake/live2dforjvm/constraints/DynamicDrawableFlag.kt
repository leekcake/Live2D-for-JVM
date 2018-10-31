package moe.leekcake.live2dforjvm.constraints

object DynamicDrawableFlag {
    /** Flag set when visible. */
    val isVisible = 1 shl 0
    /** Flag set when visibility did change. */
    val visibilityDidChange = 1 shl 1
    /** Flag set when opacity did change. */
    val opacityDidChange = 1 shl 2
    /** Flag set when draw order did change. */
    val drawOrderDidChange = 1 shl 3
    /** Flag set when render order did change. */
    val renderOrderDidChange = 1 shl 4
    /** Flag set when vertex positions did change. */
    val vertexPositionsDidChange = 1 shl 5
}