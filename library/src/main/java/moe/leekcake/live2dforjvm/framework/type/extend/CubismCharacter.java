package moe.leekcake.live2dforjvm.framework.type.extend;

import moe.leekcake.live2dforjvm.core.type.AutoPointer;
import moe.leekcake.live2dforjvm.framework.jni.extend.ExtendModelJNI;
import moe.leekcake.live2dforjvm.framework.type.math.CubismMatrix44;

/***
 * Wrapper of ExtendModel
 */
public class CubismCharacter extends AutoPointer {
    public CubismCharacter() {
        super(ExtendModelJNI.Construct());
    }

    @Override
    protected void releasePointer() {
        ExtendModelJNI.Deconstruct(pointer);
    }

    public void loadAssets(String baseDir, String fn) {
        ExtendModelJNI.LoadAssets(pointer, baseDir, fn);
    }

    public void reloadRenderer() {
        ExtendModelJNI.ReloadRenderer(pointer);
    }

    public void update() {
        ExtendModelJNI.Update(pointer);
    }

    public void draw(CubismMatrix44 matrix) {
        ExtendModelJNI.Draw(pointer, matrix.pointer);
    }

    public void startMotion(String group, int no, int priority) {
        ExtendModelJNI.StartMotion(pointer, group, no, priority);
    }

    public void startRandomMotion(String group, int priority) {
        ExtendModelJNI.StartRandomMotion(pointer, group, priority);
    }

    public void setExpression(String expression) {
        ExtendModelJNI.SetExpression(pointer, expression);
    }

    public void setRandomExpression() {
        ExtendModelJNI.SetRandomExpression(pointer);
    }

    public void registerMotionEventCallback(ExtendModelJNI.MotionEventFireable fireable) {
        ExtendModelJNI.RegisterMotionEventCallback(pointer, fireable);
    }

    public boolean hitTest(String hitArea, float x, float y) {
        return ExtendModelJNI.HitTest(pointer, hitArea, x, y);
    }
}
