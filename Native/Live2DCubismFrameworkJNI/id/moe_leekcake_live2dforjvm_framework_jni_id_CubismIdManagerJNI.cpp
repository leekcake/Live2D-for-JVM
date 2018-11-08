#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI.h"
#include <Id/CubismIdManager.hpp>

using namespace Live2D::Cubism::Framework;

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    RegisterIds
 * Signature: (J[Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_RegisterIds
(JNIEnv * env, jclass obj, jlong im, jobjectArray strings) {
	csmVector<csmString> vector;
	jsize len = env->GetArrayLength(strings);
	for (int i = 0; i < len; i++) {
		jstring strObj = (jstring) env->GetObjectArrayElement(strings, i);
		const char *rawString = env->GetStringUTFChars(strObj, NULL);
		vector.PushBack(csmString(rawString));
		env->ReleaseStringUTFChars(strObj, rawString);
	}
	((CubismIdManager*)im)->RegisterIds(vector);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    RegisterId
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_RegisterId
(JNIEnv * env, jclass obj, jlong im, jstring str) {
	const char *rawString = env->GetStringUTFChars(str, NULL);
	((CubismIdManager*)im)->RegisterId(csmString(rawString));
	env->ReleaseStringUTFChars(str, rawString);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    GetId
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_GetId
(JNIEnv * env, jclass obj, jlong im, jstring str) {
	const char *rawString = env->GetStringUTFChars(str, false);
	jlong result = (jlong) ((CubismIdManager*)im)->GetId(rawString);
	env->ReleaseStringUTFChars(str, rawString);
	return result;
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    IsExist
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_IsExist
(JNIEnv * env, jclass obj, jlong im, jstring str) {
	const char *rawString = env->GetStringUTFChars(str, false);
	jboolean result = (jboolean)((CubismIdManager*)im)->IsExist(rawString);
	env->ReleaseStringUTFChars(str, rawString);
	return result;
}