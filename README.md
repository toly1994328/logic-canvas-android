#### 零、前言：
>安卓图形绘制一直以来感觉都很繁琐  
在html5时，我用JavaScript封装了一个HTML5的canvas库。  
HTML5感觉和Android的canvas挺相似，所以考虑移植过来。  
绘图库核心是用配置信息绘图，通过逻辑运算绘图  
本篇会持续更新，记录LogicCanvas的成长历程  



##### 原理简单示意图：
![绘制一个五角星的过程.png](https://upload-images.jianshu.io/upload_images/9414344-d3d56c9d028e750b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 引入
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
	
implementation 'com.github.toly1994328:logic-canvas-android:0.01'
```

---
#### 一、以一个五角星来引入

##### 在自定义View的onDraw方法中：绘制外接圆半径100,内接圆半径50，填充色黄色的5角星
```
ZCanvas zCanvas = new ZCanvas(canvas);
zCanvas.drawNStar(
    new Painter()
            .num(5)//角的个数,数字任意
            .R(100f)//外接圆半径
            .r(50f)////内接圆半径
```

![五角星演示.png](https://upload-images.jianshu.io/upload_images/9414344-9322a4c50cb9f068.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 二、公有属性演示：
>所谓公有属性是指所有绘制图形适用的属性：包括  
线条粗细(b)、线条颜色(ss)、填充颜色(ss)、
位移(p)、坐标系(coo)、旋转(rot)、缩放(sx,sy)屏幕适配dp单位(dp)

属性| 默认值|简介|备注
---|---|---|----
p | Pos(0,0)|图形距画布左顶点偏移量|
rot | 0|旋转角度|弧度制
sx | 0|x缩放|
sy | 0|y缩放|
coo | Pos(0,0)|修改坐标系|平移、缩放、旋转使用
a | Pos(0,0)|修改锚点|
b | 1|线条粗|
ss | "#000000"|线条样式|-
fs | "#0000ff"|填充样式|-
dp | -|dp单位|在链式末尾调用

##### 1.位移：
>p 参数类型：Pos  
注：为了和数学更好契合，采用笛卡尔坐标系(上右正)，默认屏幕左上角(0,0)点  
为了明显，使用工具栏绘制网格参考

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .p(new Pos(200, -100)));//位移X,Y
```
![位移p.png](https://upload-images.jianshu.io/upload_images/9414344-605ef0cdef854edc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 2.坐标系：为了支持坐标系，可是煞费苦心啊！
>coo 参数类型：Pos
为了明显，使用工具栏绘制坐标系参考  
注意：使用坐标系后、平移、旋转、缩放都会根据新的坐标系来

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .coo(new Pos(600, 200))//设置坐标系
);
```

![坐标系coo.png](https://upload-images.jianshu.io/upload_images/9414344-46d594b3a1355f63.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 3.描边颜色、粗细
>ss 描边颜色 参数类型：int (颜色)
b 描边粗细 参数类型：int

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .p(new Pos(200, -100))
        .ss(Color.RED)//描边颜色
        .b(5f)//描边线条粗细
);
```
![描边，颜色.png](https://upload-images.jianshu.io/upload_images/9414344-0019bfb6e28aca67.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 4.旋转：思考良久，单位还是采用：角度数吧
>rot 旋转 参数类型：Float

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(new Pos(600, 200))
        .rot(90f)//设置旋转
);
```

---

![旋转.png](https://upload-images.jianshu.io/upload_images/9414344-a9c30e694dbc5a64.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 5.缩放：
>sx、sy 缩放比例 参数类型：Float

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(new Pos(600, 200))
        .sx(1.5f)
        .sy(1.5f)
);
```
![缩放.png](https://upload-images.jianshu.io/upload_images/9414344-e45ddd253bba16d5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

##### 6.锚点：
>coo 参数类型：Pos

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .ss(Color.RED)
        .coo(new Pos(600, 200))
        .a(new Pos(100, 100))
        .sx(1.5f)
        .sy(1.5f)
);
```

![修改参照点放大.png](https://upload-images.jianshu.io/upload_images/9414344-82a425573f28f684.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
##### 7.填充
>fs 描边颜色 参数类型：int (颜色)

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(100f)
        .r(50f)
        .coo(new Pos(600, 200))
        .fs(Color.YELLOW)
);
```

![填充.png](https://upload-images.jianshu.io/upload_images/9414344-295746c05353d5d0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
##### 8.dp单位适配
>下面蓝色是没有适配的，黄色是适配的，蓝色在不同分辨率产生差异，黄色则正常显示

```
zCanvas.drawNStar(new Painter()
        .num(5)
        .R(20f)
        .r(10f)
        .fs(Color.YELLOW)
        .dp(mContext)
);
```

![dp适配.png](https://upload-images.jianshu.io/upload_images/9414344-340c411d65170bfa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>公共属性展示到这里，持续更新，敬请关注  
更新时间：2018-09-12：10:25  
[LogicCanvas-项目地址：github](https://github.com/toly1994328/logic-canvas-android)


---

#### 二、直线绘制：
>特有属性：ps 参数类型 不定个数的Pos。  
再次强调：默认使用的是0,0为原点的笛卡尔坐标系

##### 1.单线条

```
 zCanvas.drawLines(
         new Painter()
                 .b(5f)
                 .ps(new Pos(0, 0), new Pos(200, -200))
 );
```

![绘制单直线.png](https://upload-images.jianshu.io/upload_images/9414344-2baec141a58dc594.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
##### 2.多线条

```
zCanvas.drawLines(
        new Painter()
                .b(5f)
                .ps(
                        new Pos(0, 0),
                        new Pos(200, -200),
                        new Pos(200,-400),
                        new Pos(200,-400),
                        new Pos(800,-400),
                        new Pos(0,0)
                )
);
```

![绘制多线条.png](https://upload-images.jianshu.io/upload_images/9414344-27ee9f5d7891e10a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 三、绘制矩形：
>参数 ： x 矩形宽  
y:矩形高  
r:矩形圆角


```
zCanvas.drawRect(
        new Painter()
                .x(1000/2f).y(618/2f).r(50f)
                .b(5f).ss(Color.RED).p(new Pos(100,-100))
);
```
![绘制矩形.png](https://upload-images.jianshu.io/upload_images/9414344-df6cfb5de1976f09.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
#### 四、画圆： 
>dir true 逆时针方向绘制--默认  
r 半径

```
zCanvas.drawCircle(
        new Painter()
                .r(100f)
                .b(5f).ss(Color.RED)
                .p(new Pos(200,-200))
```

![画圆.png](https://upload-images.jianshu.io/upload_images/9414344-dce3e1ce424f962c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---
#### 五、绘制弧线

```
zCanvas.drawArc(
        new Painter()
                .r(100f).ang(135f)
                .b(1f).ss(Color.RED)
                .p(new Pos(200,-100))
);
```
![绘制弧线.png](https://upload-images.jianshu.io/upload_images/9414344-679f18469de74145.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 六、正多边形和正多角形

```
for (int i = 5; i < 10; i++) {
    zCanvas.drawRegularPolygon(
            new Painter()
                    .num(i).R(80f)
                    .b(4f)
                    .p(new Pos(20+210*(i-5),-20)));
    zCanvas.drawRegularStar(
            new Painter()
                    .num(i).R(80f)
                    .b(4f)
                    .p(new Pos(20+210*(i-5),-220)));
}
```

![绘制正多边形和正多角星.png](https://upload-images.jianshu.io/upload_images/9414344-b8c986e32208259a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![多角星分析图](https://upload-images.jianshu.io/upload_images/9414344-deffb348a52faa88.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>展示到这里，持续更新，更多功能敬请期待  
更新时间：2018-09-12：13:25  
[LogicCanvas-项目地址：github](https://github.com/toly1994328/logic-canvas-android)
---

>本文由张风捷特烈原创,转载请注明  
[更多安卓技术欢迎访问：](https://www.jianshu.com/c/004f3fe34c94)https://www.jianshu.com/c/004f3fe34c94   
[张风捷特烈个人网站，编程笔记请访问：](http://www.toly1994.com)http://www.toly1994.com  
你的喜欢与支持将是我最大的动力  