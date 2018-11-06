package moe.leekcake.live2dforjvm.framework.jni.math;

public class CubismMatrix44JNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native long Construct();
    public static native void Deconstruct(long pointer);

    public static native void LoadIdentity(long pointer);
    public static native float[] GetArray(long pointer);
    public static native void SetMatrix(long pointer, float[] matrix);

    public static native float GetScaleX(long pointer);
    public static native float GetScaleY(long pointer);

    public static native float GetTranslateX(long pointer);
    public static native float GetTranslateY(long pointer);

    public static native float TransformX(long pointer, float x);
    public static native float TransformY(long pointer, float y);

    public static native float InvertTransformX(long pointer, float x);
    public static native float InvertTransformY(long pointer, float y);

    public static native void TranslateRelative(long pointer, float x, float y);
    public static native void Translate(long pointer, float x, float y);

    public static native void TranslateX(long pointer, float x);
    public static native void TranslateY(long pointer, float y);

    public static native void ScaleRelative(long pointer, float x, float y);
    public static native void Scale(long pointer, float x, float y);
    public static native void MultiplyByMatrix(long pointer, long matrix);
}
