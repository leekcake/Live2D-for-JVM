package moe.leekcake.live2dforjvm.framework.type;

import moe.leekcake.live2dforjvm.framework.jni.CubismFrameworkJNI;
import moe.leekcake.live2dforjvm.framework.type.id.CubismIdManager;

public class CubismFramework {
    public static void startUp() {
        CubismFrameworkJNI.StartUp();
    }

    public static void cleanUp() {
        CubismFrameworkJNI.CleanUp();
    }

    public static boolean isStarted() {
        return CubismFrameworkJNI.IsStarted();
    }

    public static void initialize() {
        CubismFrameworkJNI.Initialize();
    }

    public static void dispose() {
        CubismFrameworkJNI.Dispose();
    }

    public static boolean isInitialized() {
        return CubismFrameworkJNI.IsInitialized();
    }

    public static CubismIdManager getIdManager() {
        return new CubismIdManager(CubismFrameworkJNI.GetIdManager());
    }
}
