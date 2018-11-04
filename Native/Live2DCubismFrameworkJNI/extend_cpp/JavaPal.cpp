/*
* Copyright(c) Live2D Inc. All rights reserved.
*
* Use of this source code is governed by the Live2D Open Software license
* that can be found at http://live2d.com/eula/live2d-open-software-license-agreement_en.html.
*/

#include "JavaPal.hpp"
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>  
#include <sys/stat.h>
#include <iostream>
#include <fstream>
#include <Model/CubismMoc.hpp>
#include "JavaDefine.hpp"
#include <Live2DCubismCore.hpp>

using std::endl;
using namespace Csm;
using namespace std;
using namespace JavaDefine;

double JavaPal::s_currentFrame = 0.0;
double JavaPal::s_lastFrame = 0.0;
double JavaPal::s_deltaTime = 0.0;
JavaPALTimeFunction JavaPal::s_timeFunction = NULL;
JavaPALTextureFunction JavaPal::s_textureFunction = NULL;
JavaPALDataFunction JavaPal::s_dataFunction = NULL;

csmByte* JavaPal::LoadFileAsBytes(const string filePath, csmSizeInt* outSize)
{
	if (s_dataFunction == NULL) {
		return NULL;
	}
	return s_dataFunction(filePath, outSize);
}

void JavaPal::ReleaseBytes(csmByte* byteData)
{
    delete[] byteData;
}

csmFloat32  JavaPal::GetDeltaTime()
{
    return static_cast<csmFloat32>(s_deltaTime);
}

void JavaPal::UpdateTime()
{
	if (s_timeFunction == NULL) {
		return;
	}
	s_currentFrame = s_timeFunction();
    s_deltaTime = s_currentFrame - s_lastFrame;
    s_lastFrame = s_currentFrame;
}

void JavaPal::PrintLog(const csmChar* format, ...)
{
    va_list args;
    csmChar buf[256];
    va_start(args, format);
    vsnprintf(buf, sizeof(buf), format, args); // 標準出力でレンダリング
	Live2D::Cubism::Core::csmGetLogFunction()(buf);
    va_end(args);
}

void JavaPal::PrintMessage(const csmChar* message)
{
    PrintLog("%s", message);
}

int JavaPal::GetTexture(const char * filePath)
{
	if (s_textureFunction == NULL) {
		return -1;
	}
	return s_textureFunction(filePath);
}

void JavaPal::SetJavaPALTimeFunction(JavaPALTimeFunction function)
{
	s_timeFunction = function;
}

void JavaPal::SetJavaPALTextureFunction(JavaPALTextureFunction function)
{
	s_textureFunction = function;
}

void JavaPal::SetJavaPALDataFunction(JavaPALDataFunction function)
{
	s_dataFunction = function;
}
