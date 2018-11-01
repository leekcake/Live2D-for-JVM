package moe.leekcake.live2dforjvm.framework.type

import moe.leekcake.live2dforjvm.framework.CubismFrameworkJNI

object CubismFramework {
    fun startUp() {
        CubismFrameworkJNI.CubismFramework_StartUp()
    }

    fun cleanUp() {
        CubismFrameworkJNI.CubismFramework_CleanUp()
    }

    fun isStarted(): Boolean = CubismFrameworkJNI.CubismFramework_IsStarted()

    fun initialize() {
        CubismFrameworkJNI.CubismFramework_Initialize()
    }

    fun dispose() {
        CubismFrameworkJNI.CubismFramework_Dispose()
    }

    fun isInitialized(): Boolean {
        return CubismFrameworkJNI.CubismFramework_IsInitialized()
    }

    fun getIdManager(): CubismIdManager {
        return CubismIdManager(CubismFrameworkJNI.CubismFramework_GetIdManager())
    }
}