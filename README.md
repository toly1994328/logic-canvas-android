#### 零、前言：
>安卓图形绘制一直以来感觉都很繁琐  
在html5时，我用JavaScript封装了一个HTML5的canvas库。  
HTML5感觉和Android的canvas挺相似，所以考虑移植过来。  
绘图库核心是用配置信息绘图，通过逻辑运算绘图  
本篇会持续更新，记录LogicCanvas的成长历程  

##### 2018年9月4号更新：由V0.01升级到V0.02  
>对项目进行大规模重构，分解ShapePath类,优化调用形式，更好解耦  
由于边线而导致的精准度问题已修正
加入刚刚属性：路径的方向，代号：dir

##### 2018年9月5号更新： 
>这次更新也挺厉害的，将Pos点类使用原型模式，避免很多地方都要new  
Pos点类思想层面由点，升级到向量，实现了向量的基本用法  
加入绘制文字功能(以前竟然没发现)：目前只是放字和位置，以后会完善更多文字方面的功能  
对坐标系统进行一定的优化  
Pos的向量形式使用有点庞大，新写一篇文章讲述，详见：[Android绘图之和我一起画箭头](https://www.jianshu.com/p/d524cede6768)

##### 2018年9月6号更新：由V0.01升级到V0.03  
Painter采用单例模式  
优化原型模式，各Shape采用深拷贝来解决构造较长、繁琐的情况  
比较new 对象和拷贝的效率问题，拷贝一点。具体见文：[来谈谈Java的深浅拷贝吧](https://www.jianshu.com/p/8096ffce07fd)  
完善向量部分，进行测试


---


##### 引入
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

implementation 'com.github.toly1994328:logic-canvas-android:0.03'
```
##### 点类Pos的使用：

```
//开始时初始化一个点对象
protected Pos pos = new Pos(0, 0);
```
//需要另一个点的话：就行了，使用原型，避免new对象
```
pos.clone(x, y)
```

---



#### 一、以一个五角星来引入

##### 在自定义View的onDraw方法中：绘制外接圆半径100,内接圆半径50的5角星
```
Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
painter.draw(
    new ShapeStar()
            .num(5)//角的个数,数字任意
            .R(100f)//外接圆半径
            .r(50f)////内接圆半径
```

![五角星演示.png](https://upload-images.jianshu.io/upload_images/9414344-9322a4c50cb9f068.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 二、公有属性演示：注：公共属性对应的函数在后调用
>所谓公有属性是指所有绘制图形适用的属性：包括  
线条粗细(b)、线条颜色(ss)、填充颜色(ss)、
位移(p)、坐标系(coo)、旋转(rot)、缩放(sx,sy)屏幕适配dp单位(dp)

属性| 默认值|简介|备注
---|---|---|----
p | Pos(0,0)|图形距画布左顶点偏移量|
rot | 0|旋转角度|弧度制-
sx | 0|x缩放|-
sy | 0|y缩放|-
coo | Pos(0,0)|修改坐标系|平移、缩放、旋转使用
a | Pos(0,0)|修改锚点|-
b | 1|线条粗|-
ss | "#000000"|线条样式|-
fs | "#0000ff"|填充样式|-
dir|逆时针方向|方向|-

##### 1.位移：
>p 参数类型：Pos  
注：为了和数学更好契合，采用笛卡尔坐标系(上右正)，默认屏幕左上角(0,0)点  
为了明显，使用工具栏绘制网格参考

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .p(pos.clone(200, -100)));//位移X,Y
```
![位移p.png](https://upload-images.jianshu.io/upload_images/9414344-605ef0cdef854edc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 2.坐标系：为了支持坐标系，可是煞费苦心啊！
>coo 参数类型：Pos
为了明显，使用工具栏绘制坐标系参考  
注意：使用坐标系后、平移、旋转、缩放都会根据新的坐标系来

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .coo(pos.clone(600, 200))//设置坐标系
);
```

![坐标系coo.png](https://upload-images.jianshu.io/upload_images/9414344-46d594b3a1355f63.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 3.描边颜色、粗细
>ss 描边颜色 参数类型：int (颜色)
b 描边粗细 参数类型：int

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .p(pos.clone(200, -100))
        .ss(Color.RED)//描边颜色
        .b(5f)//描边线条粗细
);
```
![描边，颜色.png](https://upload-images.jianshu.io/upload_images/9414344-0019bfb6e28aca67.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 4.旋转：思考良久，单位还是采用：角度数吧
>rot 旋转 参数类型：Float

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(pos.clone(600, 200))
        .rot(90f)//设置旋转
);
```

---

![旋转.png](https://upload-images.jianshu.io/upload_images/9414344-a9c30e694dbc5a64.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 5.缩放：
>sx、sy 缩放比例 参数类型：Float

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(pos.clone(600, 200))
        .sx(1.5f)
        .sy(1.5f)
);
```
![缩放.png](https://upload-images.jianshu.io/upload_images/9414344-e45ddd253bba16d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 6.锚点：
>coo 参数类型：Pos

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(pos.clone(600, 200))
        .a(pos.clone(100, 100))
        .sx(1.5f)
        .sy(1.5f)
);
```

![修改参照点放大.png](https://upload-images.jianshu.io/upload_images/9414344-82a425573f28f684.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
##### 7.填充
>fs 描边颜色 参数类型：int (颜色)

```
painter.draw(new ShapeStar()
        .num(5)
        .R(100f)
        .r(50f)
        .coo(pos.clone(600, 200))
        .fs(Color.YELLOW)
);
```

![填充.png](https://upload-images.jianshu.io/upload_images/9414344-295746c05353d5d0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

>公共属性展示到这里

---

#### 二、直线绘制：
>特有属性：ps 参数类型 不定个数的Pos。  
再次强调：默认使用的是0,0为原点的笛卡尔坐标系

##### 1.单线条

```
painter.draw(
        new ShapeLine()
                .ps(pos.clone(0, 0), pos.clone(200, -200))
                .b(5f)
);
```

![绘制单直线.png](https://upload-images.jianshu.io/upload_images/9414344-2baec141a58dc594.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
##### 2.多线条

```
painter.draw(
        new ShapeLine()
                .ps(
                        pos.clone(0, 0),
                        pos.clone(200, -200),
                        pos.clone(200,-400),
                        pos.clone(200,-400),
                        pos.clone(800,-400),
                        pos.clone(0,0)
                ).b(5f)
);
```

![绘制多线条.png](https://upload-images.jianshu.io/upload_images/9414344-27ee9f5d7891e10a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 三、绘制矩形：
>参数 ： x 矩形宽  
y:矩形高  
r:矩形圆角


```
painter.draw(
        new ShapeRect()
                .x(1000/2f).y(618/2f).r(50f)
                .b(5f).ss(Color.RED).p(pos.clone(100,-100))
);
```
![绘制矩形.png](https://upload-images.jianshu.io/upload_images/9414344-df6cfb5de1976f09.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
#### 四、画圆： 
>dir true 逆时针方向绘制--默认  
r 半径

```
painter.draw(
        new ShapeArc(1)
                .r(100f)
                .b(5f).ss(Color.RED)
                .p(pos.clone(200,-200))
```

![画圆.png](https://upload-images.jianshu.io/upload_images/9414344-dce3e1ce424f962c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
#### 五、绘制弧线

```
painter.draw(
        new ShapeArc()
                .r(100f).ang(135f)
                .b(1f).ss(Color.RED)
                .p(pos.clone(200,-100))
);
```
![绘制弧线.png](https://upload-images.jianshu.io/upload_images/9414344-679f18469de74145.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 六、绘制文字：
>al 对齐方式：String 类型，效果下如图  
str 文字  
size 大小

```
painter.drawText(
        new ShapeText()
                .str("Toly")//文字
                .size(80)//大小
                .al("<")//对齐方式
                .p(400f,400f));
```

![文字al属性.png](https://upload-images.jianshu.io/upload_images/9414344-2f4bc8a243aa3e08.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---


#### 七、LogicCanvas与Android原生Path结核使用

```
Path path = new Path();// 创建Path
path.lineTo(200, -200);// lineTo
path.lineTo(200,0);
path.close();
Shape shapeEmpty = new ShapeEmpty(path)
        .b(6f).coo(400f, 400f);
painter.draw(shapeEmpty);
```

![与安卓Path结合.png](https://upload-images.jianshu.io/upload_images/9414344-abf35b857368d293.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 八、正多边形和正多角形

```
for (int i = 5; i < 10; i++) {
    painter.draw(
            new ShapeStar(ShapeStar.MODE_POLYGON)
                    .num(i).R(80f)
                    .b(4f)
                    .p(pos.clone(20+210*(i-5),-20)));//内接圆半径
    painter.draw(
            new ShapeStar(ShapeStar.MODE_REGULAR)
                    .num(i).R(80f)
                    .b(4f)
                    .p(pos.clone(20+210*(i-5),-220)));//内接圆半径
}
```

![绘制正多边形和正多角星.png](https://upload-images.jianshu.io/upload_images/9414344-b8c986e32208259a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![多角星分析图](https://upload-images.jianshu.io/upload_images/9414344-deffb348a52faa88.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>展示到这里，持续更新，更多功能敬请期待
更新时间：2018-09-12：13:25
[LogicCanvas-项目地址：github](https://github.com/toly1994328/logic-canvas-android)

---


#### 后记、
##### 1.声明：
>[1]本文由张风捷特烈原创,转载请注明  
[2]欢迎广大编程爱好者共同交流  
[3]个人能力有限，如有不正之处欢迎大家批评指证，必定虚心改正  
[4]你的喜欢与支持将是我最大的动力 

##### 2.连接传送门：
[更多安卓技术欢迎访问:安卓技术栈](https://www.jianshu.com/c/004f3fe34c94)  
[我的github地址：欢迎star](https://github.com/toly1994328)  
[简书首发，腾讯云+社区同步更新](https://cloud.tencent.com/developer/user/2608304)  
[张风捷特烈个人网站，编程笔记请访问：](http://www.toly1994.com)http://www.toly1994.com   


##### 3.联系我
>QQ:1981462002   
邮箱：1981462002@qq.com  
微信：zdl1994328  

##### 4.欢迎关注我的微信公众号，最新精彩文章，及时送达：  
![公众号.jpg](https://upload-images.jianshu.io/upload_images/9414344-c474349cd3bd4b82.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

