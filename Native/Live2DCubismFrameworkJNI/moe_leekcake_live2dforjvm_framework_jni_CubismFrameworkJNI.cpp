#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI.h"
#include <CubismFramework.hpp>
#include <JavaAllocator.hpp>
#include <Live2DCubismCore.hpp>

using namespace Live2D::Cubism::Framework;

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    StartUp
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_StartUp
(JNIEnv * env, jclass obj) {
	CubismFramework::Option* option = new CubismFramework::Option();
	option->LogFunction = Live2D::Cubism::Core::csmGetLogFunction();
	option->LoggingLevel = CubismFramework::Option::LogLevel_Verbose;
	return CubismFramework::StartUp(new JavaAllocator(), option);
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    CleanUp
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_CleanUp
(JNIEnv * env, jclass obj) {
	CubismFramework::CleanUp();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    IsStarted
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_IsStarted
(JNIEnv * env, jclass obj) {
	return CubismFramework::IsStarted();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    Initialize
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_Initialize
(JNIEnv * env, jclass obj) {
	CubismFramework::Initialize();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    Dispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_Dispose
(JNIEnv * env, jclass obj) {
	CubismFramework::Dispose();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    IsInitialized
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_IsInitialized
(JNIEnv * env, jclass obj) {
	return CubismFramework::IsInitialized();
}

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI
 * Method:    GetIdManager
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI_GetIdManager
(JNIEnv * env, jclass obj) {
	return (jlong) CubismFramework::GetIdManager();
}