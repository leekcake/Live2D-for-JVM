#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI.h"

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Construct
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Construct
  (JNIEnv *, jclass);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Deconstruct
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Deconstruct
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    LoadAssets
 * Signature: (JLjava/lang/String;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_LoadAssets
  (JNIEnv *, jclass, jlong, jstring, jstring);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    ReloadRenderer
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_ReloadRenderer
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Update
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Update
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    Draw
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_Draw
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    StartMotion
 * Signature: (JLjava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_StartMotion
  (JNIEnv *, jclass, jlong, jstring, jint, jint);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    StartRandomMotion
 * Signature: (JLjava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_StartRandomMotion
  (JNIEnv *, jclass, jlong, jstring, jint);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetExpression
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetExpression
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    SetRandomExpression
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_SetRandomExpression
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    RegisterMotionEventCallback
 * Signature: (JLmoe/leekcake/live2dforjvm/framework/jni/extend/ExtendModelJNI/MotionEventFireable;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_RegisterMotionEventCallback
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI
 * Method:    HitTest
 * Signature: (JFF)Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI_HitTest
  (JNIEnv *, jclass, jlong, jfloat, jfloat);