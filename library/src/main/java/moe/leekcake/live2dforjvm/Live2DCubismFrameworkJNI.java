package moe.leekcake.live2dforjvm;

public class Live2DCubismFrameworkJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
    }

    //Blend Function Read Proxy for Java
    public static native long getOverrideFloatBlendFunction();

    public static native long getAdditiveFloatBlendFunction();

    //Physics Options Read Proxy for Java
    public static native long allocatePhysicsOptions();

    //Animation State Read Proxy for Java
    public static native long allocateAnimationState();

    public static native float getAnimationStateTime(long state);

    /**
     * @return pointer of Gravity Vector2
     */
    public static native long getGravitiyPhysicsOptions(long options);

    /**
     * @return pointer of Wind Vector2
     */
    public static native long getWindPhysicsOptions(long options);

    //Model Hashtable Read Proxy for Java

    /**
     * Get Parameter Count from Model Hashtable
     *
     * @param table Hashtable pointer
     * @return Parameter Count
     */
    public static native int getHashTableParameterCount(long table);

    /**
     * Get Parameter Hash from Model Hashtable
     *
     * @param table Hashtable pointer
     * @param inx   Index to access
     * @return Parameter Hash
     */
    public static native int getHashTableParameterHash(long table, int inx);

    /**
     * Get Part Count from Model Hashtable
     *
     * @param table Hashtable pointer
     * @return Part Count
     */
    public static native int getHashTablePartCount(long table);

    /**
     * Get Part Hash from Model Hashtable
     *
     * @param table Hashtable pointer
     * @param inx   Index to access
     * @return Part Hash
     */
    public static native int getHashTablePartHash(long table, int inx);

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
    public static native int findPartIndexByHashFAST(long table, int hash);

    /**
     * Finds index of a drawable.
     *
     * @param model Model to query against.
     * @param hash  Drawable ID hash.
     * @return Valid index on success; '-1' otherwise.
     */
    public static native int findDrawableIndexByHash(long model, int hash);

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
     *
     * @param animation        Animation to evaluate.
     * @param state            Animation state.
     * @param blend            Blend function to use for filling sink.
     * @param weight           Blend weight factor.
     * @param model            Model to apply results to.
     * @param table            Model table to user for look-ups.
     * @param handleModelCurve [Optional] Model curve handler.
     * @param userData         [Optional] Data to pass to model curve handler.
     */
    public static native void evaluateAnimationFAST(long animation,
                                                    long state,
                                                    long blend,
                                                    float weight,
                                                    long model,
                                                    long table,
                                                    long handleModelCurve,
                                                    long userData);

    /**
     * Gets the deserialized size of a serialized physics in bytes.
     *
     * @param physicsJson Serialized physics to query for.
     * @return Number of bytes necessary.
     */
    public static native long getDeserializedSizeofPhysics(String physicsJson);

    /**
     * Deserializes physics.
     *
     * @param physicsJson Serialized physics.
     * @param address     Address to place deserialized animation at.
     * @param size        Size of passed memory block (in bytes).
     * @return Valid pointer on success; '0' otherwise.
     */
    public static native long deserializePhysicsInPlace(String physicsJson, long address, long size);

    /**
     * Evaluates physics.
     *
     * @param model     Model to apply result to.
     * @param physics   Physics to evaluate.
     * @param options   Options of evaluation.
     * @param deltaTime Time passed since last tick?
     */
    public static native void physicsEvaluate(long model,
                                              long physics,
                                              long options,
                                              float deltaTime);
}
