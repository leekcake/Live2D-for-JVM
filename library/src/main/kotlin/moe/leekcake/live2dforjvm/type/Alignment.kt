package moe.leekcake.live2dforjvm.type

/**
 * Alignment const for Memory Allocate
 *
 * Wrapping of /** Alignment constraints. */ part
 */
object Alignment {
    /** Necessary alignment for mocs (in bytes). */
    val moc = 64

    /** Necessary alignment for models (in bytes). */
    val model = 16
}