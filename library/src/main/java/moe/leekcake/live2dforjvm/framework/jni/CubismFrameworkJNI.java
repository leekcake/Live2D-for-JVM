package moe.leekcake.live2dforjvm.framework.jni;

import moe.leekcake.live2dforjvm.core.jni.Live2DCubismCoreJNI;

public class CubismFrameworkJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
        Live2DCubismCoreJNI.getVersion(); //Register core log function callback
    }

    public static native boolean StartUp();

    public static native void CleanUp();

    public static native boolean IsStarted();

    public static native void Initialize();

    public static native void Dispose();

    public static native boolean IsInitialized();

    public static native long GetIdManager();
}
