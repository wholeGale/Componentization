# 组件化探索实践
探索目标：无架构->>模块化(业务导向)->>组件化(功能导向)->>插件化(技术导向)

## 为什么要组件化开发
Android项目代码量达到一定程度，编译将是一件非常痛苦的事情，短则几分钟，长则几十分钟(PS:我曾经入职一家新公司时，提供的低配电脑一次编译
长达一小时，简直不能忍，最终自己买了顶配mac)，想想我们有时候只是改了某个module下某个界面的一个小UI，或者只是修复某个小bug，却要让AndroidStudio重新
编译一下所有代码打包(InstantRun由于不好用，一些缺陷，关闭了)，效率很低(相比较而言，flutter开发时热重载就很爽)，也不利于后期测试。
而组件化开发可以有效降低模块之间的耦合，代码结构清晰，有利于后期扩展和维护，同时模块化的编译可以有效减少编译时间，当然总的编译时间是不会减少的，
只是App模块化之后开发调试某个模块内功能时，只需要编译特定模块，让其独立打包成一个apk，可以快速编译，独立调试运行，有利于团队分工协同开发，
也便于后期开发/测试人员的针对性测试，长远来看，为插件化做好铺垫。
简言之，组件化开发优势如下：
- 项目解耦，代码结构清晰，有利于后期扩展和维护；
- 独立编译某个特定module为apk，有利于快速调试；
- 不同项目组之间分工明确，有利于协同开发；
- 便于单元测试，有利于开发/测试人员的针对性测试；
- 有利于后期进行插件化开发；

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

#### 资源冲突问题
当分别开发模块时，容易出资源重复命名的问题，可以在build.gradle中设置
> resourcePrefix "module1_"


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

#### 组件解耦的目标及代码/资源隔离
##### 解耦目标
代码解耦的首要目标就是组件之间的完全隔离，在开发过程中我们要时刻牢记，我们不仅不能直接使用其他组件中的类，最好能根本不了解其中的实现细节。

##### 代码隔离
即使我们在极力的避免组件间及模块与组件间类的直接引用。不过即使通过base中提供Service的方式解决了直接引用类的问题，
但是我们在主项目通过**implementation**添加对login和share组件的依赖后，在主项目中依旧是可以访问到login和share组件中的类的。
  
这种情况下即使我们的目标是面向接口编程，但是只要能直接访问到组件中的类，就存在有意或无意的直接通过访问类的方式使用到组件中的代码的可能，
如果真的出现了这种情况，我们上面说的解耦就会完全白做了。
  
我们希望的组件依赖是只有在打包过程中才能直接引用组件中的类，在开发阶段，所有组件中的类我们都是不可以访问的。
只有实现了这个目标，才能从根本上杜绝直接引用组件中类的问题。

**解决办法：**
我们可以通过Gradle3.0提供的新依赖方式**runtimeOnly**，通过runtimeOnly方式依赖时，依赖项仅在运行时对模块及其消费者可用，
编译期间依赖项的代码对其消费者时完全隔离的。


参考：
- https://mp.weixin.qq.com/s/ubihF5bDbofZfKTT-Ou2gw
- https://www.jianshu.com/p/186fa07fc48a