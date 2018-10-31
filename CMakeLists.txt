#1. Define Project
CMAKE_MINIMUM_REQUIRED ( VERSION 3.6 )
PROJECT ("Live2D-for-JVM")

#Use JNI
if(NOT ANDROID)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
endif()

if(WIN32)
	set(FRAMEWORK_DEFINITIOINS
		-DCSM_TARGET_WIN_GL
		-DGLEW_STATIC
	)
	
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
endif()

#glew_s
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glew-2.1.0/build/cmake)
#glfw
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glfw-3.2.1)

set(FRAMEWORK_GLFW_PATH ${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glfw-3.2.1/include)
set(FRAMEWORK_GLEW_PATH ${CMAKE_CURRENT_SOURCE_DIR}/Native/ThirdParty/glew-2.1.0/include)

#Define Live2DCubismJNI Source
set(CoreJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismCoreJNI)
set(Live2DCubismJNISource
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.c
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.h)
	
set(MemoryAccessJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/MemoryAccessJNI)
#Define MemoryAccessJNI
set(MemoryAccessJNISource
${MemoryAccessJNI}/moe_leekcake_live2dforjvm_MemoryAccessJNI.c
${MemoryAccessJNI}/moe_leekcake_live2dforjvm_MemoryAccessJNI.h
)

#Define Core
set(CSM_COMPONENTS_CORE_INCLUDE_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore/include"  CACHE STRING "Path to Live2D Cubism Core include directory for native development.")
add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore)

#Define Components

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeFramework)

include_directories(  ${CSM_CORE_INCLUDE_DIR}
  ${CSM_COMPONENTS_INCLUDE_DIR}
  )

add_library(
	Live2DCubismJNI
	SHARED
	${Live2DCubismJNISource}
)

set(LIBS ${CSM_CORE_LIBS}
${commonfiles})

message(${CSM_CORE_LIBS})

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