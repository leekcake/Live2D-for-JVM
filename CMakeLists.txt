#1. Define Project
CMAKE_MINIMUM_REQUIRED ( VERSION 3.6 )
PROJECT ("Live2D-for-JVM")

#Use JNI
if(NOT ANDROID)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
endif()

if(NOT ANDROID AND NOT EMSCRIPTEN AND NOT IOS AND NOT RPI)
	set(IS_DESKTOP TRUE)
else()
	set(IS_DESKTOP FALSE)
endif()


if(${IS_DESKTOP})
	find_package(OpenGL REQUIRED)
endif()

if(WIN32)
	#TODO: enable when it desktop
	
	set(FRAMEWORK_DEFINITIOINS
		-DCSM_TARGET_WIN_GL
		-DGLEW_STATIC
		-DGLEW_ENABLED
	)

	add_definitions(${FRAMEWORK_DEFINITIOINS})
	
	# Visual Studioのソリューション生成
	if (CMAKE_GENERATOR_PLATFORM STREQUAL "x64" AND CMAKE_GENERATOR MATCHES "Visual Studio 14 2015")
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86_64/140)

	elseif (CMAKE_GENERATOR_PLATFORM STREQUAL "x64" AND CMAKE_GENERATOR MATCHES "Visual Studio 12 2013")
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86_64/120)

	elseif (CMAKE_GENERATOR_PLATFORM STREQUAL "Win32" AND CMAKE_GENERATOR MATCHES "Visual Studio 14 2015")
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86/140)

	elseif (CMAKE_GENERATOR_PLATFORM STREQUAL "Win32" AND CMAKE_GENERATOR MATCHES "Visual Studio 12 2013")
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86/120)

	# NMake MakeFiles指定
	elseif (CMAKE_GENERATOR MATCHES "NMake Makefiles" AND MSVC_VERSION MATCHES 1900 AND CMAKE_EXE_LINKER_FLAGS STREQUAL "/machine:x64" )
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86_64/140)

	elseif (CMAKE_GENERATOR MATCHES "NMake Makefiles" AND MSVC_VERSION MATCHES 1900 )
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86/140)

	elseif (CMAKE_GENERATOR MATCHES "NMake Makefiles" AND MSVC_VERSION MATCHES 1800 AND CMAKE_EXE_LINKER_FLAGS STREQUAL "/machine:x64" )
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86_64/120)

	elseif (CMAKE_GENERATOR MATCHES "NMake Makefiles" AND MSVC_VERSION MATCHES 1800 )
		link_directories(${CMAKE_SOURCE_DIR}/../../../../Core/lib/windows/x86/120)
	endif () 
	
	#glew_s
	add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glew-2.1.0/build/cmake)
	#glfw
	add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glfw-3.2.1)
endif()

set(FRAMEWORK_GLFW_PATH ${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glfw-3.2.1/include)
set(FRAMEWORK_GLEW_PATH ${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glew-2.1.0/include)

if(ANDROID)
	add_definitions(-D CSM_TARGET_ANDROID_ES2)
	add_definitions(-D PLATFORM_ANDROID)
endif()

#Define Live2DCubismJNI Source
set(CoreJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismCoreJNI)
set(FrameworkJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismFrameworkJNI)
set(Live2DCubismJNISource
	${CoreJNI}/moe_leekcake_live2dforjvm_core_jni_Live2DCubismCoreJNI.c
	${CoreJNI}/moe_leekcake_live2dforjvm_core_jni_Live2DCubismCoreJNI.h
	
	${FrameworkJNI}/moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI.cpp
	${FrameworkJNI}/moe_leekcake_live2dforjvm_framework_jni_CubismFrameworkJNI.h
	
	${FrameworkJNI}/math/moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI.cpp
	${FrameworkJNI}/math/moe_leekcake_live2dforjvm_framework_jni_math_CubismMatrix44JNI.h
	
	${FrameworkJNI}/id/moe_leekcake_live2dforjvm_framework_jni_id_CubismIdJNI.cpp
	${FrameworkJNI}/id/moe_leekcake_live2dforjvm_framework_jni_id_CubismIdJNI.h
	
	${FrameworkJNI}/id/moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI.cpp
	${FrameworkJNI}/id/moe_leekcake_live2dforjvm_framework_jni_id_CubismIdManagerJNI.h
	
	${FrameworkJNI}/extend/moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI.cpp
	${FrameworkJNI}/extend/moe_leekcake_live2dforjvm_framework_jni_extend_ExtendModelJNI.h
	
	${FrameworkJNI}/extend/moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI.cpp
	${FrameworkJNI}/extend/moe_leekcake_live2dforjvm_framework_jni_extend_ExtendPALJNI.h
	
	${FrameworkJNI}/extend_cpp/JavaAllocator.cpp
	${FrameworkJNI}/extend_cpp/JavaAllocator.hpp
	
	${FrameworkJNI}/extend_cpp/JavaDefine.cpp
	${FrameworkJNI}/extend_cpp/JavaDefine.hpp
	
	${FrameworkJNI}/extend_cpp/JavaModel.cpp
	${FrameworkJNI}/extend_cpp/JavaModel.hpp
	
	${FrameworkJNI}/extend_cpp/JavaPal.cpp
	${FrameworkJNI}/extend_cpp/JavaPal.hpp
	
	${FrameworkJNI}/extend_cpp/JNIHelper.h
	)
	
set(MemoryAccessJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/MemoryAccessJNI)
#Define MemoryAccessJNI
set(MemoryAccessJNISource
${MemoryAccessJNI}/moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI.c
${MemoryAccessJNI}/moe_leekcake_live2dforjvm_core_jni_MemoryAccessJNI.h
)

#Define Core
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore)

#Define Components

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeFramework)

add_library(
	Live2DCubismJNI
	SHARED
	${Live2DCubismJNISource}
)

target_include_directories(Live2DCubismJNI PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore/include)
target_include_directories(Live2DCubismJNI PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeFramework/src)
target_include_directories(Live2DCubismJNI PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismFrameworkJNI/extend_cpp)
target_include_directories(Live2DCubismJNI PUBLIC ${FRAMEWORK_GLFW_PATH})
target_include_directories(Live2DCubismJNI PUBLIC ${FRAMEWORK_GLEW_PATH})

set(LIBS ${CSM_CORE_LIBS} Framework)

if(${IS_DESKTOP})
	list(APPEND LIBS glew_s glfw)
endif()

# glew_s glfw

# HACK  Prevent link errors on Android
if (ANDROID)
  #target_link_libraries(Live2DCubismJNI EGL GLESv2 android log)
  list(APPEND LIBS EGL GLESv2 android log)
endif ()

target_link_libraries(Live2DCubismJNI 
	${LIBS}
)

if (CSM_CORE_DEPS)
  add_dependencies(Live2DCubismJNI ${CSM_CORE_DEPS})
endif ()

add_library(
	MemoryAccessJNI
	SHARED
	${MemoryAccessJNISource}
)

#Copy Result when Desktop
if (NOT ANDROID AND NOT EMSCRIPTEN AND NOT IOS AND NOT RPI)
add_custom_command(TARGET Live2DCubismJNI 
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E copy $<TARGET_FILE:Live2DCubismJNI> ${CMAKE_CURRENT_SOURCE_DIR}/Native)
				   
add_custom_command(TARGET MemoryAccessJNI 
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E copy $<TARGET_FILE:MemoryAccessJNI> ${CMAKE_CURRENT_SOURCE_DIR}/Native)
endif()