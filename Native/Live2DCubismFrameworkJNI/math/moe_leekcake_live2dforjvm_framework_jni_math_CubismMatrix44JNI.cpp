#include <jni.h>
#include "moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI.h"

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Construct
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Construct
  (JNIEnv *, jclass);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Deconstruct
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Deconstruct
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    LoadIdentity
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_LoadIdentity
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetArray
 * Signature: (J)[F
 */
JNIEXPORT jfloatArray JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetArray
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    SetMatrix
 * Signature: (J[F)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_SetMatrix
  (JNIEnv *, jclass, jlong, jfloatArray);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetScaleX
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetScaleX
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetScaleY
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetScaleY
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetTranslateX
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetTranslateX
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    GetTranslateY
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_GetTranslateY
  (JNIEnv *, jclass, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TransformX
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TransformX
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TransformY
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TransformY
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    InvertTransformX
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_InvertTransformX
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    InvertTransformY
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_InvertTransformY
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateRelative
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateRelative
  (JNIEnv *, jclass, jlong, jfloat, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Translate
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Translate
  (JNIEnv *, jclass, jlong, jfloat, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateX
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateX
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    TranslateY
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_TranslateY
  (JNIEnv *, jclass, jlong, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    ScaleRelative
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_ScaleRelative
  (JNIEnv *, jclass, jlong, jfloat, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    Scale
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_Scale
  (JNIEnv *, jclass, jlong, jfloat, jfloat);

/*
 * Class:     moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI
 * Method:    MultiplyByMatrix
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI_MultiplyByMatrix
  (JNIEnv *, jclass, jlong, jlong);