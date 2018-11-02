package moe.leekcake.live2dforjvm.framework.jni.id;

public class CubismIdManagerJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native void RegisterIds(long manager, String[] ids);

    public static native void RegisterId(long manager, String id);

    public static native long GetId(long manager, String id);

    public static native boolean IsExist(long manager, String id);
}
