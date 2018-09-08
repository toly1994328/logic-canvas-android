//package com.toly1994.logiccanvaslib.array;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//
//import com.toly1994.logic_canvas.base.BaseView;
//import com.toly1994.logic_canvas.base.Pos;
//import com.toly1994.logic_canvas.core.Painter;
//import com.toly1994.logic_canvas.core.PainterEnum;
//import com.toly1994.logic_canvas.core.shape.Shape;
//import com.toly1994.logic_canvas.core.shape.ShapeRect;
//import com.toly1994.logic_canvas.go.NumGo;
//import com.toly1994.logic_canvas.logic.Logic;
//import com.toly1994.logic_canvas.utils.ColUtils;
//
///**
// * 作者：张风捷特烈<br/>
// * 时间：2018/9/2 0002:6:53<br/>
// * 邮箱：1981462002@qq.com<br/>
// * 说明：
// */
//public class RunCharView extends BaseView {
//
//    Pos coo = v2(500, 600);
//    private NumGo mRunNum;
//
//    private long lastFrameTime;
//    private Pos mVA;
//    private Pos mVB;
//    private Pos mSA;
//    private Pos mSB;
//    private Shape mFA;
//    private Shape mFB;
//
//    private int mBColor = Color.RED;
//    private int mAColor = Color.BLUE;
//
//    public RunCharView(Context context) {
//        super(context);
//    }
//
//    public RunCharView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    public void init() {
//        //创建一个水平速度向量对象,并赋值水平速度为100px/s,竖直速度50px/s
//        mVA = v2(100, -50);
//        mVB = v2(100, 40);
//        //创建一个位移向量对象
//        mSA = v2(0, 0);
//        mSB = v2(0, 0);
//
//        mRunNum = new NumGo(false, 0, 100000).setOnUpdate(new NumGo.OnUpdate() {
//            @Override
//            public void onUpdate(float rate) {
//                long frameTime = System.currentTimeMillis();
//                float detaT = frameTime - lastFrameTime;//时间变化量
//                Pos detaS = mVA.dotC(detaT / 500);//位移增量=速度*时间变化量
//                Pos detaSB = mVB.dotC(detaT / 500);//位移增量=速度*时间变化量
//                mSA = mSA.add(detaS);//位移总量 = 位移增量和
//                mSB = mSB.add(detaSB);//位移总量 = 位移增量和
//
//                if (mSA.x > 200 || mSA.x < -200) {
//                    mVA = mVA.refX();
//                    mAColor = ColUtils.randomRGB();
//                }
//                if (mSA.y > 200 || mSA.y < -200) {
//                    mVA = mVA.refY();
//                    mAColor = ColUtils.randomRGB();
//                }
//                if (mSB.x > 200 - 60 || mSB.x < -200 - 60) {
//                    mVB = mVB.refX();
//                    mBColor = ColUtils.randomRGB();
//                }
//                if (mSB.y > 200 + 60 || mSB.y < -200 + 60) {
//                    mVB = mVB.refY();
//                    mBColor = ColUtils.randomRGB();
//                }
//
//                if (Logic.disPos2d(mFA.mcoo.add(mFA.mp).add(mSA),
//                        (mFB.mcoo.add(mFB.mp).add(mSB))) < 20) {
////                    mBColor = ColUtils.randomRGB();
//                }
//
//                invalidate();
//                lastFrameTime = frameTime;//时刻更新
//            }
//        });
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
//
//        mFA = sa.deepClone().r(20).ang(360).fs(mAColor).p(mSA).coo(coo);
//        mFB = sa.deepClone().r(20).ang(360).fs(mBColor).p(mSB).coo(coo.add(60, 60));
//        Shape fr = new ShapeRect().x(440).y(440).r(1).fs(0x66B8F8F0).coo(coo).p(-220, -220);
//
//        painter.draw(fr, mFB, mFA);
//
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        parseEvent(event);
//
//        setOnEventListener(new OnEventListener() {
//            @Override
//            public void down() {
//
//            }
//
//            @Override
//            public void up() {
//                lastFrameTime = System.currentTimeMillis();
//                mRunNum.go();
//            }
//
//            @Override
//            public void move() {
//
//            }
//        });
//
//        return super.onTouchEvent(event);
//    }
//}
