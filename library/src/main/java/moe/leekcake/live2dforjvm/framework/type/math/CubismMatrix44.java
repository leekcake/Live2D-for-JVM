package moe.leekcake.live2dforjvm.framework.type.math;

import moe.leekcake.live2dforjvm.core.type.AutoPointer;
import moe.leekcake.live2dforjvm.framework.jni.math.CubismMatrix44JNI;

public class CubismMatrix44 extends AutoPointer {
    public CubismMatrix44() {
        super(CubismMatrix44JNI.Construct());
    }

    @Override
    protected void releasePointer() {
        CubismMatrix44JNI.Deconstruct(pointer);
    }

    public void loadIdentity() {
        CubismMatrix44JNI.LoadIdentity(pointer);
    }

    public float[] getArray() {
        return CubismMatrix44JNI.GetArray(pointer);
    }

    public void setMatrix(float[] arr) {
        CubismMatrix44JNI.SetMatrix(pointer, arr);
    }

    public float getScaleX() {
        return CubismMatrix44JNI.GetScaleX(pointer);
    }

    public float getScaleY() {
        return CubismMatrix44JNI.GetScaleY(pointer);
    }

    public float getTranslateX() {
        return CubismMatrix44JNI.GetTranslateX(pointer);
    }

    public float getTranslateY() {
        return CubismMatrix44JNI.GetTranslateY(pointer);
    }

    public void transformX(float x) {
        CubismMatrix44JNI.TransformX(pointer, x);
    }

    public void transformY(float y) {
        CubismMatrix44JNI.TransformY(pointer, y);
    }

    public void inventTransformX(float x) {
        CubismMatrix44JNI.InvertTransformX(pointer, x);
    }

    public void inventTransformY(float y) {
        CubismMatrix44JNI.InvertTransformY(pointer, y);
    }

    public void translateRelative(float x, float y) {
        CubismMatrix44JNI.TranslateRelative(pointer, x, y);
    }

    public void translate(float x, float y) {
        CubismMatrix44JNI.Translate(pointer, x, y);
    }

    public void translateX(float x) {
        CubismMatrix44JNI.TranslateX(pointer, x);
    }

    public void translateY(float y) {
        CubismMatrix44JNI.TranslateY(pointer, y);
    }

    public void scaleRelative(float x, float y) {
        CubismMatrix44JNI.ScaleRelative(pointer, x, y);
    }

    public void scale(float x, float y) {
        CubismMatrix44JNI.Scale(pointer, x, y);
    }

    public void multiplyByMatrix(CubismMatrix44 matrix) {
        CubismMatrix44JNI.MultiplyByMatrix(pointer, matrix.pointer);
    }
}
