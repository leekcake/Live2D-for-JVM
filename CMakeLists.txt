CMAKE_MINIMUM_REQUIRED ( VERSION 3.6 )
PROJECT ( "Live2D-for-JVM" )

set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/Native)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/Native)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/Native)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

if(NOT CMAKE_SIZEOF_VOID_P)
    message("CMAKE_SIZEOF_VOID_P Not defined, set with 64-bit default")
    set(CMAKE_SIZEOF_VOID_P 8)  # replace 4 with 8 for 64-bit machine
endif()

set(CMAKE_C_COMPILER "gcc")
set(CMAKE_CXX_COMPILER "gcc")

include(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore/CMakeLists.txt JAVA_INCLUDE_PATH)
include_directories(${CMAKE_CURRENT_SOURCE_DIR}/Native/CubismNativeCore/include)

set(CoreJNI ${CMAKE_CURRENT_SOURCE_DIR}/Native/Live2DCubismCoreJNI)
add_library(
	Live2DCubismCoreJNI
	SHARED
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.cpp
	${CoreJNI}/moe_leekcake_live2dforjvm_Live2DCubismCoreJNI.h
)
