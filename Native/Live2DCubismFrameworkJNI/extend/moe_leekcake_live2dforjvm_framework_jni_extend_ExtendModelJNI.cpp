#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI.h"
#include <JavaModel.hpp>
#include <Math/CubismMatrix44.hpp>
#include <JNIHelper.h>

using namespace Live2D::Cubism::Framework;

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Construct
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Construct
(JNIEnv * env, jclass obj) {
	return (jlong) new JavaModel();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Deconstruct
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Deconstruct
(JNIEnv * env, jclass obj, jlong model) {
	return delete (JavaModel*)model;
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    LoadAssets
 * Signature: (JLjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_LoadAssets
(JNIEnv * env, jclass obj, jlong model, jstring baseDir, jstring fn) {
	const char* baseDirChar = env->GetStringUTFChars(baseDir, NULL);
	const char* fnChar = env->GetStringUTFChars(fn, NULL);
	((JavaModel*)model)->LoadAssets(baseDirChar, fnChar);
	env->ReleaseStringUTFChars(baseDir, baseDirChar);
	env->ReleaseStringUTFChars(fn, fnChar);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    ReloadRenderer
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_ReloadRenderer
(JNIEnv * env, jclass obj, jlong model) {
	((JavaModel*)model)->ReloadRnederer();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Update
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Update
(JNIEnv * env, jclass obj, jlong model) {
	((JavaModel*)model)->Update();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Draw
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Draw
(JNIEnv * env, jclass obj, jlong model, jlong matrix) {
	((JavaModel*)model)->Draw(*((CubismMatrix44*)matrix));
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    StartMotion
 * Signature: (JLjava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_StartMotion
(JNIEnv * env, jclass obj, jlong model, jstring group, jint no, jint priority) {
	const char* groupChar = env->GetStringUTFChars(group, NULL);

	((JavaModel*)model)->StartMotion(groupChar, no, priority);

	env->ReleaseStringUTFChars(group, groupChar);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    StartRandomMotion
 * Signature: (JLjava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_StartRandomMotion
(JNIEnv * env, jclass obj, jlong model, jstring group, jint no) {
	const char* groupChar = env->GetStringUTFChars(group, NULL);

	((JavaModel*)model)->StartRandomMotion(groupChar, no);

	env->ReleaseStringUTFChars(group, groupChar);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetExpression
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetExpression
(JNIEnv * env, jclass obj, jlong model, jstring expressionID) {
	const char* expressionIDChar = env->GetStringUTFChars(expressionID, NULL);

	((JavaModel*)model)->SetExpression(expressionIDChar);

	env->ReleaseStringUTFChars(expressionID, expressionIDChar);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetRandomExpression
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetRandomExpression
(JNIEnv * env, jclass obj, jlong model) {
	((JavaModel*)model)->SetRandomExpression();
}

struct MotionEventCallbackData {
	JavaVM* vm;
	jclass callbackCls;
	jmethodID callbackMethodID;
	jobject callback;
};

void MotionEventCallback(void* data, const char* value) {
	MotionEventCallbackData* callbackData = (MotionEventCallbackData*)data;
	JNIEnv* env;
	jint rs = callbackData->vm->AttachCurrentThread;

	jstring str = env->NewStringUTF(value);
	env->CallVoidMethod(callbackData->callbackCls, callbackData->callbackMethodID, str);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    RegisterMotionEventCallback
 * Signature: (JLmoe/leekcake/live2dforjvm/framework/jni/extend/ExtendModelJNI/MotionEventFireable;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_RegisterMotionEventCallback
(JNIEnv * env, jclass obj, jlong model, jobject callback) {
	MotionEventCallbackData* data = new MotionEventCallbackData();
	env->GetJavaVM(&data->vm);
	data->callbackCls = env->GetObjectClass(callback);
	data->callbackMethodID = env->GetMethodID(data->callbackCls, "OnMotionEventFired", "(Ljava/lang/String;)V");
	data->callback = env->NewGlobalRef(callback);
	((JavaModel*)model)->SetMotionEventHandler(MotionEventCallback, callback);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    HitTest
 * Signature: (JFF)Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_HitTest
(JNIEnv * env, jclass obj, jlong model, jstring hitAreaName, jfloat x, jfloat y) {
	const char* hitAreaChar = env->GetStringUTFChars(hitAreaName, NULL);
	jboolean result = ((JavaModel*)model)->HitTest(hitAreaChar, x, y);
	env->ReleaseStringUTFChars(hitAreaName, hitAreaChar);
	return result;
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetDragging
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetDragging
(JNIEnv * env, jclass obj, jlong model, jfloat x, jfloat y) {
	((JavaModel*)model)->SetDragging(x, y);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetAcceleration
 * Signature: (JFFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetAcceleration
(JNIEnv * env, jclass obj, jlong model, jfloat x, jfloat y, jfloat z) {
	((JavaModel*)model)->SetAcceleration(x, y, z);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetOpacity
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetOpacity
(JNIEnv * env, jclass obj, jlong model, jfloat opacity) {
	((JavaModel*)model)->SetOpacity(opacity);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    GetOpacity
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_GetOpacity
(JNIEnv * env, jclass obj, jlong model) {
	return ((JavaModel*)model)->GetOpacity();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    IsInMotion
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_IsInMotion
(JNIEnv * env, jclass obj, jlong model) {
	return ((JavaModel*)model)->IsInMotion();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    GetModelMatrix
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_GetModelMatrix
(JNIEnv * env, jclass obj, jlong model) {
	return (long)((JavaModel*)model)->GetModelMatrix();
}
