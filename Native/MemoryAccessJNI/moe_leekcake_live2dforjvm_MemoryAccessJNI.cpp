/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class moe_leekcake_live2dforjvm_MemoryAccessJNI */

#ifndef _Included_moe_leekcake_live2dforjvm_MemoryAccessJNI
#define _Included_moe_leekcake_live2dforjvm_MemoryAccessJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    allocate
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_MemoryAccessJNI_allocate
(JNIEnv * env, jclass obj, jint size) {
	return (jlong)malloc(size);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    allocateAligned
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_MemoryAccessJNI_allocateAligned
(JNIEnv * env, jclass obj, jint size, jint alignment) {
	size_t offset, shift, alignedAddress;
	void *allocation, ** preamble;


	offset = alignment - 1 + sizeof(void*);


	allocation = malloc(size + (unsigned int)offset);


	alignedAddress = (size_t)allocation + sizeof(void*);


	shift = alignedAddress % alignment;


	if (shift)
	{
		alignedAddress += (alignment - shift);
	}


	preamble = (void**)alignedAddress;
	preamble[-1] = allocation;


	return alignedAddress;
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    writeToMemory
 * Signature: (JI[BII)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_MemoryAccessJNI_writeToMemory
(JNIEnv * env, jclass obj, jlong memory, jint start, jbyteArray data, jint dataStart, jint dataLength) {
	jbyte* bufferPtr = env->GetByteArrayElements(data, NULL);

	memcpy(((char *)memory) + start, bufferPtr + dataStart, dataLength);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    deAllocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_MemoryAccessJNI_deAllocate
(JNIEnv * env, jclass obj, jlong memory) {
	free((void*)memory);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    deAllocateAligned
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_MemoryAccessJNI_deAllocateAligned
(JNIEnv * env, jclass obj, jlong memory) {
	void **preamble;
	preamble = (void**)memory;
	free(preamble[-1]);
}

#ifdef __cplusplus
}
#endif
#endif