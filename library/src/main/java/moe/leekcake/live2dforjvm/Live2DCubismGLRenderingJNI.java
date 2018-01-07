package moe.leekcake.live2dforjvm;

public class Live2DCubismGLRenderingJNI {
    public static native long getSizeofGlRenderer(long model);

    public static native long makeGlRendererInPlace(long model, long address, long size);

    public static native void releaseGlRenderer(long renderer);

    public static native void updateGlRenderer(long renderer);

    public static native void drawGl(long renderer, float mvp, long textures);
}