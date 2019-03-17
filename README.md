# 组件化探索实践
探索目标：无架构->>模块化(业务导向)->>组件化(功能导向)->>插件化(技术导向)

## 模块化(Modular)
模块(Module)指的是独立的业务模块，比如[首页模块]、[直播间模块]等，模块相对与组件来说粒度更大，一个模块中可能包含多个组件。

## 组件化(Componentization)
组件指的是单一的功能组件，如 [视频播放组件]、[支付组件] 等，可以单独抽出来作为SDK。

### 组件化开发需要解决的问题
#### 独立编译运行
每个组件都是一个完整的整体，所以组件开发过程中要满足单独运行(做为App)及集成调试(做为Library)，这样还可以提升开发过程中项目的编译速度。  
解决方案：
- 利用Gradle动态配置工程类型/ApplicationId；
- 动态加载不同目录的AndroidManifest文件；

#### 组件间跳转
每个组件模块内都有自己的界面，安卓原生提供的startActivity跳转时，会导入类路径，模块间耦合严重。
解决方案：
- 利用阿里开源的ARouter进行路由跳转；

特别注意(踩坑记录)：
- 每个组件工程的build.gradle中都要依赖annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'(x.x.x要与compiler匹配，多组件间保持一致)。
- build.gradle中support-annotations问题。
- 主app的build.gradle中需要依赖子工程

#### 组件间数据传递与通信
由于主项目与组件，组件与组件之间都是不可以直接引用类的，开发中我们必然会遇到需要在组件之间进行数据传递/通信，这时候怎么处理比较优雅呢？
解决方案：
- 可以通过ARouter路由跳转时携带数据(包括基本数据类型和对象序列化)；
- 方法调用，采用 [接口 + 实现] 的方式进行通信，这时候也可以携带数据；

参考：
- https://mp.weixin.qq.com/s/ubihF5bDbofZfKTT-Ou2gw