package moe.leekcake.live2dforjvm.core.jni;

/**
 * JNI Class for Native Memory Access / (de)Allocate
 * <p>
 * Used as Reference: https://github.com/Live2D/CubismNativeComponents/blob/early-access/sample/src/Allocation.c
 */
public class MemoryAccessJNI {
    static {
        System.loadLibrary("MemoryAccessJNI");
    }

    //Allocation.c + Variant
    public static native long allocate(int size);

    public static native long allocateAligned(int size, int align);

    /**
     * Put 'data'[offset ~ offset+Length] into 'memory'[start ~ start+Length]
     * @param memory Dest Memory
     * @param start Start Offset for Dest
     * @param data Src Data
     * @param dataOffset Data Offset
     * @param dataLength Data Length to copy
     */
    public static native void writeToMemory(long memory, int start, byte[] data, int dataOffset, int dataLength);

    public static native void deAllocate(long memory);

    public static native void deAllocateAligned(long alignedMemory);
}
