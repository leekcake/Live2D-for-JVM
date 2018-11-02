package moe.leekcake.live2dforjvm.framework.jni.extend;

public class ExtendModel {
    public interface MotionEventFireable {
        void OnMotionEventFired(String eventValue);
    }

    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native long ExtendModel_Construct();
    public static native void ExtendModel_Deconstruct(long pointer);

    public static native void ExtendModel_LoadAssets(long pointer, String baseDir, String fileName);
    public static native void ExtendModel_ReloadRenderer(long pointer);
    public static native void ExtendModel_Update(long pointer);
    public static native void ExtendModel_Draw(long pointer, long matrix);
    public static native void ExtendModel_StartMotion(long pointer, String group, int no, int priority);
    public static native void ExtendModel_StartRandomMotion(long pointer, String group, int priority);
    public static native void ExtendModel_SetExpression(long pointer, String expressionID);
    public static native void ExtendModel_SetRandomExpression(long pointer);
    public static native void ExtendModel_RegisterMotionEventCallback(long pointer, MotionEventFireable fireable);
    public static native boolean ExtendModel_HitTest(long pointer, float x, float y);
}
