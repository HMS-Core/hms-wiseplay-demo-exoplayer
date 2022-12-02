# WisePlay DRM Demo Based On Exoplayer #

![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)

English | [中文](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/blob/master/readme_zh.md)

## Table of Contents

* [Introduction](#introduction)
* [Getting Started](#getting-started)
* [Supported Environments](#supported-environments)
* [License](#license)

## Introduction

This demo provides an example of integrating WisePlay DRM with the ExoPlayer project for Android. Based on ExoPlayer [r2.11.4](https://github.com/google/ExoPlayer/releases/tag/r2.11.4).For more detail about ExoPlayer, please follow [ExoPlayer's official document](https://exoplayer.dev/).

1. Demonstrates how to play online content encrypted by WisePlay DRM, for example, the HLS content encrypted in SM4 mode.
   In the sample code, modifications in ExoPlayer are marked with "Begin add for WisePlay" or "Begin mod for WisePlay", as well as "Begin add for sm4" or "Begin mod for sm4" for you to search for.
   For example, the modifications are as follows in the com.google.android.exoplayer2.C class:
    ```java
      // Begin add for WisePlay
      /**
       * WisePlay UUID
       */
      public static final UUID WISEPLAY_UUID = new UUID(0X3D5E6D359B9A41E8L, 0XB843DD3C6E72C42CL);
      // End add for WisePlay

      // Begin add for sm4
      /** "sm4c" scheme type name as defined in ISO/IEC 23001-7:2016. */
      public static final String CENC_TYPE_sm4c = "sm4c";

      /** "sm4s" scheme type name as defined in ISO/IEC 23001-7:2016. */
      public static final String CENC_TYPE_sm4s = "sm4s";
      // End add for sm4
    ```

2. Supports software decoding based on the WisePlay DRM SDK, implementing functions such as device certificate application, application for the encrypted video source certificate, video source decoding, and more.
   How to use:
   1. Contact the service provider to obtain the WisePlay DRM SDK in Java version that is for commercial use, signature, and description file.
   2. Place the obtained **WisePlayDrmSDK.aar** file under the **wiseplaydrmsdk-test.aarhms-wiseplay-demo-exoplayer/library/wiseplaydrmsdk/** directory and delete the original test file.
   3. Add the test video source file to the **hms-wiseplay-demo-exoplayer/demos/main/src/main/assets/** directory. For details about the file format, you can refer to the **media.exolist.json** file that is the original video source of ExoPlayer.
   4. Use Android Studio to compile the main demo and install it. Tap More in the upper right corner of the demo app screen displaying a list of video sources. Tick the **Use WisePlayDrmSDK** checkbox to enable the WisePlay DRM SDK. Then, functions of the SDK will be available for video source playback.

   The demo is for reference only. For details about how to use the WisePlay DRM SDK, contact the service provider.

Find more about WisePlay DRM, for example, offline license managment, please refer to another demo [WisePlay DRM Demo](https://github.com/HMS-Core/hms-wiseplay-demo) and [WisePlay DRM Official Website](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/wiseplay-introduction)


## Getting Started

To use functions provided by examples, please make sure Huawei Mobile Service 4.0+ has been installed on your cellphone.
1. Check whether the Android studio development environment is ready.
2. Run the main demo on your Android device or emulator which have installed latest Huawei Mobile Service(HMS).

The detail of run ExoPlayer demo, please follow [ExoPlayer's introduction](https://github.com/google/ExoPlayer/blob/release-v2/demos/README.md)

## Supported Environments

Android SDK Version >= 23 and JDK version >= 11 is recommended.

## Question or issues
If you want to evaluate more about HMS Core,
[r/HMSCore on Reddit](https://www.reddit.com/r/HMSCore/) is for you to keep up with latest news about HMS Core, and to exchange insights with other developers.

If you have questions about how to use HMS samples, try the following options:
- [Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services) is the best place for any programming questions. Be sure to tag your question with
  **huawei-mobile-services**.
- [Huawei Developer Forum](https://forums.developer.huawei.com/forumPortal/en/home?fid=0101187876626530001) HMS Core Module is great for general questions, or seeking recommendations and opinions.

If you run into a bug in our samples, please submit an [issue](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/issues) to the Repository. Even better you can submit a [Pull Request](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/pulls) with a fix.

##  License
The demo code is licensed under the [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
