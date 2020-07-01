# HMS WisePlay Demo Based On Exoplayer #

## Table of Contents

 * [Introduction](#introduction)
 * [Getting Started](#getting-started)
 * [Supported Environments](#supported-environments)
 * [License](#license)
 
## Introduction

This demo provides an example of integrating HMS WisePlay Kit with the ExoPlayer project for Android. 
For more detail about exoplayer, please follow [exoplayer's official document](https://exoplayer.dev/)

The demo is based on exoplayer [r2.11.4](https://github.com/google/ExoPlayer/releases/tag/r2.11.4), it shows how to play online WisePlay Drm streamings. the modification is comment by keyword "*Begin add for WisePlay*" or "*Begin mod for WisePlay*" 

For example, in the class "com.google.android.exoplayer2.C", the modification is as follows:
```java
  // Begin add for WisePlay
  /**
   * WisePlay UUID
   */
  public static final UUID WISEPLAY_UUID = new UUID(0X3D5E6D359B9A41E8L, 0XB843DD3C6E72C42CL);
  // End add for WisePlay
```

Find more about HMS Wiseplay Kit, for example, offline license managment, please refer to another [wiseplay demo](https://github.com/HMS-Core/hms-wiseplay-demo)

## Getting Started

To use functions provided by examples, please make sure Huawei Mobile Service 4.0+ has been installed on your cellphone.    
1. Check whether the Android studio development environment is ready.     
2. Run the app on your Android device or emulator which have installed latest Huawei Mobile Service(HMS).  

## Supported Environments

Android SDK Version >= 23 and JDK version >= 1.7 is recommended.

##  License
pushkit PHP sample is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
