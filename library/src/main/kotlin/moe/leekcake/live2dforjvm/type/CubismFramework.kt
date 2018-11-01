package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.framework.CubismFrameworkJNI

object CubismFramework {
    fun StartUp() {
        CubismFrameworkJNI.CubismFramework_StartUp()
    }

    fun CleanUp() {
        CubismFrameworkJNI.CubismFramework_CleanUp()
    }

    fun IsStarted(): Boolean = CubismFrameworkJNI.CubismFramework_IsStarted()

    fun Initialize() {
        CubismFrameworkJNI.CubismFramework_Initialize()
    }

    fun Dispose() {
        CubismFrameworkJNI.CubismFramework_Dispose()
    }

    fun IsInitialized(): Boolean {
        return CubismFrameworkJNI.CubismFramework_IsInitialized()
    }

    fun GetIdManager(): CubismIdManager {
        return CubismIdManager(CubismFrameworkJNI.CubismFramework_GetIdManager())
    }
}