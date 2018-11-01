package moe.leekcake.live2dforjvm.framework.type.id;

import moe.leekcake.live2dforjvm.framework.jni.id.CubismIdManagerJNI;
import moe.leekcake.live2dforjvm.core.type.AutoPointer;

public class CubismIdManager extends AutoPointer {
    public CubismIdManager(long pointer) {
        super(pointer);
    }

    public void registerIds(String[] ids) {
        CubismIdManagerJNI.CubismIdManager_RegisterIds(pointer, ids);
    }

    public void registerId(String id) {
        CubismIdManagerJNI.CubismIdManager_RegisterId(pointer, id);
    }

    public CubismId getId(String id) {
        return new CubismId(CubismIdManagerJNI.CubismIdManager_GetId(pointer, id));
    }

    public boolean isExist(String id) {
        return CubismIdManagerJNI.CubismIdManager_IsExist(pointer, id);
    }
}
