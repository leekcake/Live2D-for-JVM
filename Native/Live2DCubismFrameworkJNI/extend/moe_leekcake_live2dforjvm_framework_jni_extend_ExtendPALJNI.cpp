#include <jni.h>
#include <JavaPal.hpp>
#include "moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI.h"

using namespace std;
static JavaVM* vm;

static double JavaPal_GetTime() {
	JNIEnv* env;
	jint rs = vm->AttachCurrentThread((void**)&env, NULL);

	jclass callbackClass = env->FindClass("java/lang/System");
	jmethodID callbackMethodId = env->GetStaticMethodID(callbackClass, "currentTimeMillis", "()J");

	long time = env->CallLongMethod(callbackClass, callbackMethodId);
	return time / (double)1000;
}

static unsigned char* JavaPal_GetData(string path, Csm::csmSizeInt* outSize) {
	JNIEnv* env;
	jint rs = vm->AttachCurrentThread((void**)&env, NULL);

	jclass callbackClass = env->FindClass("moe/leekcake/live2dforjvm/framework/jni/extend/ExtendPALJNI");
	jmethodID callbackMethodId = env->GetStaticMethodID(callbackClass, "loadFileAsBytesProxy", "(Ljava/lang/String;)[B");

	jstring str = env->NewStringUTF(path.c_str());
	jbyteArray arr = (jbyteArray)env->CallStaticObjectMethod(callbackClass, callbackMethodId, str);

	int len = env->GetArrayLength(arr);
	unsigned char* buf = new unsigned char[len];
	env->GetByteArrayRegion(arr, 0, len, reinterpret_cast<jbyte*>(buf));

	*outSize = len;

	return buf;
}

static int JavaPal_GetTexture(const char* path) {
	JNIEnv* env;
	jint rs = vm->AttachCurrentThread((void**)&env, NULL);

	jclass callbackClass = env->FindClass("moe/leekcake/live2dforjvm/framework/jni/extend/ExtendPALJNI");
	jmethodID callbackMethodId = env->GetStaticMethodID(callbackClass, "getTextureProxy", "(Ljava/lang/String;)I");

	return env->CallStaticIntMethod(callbackClass, callbackMethodId, env->NewStringUTF(path));
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI
 * Method:    bindProxy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI_bindProxy
(JNIEnv * env, jclass obj) {
	env->GetJavaVM(&vm);

	JavaPal::SetJavaPALDataFunction(JavaPal_GetData);
	JavaPal::SetJavaPALTextureFunction(JavaPal_GetTexture);
	JavaPal::SetJavaPALTimeFunction(JavaPal_GetTime);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI
 * Method:    updateTime
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI_updateTime
(JNIEnv *, jclass) {
	JavaPal::UpdateTime();
}

