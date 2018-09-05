package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.bean.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.shape.ShapeStar;
import com.toly1994.logic_canvas.simple.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class LogicView extends View {


    private Context mContext;
    private Pos pos = Pos.init();

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
        final Painter painter = new Painter(canvas);

        for (int i = 5; i < 10; i++) {
            painter.draw(
                    new ShapeStar(ShapeStar.MODE_POLYGON)
                            .num(i).R(80f)
                            .b(4f)
                            .p(pos.form(20+210*(i-5),-20)));//内接圆半径

            painter.draw(
                    new ShapeStar(ShapeStar.MODE_REGULAR)
                            .num(i).R(80f)
                            .b(4f)
                            .p(pos.form(20+210*(i-5),-220)));//内接圆半径
        }

//        //测试1：绘制外接圆半径100,内接圆半径50的5角星
//        painter.draw(
//                new ShapeStar()
//                        .num(6)//角的个数,数字任意
//                        .R(100f)//外接圆半径
//                        .r(50f));////内接圆半径

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .p(pos.form(200, -100)));//位移X,Y

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .coo(pos.form(600, 200))//设置坐标系
//        );

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .p(pos.form(200, -100))
//                .ss(Color.RED)//描边颜色
//                .b(5f)//描边线条粗细
//        );

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(pos.form(600, 200))
//                .rot(90f)//设置旋转
//        );

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(pos.form(600, 200))
//                .sx(1.5f)
//                .sy(1.5f)
//        );

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .ss(Color.RED)
//                .coo(pos.form(600, 200))
//                .a(pos.form(100, 100))
//                .sx(1.5f)
//                .sy(1.5f)
//        );

//        painter.draw(new ShapeStar()
//                .num(5)
//                .R(100f)
//                .r(50f)
//                .coo(pos.form(600, 200))
//                .fs(Color.YELLOW)
//        );

        //绘制网格
//        CanvasUtils.drawGrid(mContext,50,canvas);
//        //绘制坐标系
        CanvasUtils.drawCoord(mContext, pos.form(600, 200),  50, canvas);
    }
}
