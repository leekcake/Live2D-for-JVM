package moe.leekcake.live2dforjvm.framework.jni.math;

public class CubismMatrix44JNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native long CubismMatrix44_Construct();
    public static native void CubismMatrix44_Deconstruct(long pointer);

    public static native void CubismMatrix44_LoadIdentity(long pointer);
    public static native float[] CubismMatrix44_GetArray(long pointer);
    public static native void CubismMatrix44_SetMatrix(long pointer, float[] matrix);

    public static native float CubismMatrix44_GetScaleX(long pointer);
    public static native float CubismMatrix44_GetScaleY(long pointer);

    public static native float CubismMatrix44_GetTranslateX(long pointer);
    public static native float CubismMatrix44_GetTranslateY(long pointer);

    public static native void CubismMatrix44_TransformX(long pointer, long x);
    public static native void CubismMatrix44_TransformY(long pointer, long y);

    public static native void CubismMatrix44_InvertTransformX(long pointer, long x);
    public static native void CubismMatrix44_InvertTransformY(long pointer, long y);

    public static native void CubismMatrix44_TranslateRelative(long pointer, float x, float y);
    public static native void CubismMatrix44_Translate(long pointer, float x, float y);

    public static native void CubismMatrix44_TranslateX(long pointer, float x);
    public static native void CubismMatrix44_TranslateY(long pointer, float y);

    public static native void CubismMatrix44_ScaleRelative(long pointer, float x, float y);
    public static native void CubismMatrix44_Scale(long pointer, float x, float y);
    public static native void CubismMatrix44_MultiplyByMatrix(long pointer, long matrix);
}
