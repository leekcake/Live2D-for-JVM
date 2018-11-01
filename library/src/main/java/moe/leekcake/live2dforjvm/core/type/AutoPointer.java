package moe.leekcake.live2dforjvm.core.type;

import moe.leekcake.live2dforjvm.core.jni.MemoryAccessJNI;

public abstract class AutoPointer {
    public final long pointer;
    private boolean isReleased = false;
    public AutoPointer(long pointer) {
        this.pointer = pointer;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        release();
    }

    private void release() {
        if(isReleased) return;
        isReleased = true;
        releasePointer();
    }

    protected void releasePointer() {
        MemoryAccessJNI.deAllocate(pointer);
    }
}
