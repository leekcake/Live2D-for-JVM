package moe.leekcake.live2dforjvm.framework.jni;

public class CubismFrameworkJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native boolean StartUp();

    public static native void CleanUp();

    public static native boolean IsStarted();

    public static native void Initialize();

    public static native void Dispose();

    public static native boolean IsInitialized();

    public static native long GetIdManager();
}
