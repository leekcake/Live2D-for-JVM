# Live2D(Cubism) for JVM
Unofficial, unstable and incomplete binding of Live2D(Cubism) Native for JVM based language (like java, kotlin, etc...)

## Purpose of Development
I personally need to use live2d in kotlin with jvm based game engine (LibGDX), but there no offical support for jvm. so I developed it.
because my develop skill is too bad, and I'm too busy to handle all thing, It focused to my purpose. so it may doesn't enough for you.

## Typical Checklist
 - I do not guarantee that this will work normally.
 - Because my English is not good, overall word selection and long sentence completion may be inadequate.
 - It use customized CubismNativeComponents repo(https://github.com/leekcake/CubismNativeComponents), may you want to use generic repo(https://github.com/Live2D/CubismNativeComponents) for it
 - Cubism Native Core's static library doesn't prepared for suit to shared library in linux. so can't use it for linux
 - Link error raised for arm64-v8a arch build(Archive has no index; run ranlib to add one), and I doesn't have any idea to resolve it :(
 - iOS(with Intel MOE[Multi-OS-Engine]) doesn't tested
 
## How to Initialize the development environment
- Download Native Core from https://live2d.github.io/#native (Cubism31SdkNative-EAP5.zip ?)
- Extract, Move 'Core' Folder into (Project Root)\Native and Rename as 'CubismNativeCore'
- Download submodule with git submodule init / update
- Do build configure with cmake
- Build with compiler
- Open gradle project with your IDE