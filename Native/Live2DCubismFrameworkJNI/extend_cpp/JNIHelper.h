#pragma once

#ifndef JNI_HELPER
#define JNI_HELPER

#if PLATFORM_ANDROID
#define AttachCurrentThread AttachCurrentThread(&env, NULL)
#else
#define AttachCurrentThread AttachCurrentThread((void**)&env, NULL)
#endif

#endif