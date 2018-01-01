package moe.leekcake.live2dforjvm;

import moe.leekcake.live2dforjvm.type.Vector2;

/**
 * JNI Class for Live2DCubismCore
 */
public class Live2DCubismCoreJNI {

    static {
        System.loadLibrary("Live2DCubismCoreJNI");
    }

    //Live2DCubismCore.h

    /* ------- *
     * VERSION *
     * ------- */

    //typedef unsigned int csmVersion;

    /**
     * @return Version of Cubism Core
     */
    public static native long getVersion();

    /* ------- *
     * LOGGING *
     * ------- */
    //TODO: LOG Part

    /* --- *
     * MOC *
     * --- */

    //csmApi csmMoc* csmReviveMocInPlace(void* address, const unsigned int size);

    /**
     * Revive Moc3 file from memory<br>
     * TODO: Make 'Revive' more understandable
     * @param address Address of Memory
     * @param size Size of Memory
     * @return csmMoc pointer
     */
    public static native long reviveMocInPlace(long address, long size);

    /* ----- *
     * MODEL *
     * ----- */

    //csmApi unsigned int csmGetSizeofModel(const csmMoc* moc);

    /**
     * Get Size for Allocate Memory (for Initialize Model)
     * //TODO: Better Comment...
     * @param moc csmMoc pointer
     * @return Size of Model
     */
    public static native long getSizeofModel(long moc);

    //csmApi csmModel* csmInitializeModelInPlace(const csmMoc* moc,
    //                                                void* address,
    //                                                const unsigned int size);

    /**
     * Initialize model from moc file to memory
     * @param moc csmMoc pointer
     * @param address Address of Memory
     * @param size Size of Memory
     * @return csmModel pointer
     */
    public static native long initializeModelInPlace(long moc, long address, long size);

    //csmApi void csmUpdateModel(csmModel* model);

    /**
     * TODO: Know about 'update'
     *
     * @param model csmModel Pointer
     */
    public static native void updateModel(long model);

    /* ------ *
     * CANVAS *
     * ------ */

    //csmApi void csmReadCanvasInfo(const csmModel* model,
    //                                  csmVector2* outSizeInPixels,
    //                                  csmVector2* outOriginInPixels,
    //                                  float* outPixelsPerUnit);
    //outPixelsPerUnit -> result

    /**
     * Get Canvas Info from Model
     * @param model csmModel pointer
     * @param outSizeInPixels canvas size in pixels
     * @param outOriginInPixels origin size in pixels
     * @return outPixelsPerUnit //TODO: Know about 'unit'
     */
    public static native float readCanvasInfo(long model, Vector2 outSizeInPixels, Vector2 outOriginInPixels);

    /* ---------- *
     * PARAMETERS *
     * ---------- */
    //TODO: Complete Document?

    //csmApi int csmGetParameterCount(const csmModel* model);

    public static native int getParameterCount(long model);

    //csmApi const char** csmGetParameterIds(const csmModel* model);
    public static native String getParameterId(long model, int inx);

    //csmApi const float* csmGetParameterMinimumValues(const csmModel* model);
    public static native float getParameterMinimumValue(long model, int inx);

    //csmApi const float* csmGetParameterMaximumValues(const csmModel* model);
    public static native float getParameterMaximumValue(long model, int inx);

    //csmApi const float* csmGetParameterDefaultValues(const csmModel* model);
    public static native float getParameterDefaultValue(long model, int inx);

    //csmApi float* csmGetParameterValues(csmModel* model);
    public static native float getParameterValue(long model, int inx);

    /* ----- *
     * PARTS *
     * ----- */

    //csmApi int csmGetPartCount(const csmModel* model);
    public static native int getPartCount(long model);

    //csmApi const char** csmGetPartIds(const csmModel* model);
    public static native String getPartId(long model, int inx);

    //csmApi float* csmGetPartOpacities(csmModel* model);
    public static native float getPartOpacity(long model, int inx);

    /* --------- *
     * DRAWABLES *
     * --------- */

    //csmApi int csmGetDrawableCount(const csmModel* model);
    public static native int getDrawableCount(long model);

    //csmApi const char** csmGetDrawableIds(const csmModel* model);
    public static native String getDrawableId(long model, int inx);

    //typedef unsigned char csmFlags;
    //csmApi const csmFlags* csmGetDrawableConstantFlags(const csmModel* model);
    public static native int getDrawableConstantFlag(long model, int inx);

    //csmApi const csmFlags* csmGetDrawableDynamicFlags(const csmModel* model);
    public static native int getDrawableDynamicFlag(long model, int inx);

    //csmApi const int* csmGetDrawableTextureIndices(const csmModel* model);
    public static native int getDrawableTextureIndex(long model, int inx);

    //csmApi const int* csmGetDrawableDrawOrders(const csmModel* model);
    public static native int getDrawableDrawOrder(long model, int inx);

    //csmApi const int* csmGetDrawableRenderOrders(const csmModel* model);
    public static native int getDrawableRenderOrder(long model, int inx);

    //csmApi const float* csmGetDrawableOpacities(const csmModel* model);
    public static native float getDrawableOpacity(long model, int inx);

    //csmApi const int* csmGetDrawableMaskCounts(const csmModel* model);
    public static native int getDrawableMaskCount(long model, int inx);

    //csmApi const int** csmGetDrawableMasks(const csmModel* model);
    public static native int getDrawableMask(long model, int inx, int maskInx);

    //csmApi const int* csmGetDrawableVertexCounts(const csmModel* model);
    public static native int getDrawableVertexCount(long model, int inx);

    //csmApi const csmVector2** csmGetDrawableVertexPositions(const csmModel* model);
    public static native void getDrawableVertexPosition(long model, int inx, int vertexInx, Vector2 out);

    //csmApi const csmVector2** csmGetDrawableVertexUvs(const csmModel* model);
    public static native void getDrawableVertexUv(long model, int inx, int vertexInx, Vector2 out);

    //csmApi const int* csmGetDrawableIndexCounts(const csmModel* model);
    public static native int getDrawableIndexCount(long model, int inx);

    //csmApi const unsigned short** csmGetDrawableIndices(const csmModel* model);
    public static native int getDrawableIndex(long model, int inx, int Indexinx);

    //csmApi void csmResetDrawableDynamicFlags(csmModel* model);
    public static native void resetDrawableDynamicFlags(long model);
}
