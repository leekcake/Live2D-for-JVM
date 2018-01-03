package moe.leekcake.live2dforjvm;

public class Live2DCubismFrameworkJNI {
    // ---------------- //
    // MODEL EXTENSIONS //
    // ---------------- //

    /**
     * Hashes an ID.
     *
     * @param id ID to hash.
     * @return Non-zero hash on success; '0' otherwise.
     */
    public static native int generateHashId(String id);

    /**
     * Gets the size of a model hash table in bytes.
     *
     * @param model model pointer
     * @return Number of bytes necessary.
     */
    public static native long getSizeofModelHashTable(long model);

    /**
     * Initializes model hash table.
     *
     * @param model   Model to hash.
     * @param address Address to place table into.
     * @param size    Size of buffer in bytes.
     * @return Valid pointer on success; '0' otherwise.
     */
    public static native long initializeModelHashTableInPlace(long model, long address, long size);

    /**
     * Finds index of a parameter.
     *
     * @param model Model to query against.
     * @param hash  Parameter ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int findParameterIndexByHash(long model, int hash);

    /**
     * Finds index of a parameter faster by comparing hashes.
     *
     * @param table Table to compare against.
     * @param hash  Parameter ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int findParameterIndexByHashFAST(long table, int hash);

    /**
     * Finds index of a part.
     *
     * @param model Model to query against.
     * @param hash  Part ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int findPartIndexByHash(long model, int hash);

    /**
     * Finds index of a part faster by comparing hashes.
     *
     * @param table Table to compare against.
     * @param hash  Part ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int csmFindPartIndexByHashFAST(long table, int hash);

    /**
     * Finds index of a drawable.
     *
     * @param model Model to query against.
     * @param hash  Drawable ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int csmFindDrawableIndexByHash(long model, int hash);

    /**
     * Queries whether a model uses clipping masks.
     *
     * @param model Model to query.
     * @return Non-zero if model uses clipping masks; '0'otherwise.
     */
    public static native int doesModelUseMasks(long model);


    // --------------- //
    // ANIMATION STATE //
    // --------------- //

    /**
     * Initializes an animation state.
     *
     * @param state State to initialize.
     */
    public static native void initializeAnimationState(long state);

    /**
     * Resets an animation state.
     *
     * @param state State to reset.
     */
    public static native void resetAnimationState(long state);

    /**
     * Ticks an animation state.
     *
     * @param state     State to tick.
     * @param deltaTime Time passed since last tick.
     */
    public static native void updateAnimationState(long state, float deltaTime);

    // --------- //
    // ANIMATION //
    // --------- //

    /**
     * Gets the deserialized size of a serialized animation in bytes.
     *
     * @param motionJsonString Serialized animation to query for.
     * @return Number of bytes necessary.
     */
    public static native long getDeserializedSizeofAnimation(String motionJsonString);

    /**
     * Deserializes an animotion.
     *
     * @param motionJson Serialized animation.
     * @param address    Address to place deserialized animation at.
     * @param size       Size of passed memory block (in bytes).
     * @return Valid pointer on success; '0' otherwise.
     */
    public static native long deserializeAnimationInPlace(String motionJson, long address, long size);

    /**
     * Evaluates an animation fast by using a hash table for look-ups.
     * @param animation Animation to evaluate.
     * @param state Animation state.
     * @param blend Blend function to use for filling sink.
     * @param weight Blend weight factor.
     * @param model Model to apply results to.
     * @param table Model table to user for look-ups.
     * @param handleModelCurve [Optional] Model curve handler.
     * @param userData [Optional] Data to pass to model curve handler.
     */
    public static native void csmEvaluateAnimationFAST(long animation,
                                                       long state,
                                                       long blend,
                                                       float weight,
                                                       long model,
                                                       long table,
                                                       long handleModelCurve,
                                                       long userData);

    public static native long getDeserializedSizeofPhysics(String physicsJson);

    public static native long deserializePhysicsInPlace(String physicsJson, long address, long size);

    public static native void csmPhysicsEvaluate(long model,
                                                 long physics,
                                                 long options,
                                                 float deltaTime);
}
