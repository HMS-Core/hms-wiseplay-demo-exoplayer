# WisePlay DRM Demo Based On ExoPlayer #


![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)

中文 | [English](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/blob/master/README.md)

## 目录

 * [介绍](#介绍)
 * [开始](#开始)
 * [开发环境](#开发环境)
 * [开源许可](#开源许可)
 
## 介绍

这个Demo演示了如何在开源播放器ExoPlayer中集成WisePlay DRM，关于ExoPlayer的更多介绍，请参考其官网：[ExoPlayer官网](https://exoplayer.dev/)

Demo代码基于ExoPlayer的代码标签为[r2.11.4](https://github.com/google/ExoPlayer/releases/tag/r2.11.4), 演示了如何在线播放WisePlay加密片源。
代码中对ExoPlayer的修改均以关键如下的注释标记 "*Begin add for WisePlay*"或"*Begin mod for WisePlay*" ，搜索代码的改动点，可根据这些关键字进行搜索。

例如，在类"com.google.android.exoplayer2.C"中, 修改点如下:
```java
  // Begin add for WisePlay
  /**
   * WisePlay UUID
   */
  public static final UUID WISEPLAY_UUID = new UUID(0X3D5E6D359B9A41E8L, 0XB843DD3C6E72C42CL);
  // End add for WisePlay
```

想了解更多WisePlay处理License的代码演示，请参考WisePlay的Demo代码仓 [WisePlay DRM Demo](https://github.com/HMS-Core/hms-wiseplay-demo) 以及[开发者联盟官网](https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/wiseplay-introduction)    
    

## 开始

使用Demo之前, 请确保手机上正确安装HMS Core 4.0+.

1.检查Android Studio环境是否就绪，网络正常连接.
2.运行Demo中的main demo工程.  

ExoPlayer Demo执行的细节，可参考其[官方说明](https://github.com/google/ExoPlayer/blob/release-v2/demos/README.md)

## 开发环境

Android SDK Version >= 23以及JDK version >= 1.7

## 技术支持
如果您对HMS Core还处于评估阶段，可在[Reddit社区](https://www.reddit.com/r/HMSCore/)获取关于HMS Core的最新讯息，并与其他开发者交流见解。

如果您对使用HMS示例代码有疑问，请尝试：
- 开发过程遇到问题上[Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services)，在**huawei-mobile-services**标签下提问，有华为研发专家在线一对一解决您的问题。
- 到[华为开发者论坛](https://developer.huawei.com/consumer/cn/forum/blockdisplay?fid=18) HMS Core板块与其他开发者进行交流。

如果您在尝试示例代码中遇到问题，请向仓库提交[issue](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/issues)，也欢迎您提交[Pull Request](https://github.com/HMS-Core/hms-wiseplay-demo-exoplayer/pulls)。
	
## 开源许可    

此Demo基于APL2.0 [Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
