#include "moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.h"
#include <Live2DCubismCore.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismCoreJNI
* Method:    allocateVector2
* Signature: (FF)J
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_allocateVector2
(JNIEnv * env, jclass obj, jfloat x, jfloat y) {
	csmVector2* vector = new csmVector2();
	vector->X = x;
	vector->Y = y;
	return (jlong)vector;
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismCoreJNI
* Method:    getVector2X
* Signature: (J)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getVector2X
(JNIEnv * env, jclass obj, jlong vector) {
	return ((csmVector2*)vector)->X;
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismCoreJNI
* Method:    getVector2Y
* Signature: (J)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getVector2Y
(JNIEnv * env, jclass obj, jlong vector) {
	return ((csmVector2*)vector)->Y;
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismCoreJNI
* Method:    setVector2X
* Signature: (JF)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_setVector2X
(JNIEnv * env, jclass obj, jlong vector, jfloat value) {
	((csmVector2*)vector)->X = value;
}

/*
* Class:     moe_leekcake_live2dforjvm_Live2DCubismCoreJNI
* Method:    setVector2Y
* Signature: (JF)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_setVector2Y
(JNIEnv * env, jclass obj, jlong vector, jfloat value) {
	((csmVector2*)vector)->Y = value;
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    GetVersion
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getVersion
(JNIEnv * env, jclass obj) {
	return csmGetVersion();
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    ReviveMocInPlace
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_reviveMocInPlace
(JNIEnv * env, jclass obj, jlong address, jlong size) {
	return (jlong)csmReviveMocInPlace((void*)address, size);
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    GetSizeofModel
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getSizeofModel
(JNIEnv * env, jclass obj, jlong moc) {
	return csmGetSizeofModel((const csmMoc*)moc);
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    InitializeModelInPlace
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_initializeModelInPlace
(JNIEnv * env, jclass obj, jlong moc, jlong address, jlong size) {
	return (jlong)csmInitializeModelInPlace((const csmMoc*)moc, (void*)address, size);
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    UpdateModel
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_updateModel
(JNIEnv * env, jclass obj, jlong model) {
	csmUpdateModel((csmModel*)model);
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    ReadCanvasInfo
 * Signature: (JLmoe/leekcake/live2d_for_java/Live2DType/CSMVector2;Lmoe/leekcake/live2d_for_java/Live2DType/CSMVector2;)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_readCanvasInfo
(JNIEnv * env, jclass obj, jlong model, jlong size, jlong origin) {
	float perUnit;
	csmReadCanvasInfo((csmModel*)model, (csmVector2*)size, (csmVector2*)origin, &perUnit);
	return perUnit;
}

/*
 * Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
 * Method:    GetParameterCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterCount
(JNIEnv * env, jclass obj, jlong model) {
	return csmGetParameterCount((const csmModel*)model);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetParameterId
* Signature: (JI)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterId
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return env->NewStringUTF(csmGetParameterIds((const csmModel*)model)[inx]);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetParameterMinimumValue
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterMinimumValue
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetParameterMinimumValues((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetParameterMaximumValue
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterMaximumValue
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetParameterMaximumValues((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetParameterDefaultValue
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterDefaultValue
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetParameterDefaultValues((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetParameterValue
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getParameterValue
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetParameterValues((csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetPartCount
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getPartCount
(JNIEnv * env, jclass obj, jlong model) {
	return csmGetPartCount((const csmModel*)model);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetPartId
* Signature: (JI)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getPartId
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return env->NewStringUTF(csmGetPartIds((const csmModel*)model)[inx]);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetPartOpacity
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getPartOpacity
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetPartOpacities((csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableCount
* Signature: (J)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableCount
(JNIEnv * env, jclass obj, jlong model) {
	return csmGetDrawableCount((const csmModel*)model);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableId
* Signature: (JI)Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableId
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return env->NewStringUTF(csmGetDrawableIds((const csmModel*)model)[inx]);
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableConstantFlag
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableConstantFlag
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableConstantFlags((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableDynamicFlag
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableDynamicFlag
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableDynamicFlags((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableTextureIndex
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableTextureIndex
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableTextureIndices((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableDrawOrder
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableDrawOrder
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableDrawOrders((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableRenderOrder
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableRenderOrder
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableRenderOrders((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableOpacity
* Signature: (JI)F
*/
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableOpacity
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableOpacities((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableMaskCount
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableMaskCount
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableMaskCounts((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableMask
* Signature: (JII)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableMask
(JNIEnv * env, jclass obj, jlong model, jint inx, jint maskInx) {
	return csmGetDrawableMasks((const csmModel*)model)[inx][maskInx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableVertexCount
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableVertexCount
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableVertexCounts((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableVertexPosition
* Signature: (JII)Lmoe/leekcake/live2d_for_java/Live2DType/CSMVector2;
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableVertexPosition
(JNIEnv * env, jclass obj, jlong model, jint inx, jint vertexInx, jlong out) {
	return (jlong)&csmGetDrawableVertexPositions((const csmModel*)model)[inx][vertexInx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableVertexUv
* Signature: (JII)Lmoe/leekcake/live2d_for_java/Live2DType/CSMVector2;
*/
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableVertexUv
(JNIEnv * env, jclass obj, jlong model, jint inx, jint vertexInx, jobject out) {
	return (jlong)&csmGetDrawableVertexUvs((const csmModel*)model)[inx][vertexInx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableIndexCount
* Signature: (JI)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableIndexCount
(JNIEnv * env, jclass obj, jlong model, jint inx) {
	return csmGetDrawableIndexCounts((const csmModel*)model)[inx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    GetDrawableIndex
* Signature: (JII)I
*/
JNIEXPORT jint JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_getDrawableIndex
(JNIEnv * env, jclass obj, jlong model, jint inx, jint Indexinx) {
	return csmGetDrawableIndices((const csmModel*)model)[inx][Indexinx];
}

/*
* Class:     moe_leekcake_live2d_for_java_Live2DCubismCoreJNI
* Method:    ResetDrawableDynamicFlags
* Signature: (J)V
*/
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_Live2DCubismCoreJNI_resetDrawableDynamicFlags
(JNIEnv * env, jclass obj, jlong model) {
	return csmResetDrawableDynamicFlags((csmModel*)model);
}
