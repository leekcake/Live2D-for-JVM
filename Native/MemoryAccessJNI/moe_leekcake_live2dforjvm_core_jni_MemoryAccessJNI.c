#include "moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI.h"
#include <stdlib.h>
#include <string.h>
/* Header for class moe_leekcake_live2dforjvm_MemoryAccessJNI */

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    allocate
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI_allocate
(JNIEnv * env, jclass obj, jint size) {
	return (jlong)malloc(size);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    allocateAligned
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI_allocateAligned
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
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI_writeToMemory
(JNIEnv * env, jclass obj, jlong memory, jint start, jbyteArray data, jint dataStart, jint dataLength) {
	jbyte* bufferPtr = (*env)->GetByteArrayElements(env, data, NULL);

	memcpy(((char *)memory) + start, bufferPtr + dataStart, dataLength);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    deAllocate
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI_deAllocate
(JNIEnv * env, jclass obj, jlong memory) {
	free((void*)memory);
}

/*
 * Class:     moe_leekcake_live2dforjvm_MemoryAccessJNI
 * Method:    deAllocateAligned
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI_deAllocateAligned
(JNIEnv * env, jclass obj, jlong memory) {
	void **preamble;
	preamble = (void**)memory;
	free(preamble[-1]);
}
