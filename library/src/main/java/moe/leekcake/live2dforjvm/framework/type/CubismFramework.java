package moe.leekcake.live2dforjvm.framework.type;

import moe.leekcake.live2dforjvm.framework.jni.CubismFrameworkJNI;
import moe.leekcake.live2dforjvm.framework.type.id.CubismIdManager;

public class CubismFramework {
    public static void startUp() {
        CubismFrameworkJNI.CubismFramework_StartUp();
    }

    public static void cleanUp() {
        CubismFrameworkJNI.CubismFramework_CleanUp();
    }

    public static boolean isStarted() {
        return CubismFrameworkJNI.CubismFramework_IsStarted();
    }

    public static void initialize() {
        CubismFrameworkJNI.CubismFramework_Initialize();
    }

    public static void dispose() {
        CubismFrameworkJNI.CubismFramework_Dispose();
    }

    public static boolean isInitialized() {
        return CubismFrameworkJNI.CubismFramework_IsInitialized();
    }

    public static CubismIdManager getIdManager() {
        return new CubismIdManager(CubismFrameworkJNI.CubismFramework_GetIdManager());
    }
}
