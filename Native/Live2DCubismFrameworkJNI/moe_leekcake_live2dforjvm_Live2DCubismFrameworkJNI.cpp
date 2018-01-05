#include "moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI.h"
#include <Live2DCubismFramework.h>

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getHashTableParameterCount
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getHashTableParameterCount
(JNIEnv * env, jclass obj, jlong table) {
	return ((csmModelHashTable*)table)->Parameters.Count;
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getHashTableParameterHash
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getHashTableParameterHash
(JNIEnv * env, jclass obj, jlong table, jint inx) {
	return ((csmModelHashTable*)table)->Parameters.IdHashes[inx];
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getHashTablePartCount
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getHashTablePartCount
(JNIEnv * env, jclass obj, jlong table) {
	return ((csmModelHashTable*)table)->Parts.Count
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getHashTablePartHash
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getHashTablePartHash
(JNIEnv * env, jclass obj, jlong table, jint inx) {
	return ((csmModelHashTable*)table)->Parts.IdHashes[inx];
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    generateHashId
* Signature: (Ljava/lang/String;)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_generateHashId
(JNIEnv * env, jclass obj, jstring id) {
	return csmHashId(env->GetStringUTFChars(id, false));
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getSizeofModelHashTable
* Signature: (J)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getSizeofModelHashTable
(JNIEnv * env, jclass obj, jlong model) {
	return csmGetSizeofModelHashTable((const csmModel*)model);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    initializeModelHashTableInPlace
* Signature: (JJJ)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_initializeModelHashTableInPlace
(JNIEnv * env, jclass obj, jlong model, jlong address, jlong size) {
	return (jlong)csmInitializeModelHashTableInPlace((const csmModel*)model, (void*)address, size);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    findParameterIndexByHash
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_findParameterIndexByHash
(JNIEnv * env, jclass obj, jlong model, jint hash) {
	return csmFindParameterIndexByHash((const csmModel*)model, hash);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    findParameterIndexByHashFAST
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_findParameterIndexByHashFAST
(JNIEnv * env, jclass obj, jlong table, jint hash) {
	return csmFindParameterIndexByHashFAST((const csmModelHashTable*)table, hash);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    findPartIndexByHash
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_findPartIndexByHash
(JNIEnv * env, jclass obj, jlong model, jint hash) {
	return csmFindPartIndexByHash((const csmModel*)model, hash);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    csmFindPartIndexByHashFAST
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_csmFindPartIndexByHashFAST
(JNIEnv * env, jclass obj, jlong table, jint hash) {
	return csmFindPartIndexByHashFAST((const csmModelHashTable*)table, hash);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    csmFindDrawableIndexByHash
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_csmFindDrawableIndexByHash
(JNIEnv * env, jclass obj, jlong model, jint hash) {
	return csmFindDrawableIndexByHash((const csmModel*)model, hash);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    doesModelUseMasks
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_doesModelUseMasks
(JNIEnv * env, jclass obj, jlong model) {
	return csmDoesModelUseMasks((const csmModel*)model);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    initializeAnimationState
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_initializeAnimationState
(JNIEnv * env, jclass obj, jlong state) {
	csmInitializeAnimationState((csmAnimationState*)state);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    resetAnimationState
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_resetAnimationState
(JNIEnv * env, jclass obj, jlong state) {
	csmResetAnimationState((csmAnimationState*)state);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    updateAnimationState
* Signature: (JF)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_updateAnimationState
(JNIEnv * env, jclass obj, jlong state, jfloat deltaTime) {
	csmUpdateAnimationState((csmAnimationState*)state, deltaTime);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getDeserializedSizeofAnimation
* Signature: (Ljava/lang/String;)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getDeserializedSizeofAnimation
(JNIEnv * env, jclass obj, jstring json) {
	return csmGetDeserializedSizeofAnimation(env->GetStringUTFChars(json, false));
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    deserializeAnimationInPlace
* Signature: (Ljava/lang/String;JJ)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_deserializeAnimationInPlace
(JNIEnv * env, jclass obj, jstring json, jlong address, jlong size) {
	return (jlong)csmDeserializeAnimationInPlace(env->GetStringUTFChars(json, false), (void*)address, size);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    csmEvaluateAnimationFAST
* Signature: (JJJFJJJJ)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_csmEvaluateAnimationFAST
(JNIEnv * env, jclass obj, jlong animation, jlong state, jlong blend,
	jfloat weight, jlong model, jlong table, jlong handleModelCurve, jlong userData) {
	csmEvaluateAnimationFAST((const csmAnimation*)animation,
		(const csmAnimationState*)state,
		(const csmFloatBlendFunction)blend,
		weight,
		(csmModel*)model,
		(const csmModelHashTable*)table,
		(csmModelAnimationCurveHandler*)handleModelCurve,
		(void*)userData);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    getDeserializedSizeofPhysics
* Signature: (Ljava/lang/String;)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_getDeserializedSizeofPhysics
(JNIEnv * env, jclass obj, jstring json) {
	return csmGetDeserializedSizeofPhysics(env->GetStringUTFChars(json, false));
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    deserializePhysicsInPlace
* Signature: (Ljava/lang/String;JJ)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_deserializePhysicsInPlace
(JNIEnv * env, jclass obj, jstring json, jlong address, jlong size) {
	return (jlong)csmDeserializePhysicsInPlace(env->GetStringUTFChars(json, false), (void*)address, size);
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI
* Method:    csmPhysicsEvaluate
* Signature: (JJJF)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI_csmPhysicsEvaluate
(JNIEnv * env, jclass obj, jlong model, jlong physics, jlong options, jfloat deltaTime) {
	csmPhysicsEvaluate((csmModel*)model, (csmPhysicsRig*)physics, (csmPhysicsOptions*)options, deltaTime);
}