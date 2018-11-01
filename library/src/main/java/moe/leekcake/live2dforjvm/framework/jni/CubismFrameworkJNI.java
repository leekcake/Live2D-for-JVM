package moe.leekcake.live2dforjvm.framework.jni;

public class CubismFrameworkJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native boolean CubismFramework_StartUp();

    public static native void CubismFramework_CleanUp();

    public static native boolean CubismFramework_IsStarted();

    public static native void CubismFramework_Initialize();

    public static native void CubismFramework_Dispose();

    public static native boolean CubismFramework_IsInitialized();

    public static native long CubismFramework_GetIdManager();
}
