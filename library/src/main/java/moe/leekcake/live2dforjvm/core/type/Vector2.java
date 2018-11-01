package moe.leekcake.live2dforjvm.core.type;

import moe.leekcake.live2dforjvm.core.jni.Live2DCubismCoreJNI;

public class Vector2 extends AutoPointer {
    public Vector2() {
        this(0, 0);
    }

    public Vector2(float x, float y) {
        super(Live2DCubismCoreJNI.allocateVector2(0,0));
    }

    public float getX() {
        return Live2DCubismCoreJNI.getVector2X(pointer);
    }

    public float getY() {
        return Live2DCubismCoreJNI.getVector2Y(pointer);
    }

    public void setX(float x) {
        Live2DCubismCoreJNI.setVector2X(pointer, x);
    }

    public void setY(float y) {
        Live2DCubismCoreJNI.setVector2Y(pointer, y);
    }
}
