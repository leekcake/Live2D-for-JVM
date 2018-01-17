#1. Define Project
CMAKE_MINIMUM_REQUIRED ( VERSION 3.6 )
PROJECT ("Live2D-for-JVM")

#Use JNI
if(NOT ANDROID)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
endif()

#Define GLES / GLAD
if (NOT ANDROID AND NOT IOS AND NOT EMSCRIPTEN AND NOT RPI)
  set(_DESKTOP ON)
endif ()
# Set glad flag on desktop.
if (_DESKTOP)
  set(_USE_GLAD ON)
  set(CSM_COMPONENTS_GL_H "${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents/sample/vendor/glad/include/glad/glad.h" CACHE STRING "" FORCE)
  include_directories(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents/sample/vendor/glad/include)
# ... and OpenGL ES 2.0 flag else.
else ()
  set(_USE_GLES20 ON)
endif ()

#Define Live2DCubismJNI Source
set(CoreJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismCoreJNI)
set(FrameworkJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismFrameworkJNI)
set(GLRenderingJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismGLRenderingJNI)
set(Live2DCubismJNISource
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.c
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.h
	${FrameworkJNI}/moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI.c
	${FrameworkJNI}/moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI.h
	${GLRenderingJNI}/moe_leekcake_live2dforjvm_Live2DCubismGLRenderingJNI.c
	${GLRenderingJNI}/moe_leekcake_live2dforjvm_Live2DCubismGLRenderingJNI.h)
	
if(_USE_GLAD)
		list(APPEND Live2DCubismJNISource ${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents/sample/vendor/glad/src/glad.c)
endif()

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
option(CSM_COMPONENTS_BUILD_GL_RENDERER "Enables OpenGL reference renderer." ON)

if (_DESKTOP)
  set(CSM_COMPONENTS_GL_H "${CMAKE_CURRENT_LIST_DIR}/Native/CubismNativeComponents/sample/vender/glad/include/glad/glad.h" CACHE STRING "")
endif ()

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents)

include_directories(  ${CSM_CORE_INCLUDE_DIR}
  ${CSM_COMPONENTS_INCLUDE_DIR}
  )

add_library(
	Live2DCubismJNI
	SHARED
	${Live2DCubismJNISource}
)

set(LIBS ${CSM_CORE_LIBS}
${CSM_COMPONENTS_LIBS})

# HACK  Prevent link errors on Android
if (ANDROID)
  #target_link_libraries(Live2DCubismJNI EGL GLESv2 android log)
  list(APPEND LIBS EGL GLESv2 android log)
endif ()

target_link_libraries(Live2DCubismJNI 
	${LIBS}
)

if (_USE_GLAD)
  set(_DEFINITIONS -D_USE_GLAD)
else (_USE_GLES20)
  set(_DEFINITIONS -D_USE_GLES20)
endif ()
target_compile_definitions(Live2DCubismJNI PRIVATE ${_DEFINITIONS})

if (CSM_CORE_DEPS)
  add_dependencies(Live2DCubismJNI ${CSM_CORE_DEPS})
endif ()

add_library(
	MemoryAccessJNI
	SHARED
	${MemoryAccessJNISource}
)

if (NOT ANDROID AND NOT EMSCRIPTEN AND NOT IOS AND NOT RPI)
add_custom_command(TARGET Live2DCubismJNI 
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E copy $<TARGET_FILE:Live2DCubismJNI> ${CMAKE_CURRENT_SOURCE_DIR}/Native)
				   
add_custom_command(TARGET MemoryAccessJNI 
                   POST_BUILD
                   COMMAND ${CMAKE_COMMAND} -E copy $<TARGET_FILE:MemoryAccessJNI> ${CMAKE_CURRENT_SOURCE_DIR}/Native)
endif()