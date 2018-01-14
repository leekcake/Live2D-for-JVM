package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.MemoryAccessJNI

/**
 * try to auto-release c pointer with java 'finalize' method,
 */
abstract class AutoPointer(val pointer: Long) {
    private var released: Boolean = false

    //Java will call it, may be?
    @Suppress("Unused")
    protected fun finalize() {
        release()
    }

    open fun release() {
        if (released) return
        MemoryAccessJNI.deAllocate(pointer)
        released = true
    }
}