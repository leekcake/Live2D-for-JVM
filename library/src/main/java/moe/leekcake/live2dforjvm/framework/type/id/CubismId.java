package moe.leekcake.live2dforjvm.framework.type.id;

import moe.leekcake.live2dforjvm.framework.jni.id.CubismIdJNI;
import moe.leekcake.live2dforjvm.core.type.AutoPointer;

public class CubismId extends AutoPointer {
    public CubismId(long pointer) {
        super(pointer);
    }

    public String getString() {
        return CubismIdJNI.GetString(pointer);
    }
}
