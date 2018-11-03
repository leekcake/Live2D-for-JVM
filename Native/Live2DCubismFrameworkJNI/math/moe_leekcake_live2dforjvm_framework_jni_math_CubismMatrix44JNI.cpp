#include <jni.h>
#include <Math/CubismMatrix44.hpp>
#include "moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI.h"

using namespace Live2D::Cubism::Framework;

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Construct
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Construct
(JNIEnv * env, jclass obj) {
	return (jlong) new CubismMatrix44();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Deconstruct
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Deconstruct
(JNIEnv * env, jclass obj, jlong matrix) {
	delete (CubismMatrix44*)matrix;
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    LoadIdentity
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_LoadIdentity
(JNIEnv * env, jclass obj, jlong matrix) {
	((CubismMatrix44*)matrix)->LoadIdentity();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetArray
 * Signature: (J)[F
 */
JNIEXPORT jfloatArray JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetArray
(JNIEnv * env, jclass obj, jlong matrix) {
	jfloatArray result = env->NewFloatArray(16);
	csmFloat32* arr = ((CubismMatrix44*)matrix)->GetArray();
	env->SetFloatArrayRegion(result, 0, 16, arr);
	return result;
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    SetMatrix
 * Signature: (J[F)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_SetMatrix
(JNIEnv * env, jclass obj, jlong matrix, jfloatArray arr) {
	((CubismMatrix44*)matrix)->SetMatrix(env->GetFloatArrayElements(arr, false));
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetScaleX
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetScaleX
(JNIEnv * env, jclass obj, jlong matrix) {
	return ((CubismMatrix44*)matrix)->GetScaleX();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetScaleY
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetScaleY
(JNIEnv * env, jclass obj, jlong matrix) {
	return ((CubismMatrix44*)matrix)->GetScaleY();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetTranslateX
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetTranslateX
(JNIEnv * env, jclass obj, jlong matrix) {
	return ((CubismMatrix44*)matrix)->GetTranslateX();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetTranslateY
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetTranslateY
(JNIEnv * env, jclass obj, jlong matrix) {
	return ((CubismMatrix44*)matrix)->GetTranslateY();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TransformX
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TransformX
(JNIEnv * env, jclass obj, jlong matrix, jfloat x) {
	((CubismMatrix44*)matrix)->TransformX(x);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TransformY
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TransformY
(JNIEnv * env, jclass obj, jlong matrix, jfloat y) {
	((CubismMatrix44*)matrix)->TransformY(y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    InvertTransformX
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_InvertTransformX
(JNIEnv * env, jclass obj, jlong matrix, jfloat x) {
	((CubismMatrix44*)matrix)->InvertTransformX(x);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    InvertTransformY
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_InvertTransformY
(JNIEnv * env, jclass obj, jlong matrix, jfloat y) {
	((CubismMatrix44*)matrix)->InvertTransformY(y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateRelative
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateRelative
(JNIEnv * env, jclass obj, jlong matrix, jfloat x, jfloat y) {
	((CubismMatrix44*)matrix)->TranslateRelative(x, y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Translate
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Translate
(JNIEnv * env, jclass obj, jlong matrix, jfloat x, jfloat y) {
	((CubismMatrix44*)matrix)->Translate(x, y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateX
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateX
(JNIEnv * env, jclass obj, jlong matrix, jfloat x) {
	((CubismMatrix44*)matrix)->TransformX(x);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateY
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateY
(JNIEnv * env, jclass obj, jlong matrix, jfloat y) {
	((CubismMatrix44*)matrix)->TranslateY(y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    ScaleRelative
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_ScaleRelative
(JNIEnv * env, jclass obj, jlong matrix, jfloat x, jfloat y) {
	((CubismMatrix44*)matrix)->ScaleRelative(x, y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Scale
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Scale
(JNIEnv * env, jclass obj, jlong matrix, jfloat x, jfloat y) {
	((CubismMatrix44*)matrix)->Scale(x, y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    MultiplyByMatrix
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_MultiplyByMatrix
(JNIEnv * env, jclass obj, jlong matrix, jlong anotherMatrix) {
	((CubismMatrix44*)matrix)->MultiplyByMatrix(((CubismMatrix44*)anotherMatrix));
}