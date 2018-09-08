//package com.toly1994.logiccanvaslib.array;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//
//import com.toly1994.logic_canvas.base.BaseView;
//import com.toly1994.logic_canvas.base.Pos;
//import com.toly1994.logic_canvas.core.Painter;
//import com.toly1994.logic_canvas.core.PainterEnum;
//import com.toly1994.logic_canvas.core.shape.Shape;
//import com.toly1994.logic_canvas.core.shape.ShapeCommon;
//import com.toly1994.logic_canvas.utils.TimeTest;
//
///**
// * 作者：张风捷特烈<br/>
// * 时间：2018/9/2 0002:6:53<br/>
// * 邮箱：1981462002@qq.com<br/>
// * 说明：
// */
//public class ArrowTestView extends BaseView {
//    private static final String TAG = "ArrowView";
//
//    Pos coo = v2(500, 800);
//
//
//
//    private float mAng = 0;
//
//    public ArrowTestView(Context context) {
//        super(context);
//    }
//
//    public ArrowTestView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    public void init() {
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
//
//        //基于向量绘制直线
//        final Shape commonShape = ShapeCommon.getInstance().coo(coo).b(5f).ss(Color.GRAY);
//
//
//        //new 对象：方法耗时:0.491秒
//        new TimeTest() {
//            @Override
//            protected void run() {
//                for (int i = 0; i < 100000; i++) {
////序列化深拷贝 方式------------------------------------------------------
////                    sl.formAll().ang(10f).c(200f).parsed().shape(commonShape);// 15.888秒
////                    sl.deepClone().ang(10f).c(200f).parsed().shape(commonShape);// 0.427秒
//
////浅拷贝 方式------------------------------------------------------
////                    sl.clone().ang(10f).c(200f).parsed().shape(commonShape);//浅拷贝：0.351秒
//                    coo.clone(0, 0);//浅拷贝：0.074秒
//
//
////new 方式------------------------------------------------------
////                    new Pos(0, 0);//new Pos: 0.006秒
////                    new ShapeLine().ang(10f).c(200f).parsed().shape(commonShape);//new： 0.491秒
//                }
//            }
//        };
//
//
////        painter.draw(b).cap(b);
////        ShapeLine a = (ShapeLine) sl.ang(40f).c(200f).parsed().shape(commonShape);
////        ShapeLine b = (ShapeLine) a.deepClone().coo(coo).ss(Color.RED).p(100, 100).b(3);
////
////        painter.draw(b.ss(Color.GRAY).b(5));
////        a.mv.resize(500);
////        painter.draw(b.ss(Color.BLUE).b(1)).cap(b);
//
//
////        painter.draw(a).cap(a);
//
////        CanvasUtils.drawGrid(getContext(), 50, canvas);
////        CanvasUtils.drawCoord(getContext(), coo, 50, canvas);
//    }
//
//}
