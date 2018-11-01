package moe.leekcake.live2dforjvm.core.constraints;

public class DynamicDrawableFlag {
    /** Flag set when visible. */
    public static final int isVisible = 1 << 0;
    /** Flag set when visibility did change. */
    public static final int visibilityDidChange = 1 << 1;
    /** Flag set when opacity did change. */
    public static final int opacityDidChange = 1 << 2;
    /** Flag set when draw order did change. */
    public static final int drawOrderDidChange = 1 << 3;
    /** Flag set when render order did change. */
    public static final int renderOrderDidChange = 1 << 4;
    /** Flag set when vertex positions did change. */
    public static final int vertexPositionsDidChange = 1 << 5;
}
