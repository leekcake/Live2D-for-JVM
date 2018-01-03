package moe.leekcake.live2dforjvm;

/**
 * JNI Class for Native Memory Access / (de)Allocate
 *
 * Used as Reference: https://github.com/Live2D/CubismNativeComponents/blob/early-access/sample/src/Allocation.c
 */
public class MemoryAccessJNI {
    //Allocation.c + Variant
    public static native long allocate(int size);
    public static native long allocateAligned(int size, int align);

    //memory의 start 부분부터 시작해서 data의 offset부터 length까지의 데이터를 넣습니다
    public static native void writeToMemory(long memory, int start, byte[] data, int dataOffset, int dataLength);

    public static native void deAllocate(long memory);
    public static native void deAllocateAligned(long alignedMemory);
}
