package moe.leekcake.live2dforjvm.framework.type.id;

import moe.leekcake.live2dforjvm.framework.jni.id.CubismIdManagerJNI;
import moe.leekcake.live2dforjvm.core.type.AutoPointer;

public class CubismIdManager extends AutoPointer {
    public CubismIdManager(long pointer) {
        super(pointer);
    }

    public void registerIds(String[] ids) {
        CubismIdManagerJNI.RegisterIds(pointer, ids);
    }

    public void registerId(String id) {
        CubismIdManagerJNI.RegisterId(pointer, id);
    }

    public CubismId getId(String id) {
        return new CubismId(CubismIdManagerJNI.GetId(pointer, id));
    }

    public boolean isExist(String id) {
        return CubismIdManagerJNI.IsExist(pointer, id);
    }
}
