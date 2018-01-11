#Define Project
CMAKE_MINIMUM_REQUIRED ( VERSION 3.6 )
PROJECT ("Live2D-for-JVM")
include(ExternalProject)

#Require JNI
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

#Define Core
set(CSM_COMPONENTS_CORE_INCLUDE_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore/include)
#include_directories(${CSM_COMPONENTS_CORE_INCLUDE_DIRECTORY})

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore)

#Define Components
#include_directories(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents/include)
set(CSM_COMPONENTS_BUILD_GL_RENDERER ON)

if (NOT ANDROID AND NOT IOS AND NOT EMSCRIPTEN AND NOT RPI)
  set(_DESKTOP ON)
endif ()
# Set glad flag on desktop.
if (_DESKTOP)
  set(_USE_GLAD ON)
  set(CSM_COMPONENTS_GL_H "${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents/sample/vendor/glad/include/glad/glad.h" CACHE STRING "" FORCE)
# ... and OpenGL ES 2.0 flag else.
else ()
  set(_USE_GLES20 ON)
endif ()

add_subdirectory(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeComponents)

include_directories(  ${CSM_CORE_INCLUDE_DIR}
  ${CSM_COMPONENTS_INCLUDE_DIR}
  )

set(CoreJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismCoreJNI)
set(FrameworkJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismFrameworkJNI)
set(MemoryAccessJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/MemoryAccessJNI)
set(GLRenderingJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismGLRenderingJNI)
add_library(
	Live2DCubismJNI
	SHARED
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.cpp
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.h
	${FrameworkJNI}/moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI.cpp
	${FrameworkJNI}/moe_leekcake_live2dforjvm_Live2DCubismFrameworkJNI.h
	${GLRenderingJNI}/moe_leekcake_live2dforjvm_Live2DCubismGLRenderingJNI.cpp
	${GLRenderingJNI}/moe_leekcake_live2dforjvm_Live2DCubismGLRenderingJNI.h
	${_CSM_COMPONENTS_SRC_FILES}
)

target_link_libraries(Live2DCubismJNI 
${CSM_CORE_LIBS}
${CSM_COMPONENTS_LIBS}
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
	${MemoryAccessJNI}/moe_leekcake_live2dforjvm_MemoryAccessJNI.cpp
	${MemoryAccessJNI}/moe_leekcake_live2dforjvm_MemoryAccessJNI.h
)