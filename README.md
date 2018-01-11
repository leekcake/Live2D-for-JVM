# Live2D for JVM
Defective porting of Cubism for JVM based language (like java, kotlin, etc...)

## Setup
- Download Native Core from https://live2d.github.io/#native (Cubism31SdkNative-EAP5.zip ?)
- Extract, Move 'Core' Folder into (Project Root)\Native and Rename as 'CubismNativeCore'
- Download submodule with git submodule init / update
- Do build configure with cmake
- Build with compiler
 
## Doesn't working
 - Cubism Native Core's static library doesn't prepared for suit to shared library in linux. so can't use it for linux