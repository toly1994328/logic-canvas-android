#### 零、前言

>[1].今天总结一下TextView  
[2].TextView在View家族的地位是：源码行数11000+，可以说是个大类  
[3].TextView直接继承自View，EditText，Button，CheckBox都是它的后代  
[4].TextView可以说常用至极，所以掌握TextView是必要的
[5].一些细小偏僻的点在这里综合一下，以便用时好找

---
#### 一、拿一个Hello World的TextView来举例
##### 1、代码中设置字体大小，自选尺寸

```
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);//dp
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_PT,20);//磅
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);//sp---默认
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);//像素
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_MM,20);//毫米
mIdTv.setTextSize(TypedValue.COMPLEX_UNIT_IN, 20);//英寸
```

---
##### 2.append()添加字符串

```
mIdTv.append("--toly"); //Hello World--toly
mIdTv.append("--toly", 0, 3); //Hello World--t
```

---
##### 3.单行显示、行尾省略

```
mIdTv.setSingleLine();
mIdTv.setEllipsize(TextUtils.TruncateAt.END);//结尾省略...
或
android:singleLine="true"
android:ellipsize="end"
```

---

##### 4.跑马灯效果

```
android:focusable="true"
android:focusableInTouchMode="true"
android:ellipsize="marquee"
android:singleLine="true"
android:marqueeRepeatLimit="marquee_forever"
```


---

##### 5.SpannableString的简单使用
>textview富文本，这里简单实现下图效果：

![SpannableString](https://upload-images.jianshu.io/upload_images/9414344-9c8d4e80d930581f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
mIdTv.setText(seal());

private SpannableString seal() {
    SpannableString ssSeal = new SpannableString("水杯特价￥50￥3点击购买");
    //图片
    Drawable drawable = getResources().getDrawable(R.mipmap.cup);
    drawable.setBounds(0,0,200,200);
    ImageSpan imageSpan = new ImageSpan(drawable);
    ssSeal.setSpan(imageSpan, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //斜体
    StyleSpan styleSpan_I  = new StyleSpan(Typeface.ITALIC);
    ssSeal.setSpan(styleSpan_I, 2, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //粗体
    StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
    ssSeal.setSpan(styleSpan_B, 3, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //背景
    BackgroundColorSpan bgSpan = new BackgroundColorSpan(Color.parseColor("#662B90F5"));
    ssSeal.setSpan(bgSpan, 7, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //下划线
    ssSeal.setSpan(new StrikethroughSpan(), 4,7, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //颜色
    ForegroundColorSpan colSpan = new ForegroundColorSpan(Color.CYAN);
    ssSeal.setSpan(colSpan, 9, 13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //字大小
    ssSeal.setSpan(new RelativeSizeSpan(2f), 7, 9, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    //下划线
    ssSeal.setSpan(new UnderlineSpan(), 9,13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    return ssSeal;
}
```

---
##### 6.阴影效果
![阴影.png](https://upload-images.jianshu.io/upload_images/9414344-0fb538fa56f9a588.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
android:shadowColor="#DAA0F8"
android:shadowDx="5"
android:shadowDy="5"
android:shadowRadius="10"
```

---
##### 7.字间距、行间距
![字间距.png](https://upload-images.jianshu.io/upload_images/9414344-20341d17c998b07d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
android:letterSpacing="0.5"//设置字间距
android:lineSpacingExtra //设置行间距，如”8dp”。
android:lineSpacingMultiplier//设置行间距倍数，如“1.2”，即为1.2倍行间距
```

---
##### 8.设置textView抗锯齿

```
mIdTv.getPaint().setAntiAlias(true);或
mIdTv.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
```

---
##### 9.添加HTML

```
Spanned spanned = Html.fromHtml(
        "<b>toly:</b> link to" +
                "<a href=\"http://www.toly1994.com\">Endless</a> ");
mIdTv.setMovementMethod(LinkMovementMethod.getInstance());//激活链接
mIdTv.setText(spanned);
```

![HTML.png](https://upload-images.jianshu.io/upload_images/9414344-d4fea350b2939e18.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


---
##### 10.自定义字体！！！
>准备字体ygyxsziti2.0.ttf  
在项目main文件夹里创建assets文件夹

![自定义字体.png](https://upload-images.jianshu.io/upload_images/9414344-8494a3b73238bb89.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
mIdTv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ygyxsziti2.0.ttf"));//设置字体
mIdTv.setText("张风捷特烈");
```

##### 11.设置可选择

```
android:textIsSelectable="true"
```

![可选择.png](https://upload-images.jianshu.io/upload_images/9414344-aacdb3c65d958b23.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


##### 12.设置图片填充文字内部

```
mIdTv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ygyxsziti2.0.ttf"));//设置字体
Bitmap bitmap = BitmapFactory.decodeResource(
        getResources(),
        R.mipmap.bg4);
Shader shader = new BitmapShader(
        bitmap,
        Shader.TileMode.REPEAT,
        Shader.TileMode.REPEAT);
mIdTv.getPaint().setShader(shader);
mIdTv.setText("张风捷特烈");
```
![设置贴图.png](https://upload-images.jianshu.io/upload_images/9414344-b461cb628ab39940.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>本文由张风捷特烈原创,转载请注明  
[更多安卓技术欢迎访问：](https://www.jianshu.com/c/004f3fe34c94)https://www.jianshu.com/c/004f3fe34c94   
[张风捷特烈个人网站，编程笔记请访问：](http://www.toly1994.com)http://www.toly1994.com  
你的喜欢与支持将是我最大的动力  