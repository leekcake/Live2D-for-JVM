package moe.leekcake.live2dforjvm.framework.jni.id;

public class CubismIdJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native String CubismId_GetString(long id);
}