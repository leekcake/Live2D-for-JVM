package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.framework.CubismIdManagerJNI

class CubismIdManager(val pointer: Long) {
    fun registerIds(ids: Array<String>) {
        return CubismIdManagerJNI.CubismIdManager_RegisterIds(pointer, ids)
    }

    fun registerId(id: String) {
        return CubismIdManagerJNI.CubismIdManager_RegisterId(pointer, id)
    }

    fun getId(id: String): CubismId {
        return CubismId(CubismIdManagerJNI.CubismIdManager_GetId(pointer, id))
    }

    fun isExist(id: String): Boolean {
        return CubismIdManagerJNI.CubismIdManager_IsExist(pointer, id)
    }
}