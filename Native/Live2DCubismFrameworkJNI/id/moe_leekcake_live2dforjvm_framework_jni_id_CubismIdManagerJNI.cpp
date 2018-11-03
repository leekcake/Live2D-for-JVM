#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI.h"

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    RegisterIds
 * Signature: (J[Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_RegisterIds
  (JNIEnv *, jclass, jlong, jobjectArray);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    RegisterId
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_RegisterId
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    GetId
 * Signature: (JLjava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_GetId
  (JNIEnv *, jclass, jlong, jstring);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI
 * Method:    IsExist
 * Signature: (JLjava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI_IsExist
  (JNIEnv *, jclass, jlong, jstring);