package moe.leekcake.live2dforjvm.framework.type.id

import moe.leekcake.live2dforjvm.framework.id.CubismIdJNI

class CubismId(val pointer: Long) {
    fun getString(): String {
        return CubismIdJNI.CubismId_GetString(pointer);
    }
}