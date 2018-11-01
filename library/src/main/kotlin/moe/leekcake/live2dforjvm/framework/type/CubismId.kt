package moe.leekcake.live2dforjvm.framework.type

import moe.leekcake.live2dforjvm.framework.CubismIdJNI

class CubismId(val pointer: Long) {
    fun getString(): String {
        return CubismIdJNI.CubismId_GetString(pointer);
    }
}