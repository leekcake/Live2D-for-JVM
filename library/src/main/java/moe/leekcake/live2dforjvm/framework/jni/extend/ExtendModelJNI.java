package moe.leekcake.live2dforjvm.framework.jni.extend;

public class ExtendModelJNI {
    public interface MotionEventFireable {
        void OnMotionEventFired(String eventValue);
    }

    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    public static native long Construct();
    public static native void Deconstruct(long pointer);

    public static native void LoadAssets(long pointer, String baseDir, String fileName);
    public static native void ReloadRenderer(long pointer);
    public static native void Update(long pointer);
    public static native void Draw(long pointer, long matrix);
    public static native void StartMotion(long pointer, String group, int no, int priority);
    public static native void StartRandomMotion(long pointer, String group, int priority);
    public static native void SetExpression(long pointer, String expressionID);
    public static native void SetRandomExpression(long pointer);
    public static native void RegisterMotionEventCallback(long pointer, MotionEventFireable fireable);
    public static native boolean HitTest(long pointer, String hitAreaName, float x, float y);
    public static native void SetDragging(long pointer, float x, float y);

    public static native void SetAcceleration(long pointer, float x, float y, float z);
    public static native void SetOpacity(long pointer, float opacity);
    public static native float GetOpacity(long pointer);
    public static native boolean IsInMotion(long pointer);

    public static native long GetModelMatrix(long pointer);
}
