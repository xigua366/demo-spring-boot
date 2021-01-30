### 缺点:
+ Lombok的使用要求一定要在IDE中安装对应的插件，如果项目组中有一个人使用了Lombok则都要用。
+ 代码可读性，可调试性低，比如想知道某个类中的某个属性的getter方法都被哪些类引用。
+ 影响升级，如果升级到某个新版本的JDK的时候，如果其中的特性在Lombok中不支持的话就会受到影响。
+ 要注意常⻅的细节点：
    + 比如只使用了@Data，而不使用@EqualsAndHashCode(callSuper=true)的话，会默认是@EqualsAndHashCode(callSuper=false),
    + 这时候生成的equals()方法只会比较子类的属性，不会考虑从父类继承的属性，无论父类属性访问权限是否开放,
    + 只要知道是否需要使用父类的属性即可，也提供定制化配置（参看lombok.config文件），所以不用过多担心。

### 优点：
+ 使用注解即可帮忙自动生成代码。
+ 大大减少了代码量，使代码非常简洁。
+ 部分注解在业务项目中开发能大大提高效率。


### 项目中应该用还是不用呢？
+ 不建议开发中间件的项目使用，中间件设计的要求是解耦少依赖
+ 业务项目实体类可以用，且用的时候知道对应的常⻅的注解原理


### Lombok主要注解
+ @Setter/@Getter
+ @NonNull
+ @NoArgsConstructor
+ @AllArgsConstructor
+ @RequiredArgsConstructor
+ @ToString
+ @EqualsAndHashCode
+ @Data
+ @Builder
+ @Log
+ @Slf4j

> @Data等效于以下多个注解的集合：
> 
> @ToString
> 
> @EqualsAndHashCode
> 
> @Getter
> 
> @Setter
> 
> @RequiredArgsConstructor


### lombok原理
lombok本质是在javac编译阶段生成*.class字节码文件时做了一些工作，给类添加注解后，可以使用 mvn clean compile 命令，查看target下的.class文件，查看效果。