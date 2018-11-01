package moe.leekcake.live2dforjvm.framework.id;

public class CubismIdManagerJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native void CubismIdManager_RegisterIds(long manager, String[] ids);

    public static native void CubismIdManager_RegisterId(long manager, String id);

    public static native long CubismIdManager_GetId(long manager, String id);

    public static native boolean CubismIdManager_IsExist(long manager, String id);
}
