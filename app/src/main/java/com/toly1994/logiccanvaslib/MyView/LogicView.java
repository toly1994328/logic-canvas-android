package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Painter;
import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.ZCanvas;
import com.toly1994.logic_canvas.simple.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class LogicView extends View {


    private Context mContext;

    public LogicView(Context context) {
        super(context);
        mContext = context;
    }

    public LogicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final ZCanvas zCanvas = new ZCanvas(canvas);

        for (int i = 5; i < 10; i++) {
            zCanvas.drawRegularPolygon(
                    new Painter()
                            .num(i).R(80f)
                            .b(4f)
                            .p(new Pos(20+210*(i-5),-20)));//内接圆半径

            zCanvas.drawRegularStar(
                    new Painter()
                            .num(i).R(80f)
                            .b(4f)
                            .p(new Pos(20+210*(i-5),-220)));//内接圆半径

        }

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(20f)
//                .r(10f))
//        ;


//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .p(new Pos(200, -100)));//位移X,Y

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .coo(new Pos(600, 200))//设置坐标系
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .p(new Pos(200, -100))//设置坐标系
//                .ss(Color.RED)//描边颜色
//                .b(5f)//描边线条粗细
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(new Pos(600, 200))
//                .rot(90f)//设置旋转
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(new Pos(600, 200))
//                .sx(1.5f)
//                .sy(1.5f)
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(new Pos(600, 200))
//                .a(new Pos(100, 100))
//                .sx(1.5f)
//                .sy(1.5f)
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(20f)
//                .r(10f)
//                .fs(Color.YELLOW)
//                .dp(mContext)
//        );

//        zCanvas.drawNStar(new Painter()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .fs(Color.YELLOW)
//                .dp(mContext));
        //绘制网格
        CanvasUtils.drawGrid(mContext,50,canvas);
//        //绘制坐标系
//        CanvasUtils.drawCoord(mContext, new Pos(600, 200), 10, 50, canvas);
    }
}
