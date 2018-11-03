#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_id_CubismIdJNI.h"
#include <Id/CubismId.hpp>

using namespace Live2D::Cubism::Framework;

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_id_CubismIdJNI
 * Method:    GetString
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_id_CubismIdJNI_GetString
(JNIEnv * env, jclass obj, jlong id) {
	csmString string = ((CubismId*)id)->GetString();
	return env->NewString((jchar*) string.GetRawString(), string.GetLength());
}
