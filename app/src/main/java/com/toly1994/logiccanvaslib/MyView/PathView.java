package com.toly1994.logiccanvaslib.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.toly1994.logic_canvas.base.Pos;
import com.toly1994.logic_canvas.core.Painter;
import com.toly1994.logic_canvas.core.PainterEnum;
import com.toly1994.logic_canvas.core.shape.Shape;
import com.toly1994.logic_canvas.core.shape.ShapeCommon;
import com.toly1994.logic_canvas.core.shape.ShapeStar;
import com.toly1994.logic_canvas.utils.CanvasUtils;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/9/2 0002:6:53<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class PathView extends View {


    private Context mContext;

    public PathView(Context context) {
        super(context);
        mContext = context;
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Painter painter = PainterEnum.INSTANCE.getInstance(canvas);
        Path path = new Path();// 创建Path
        Path src = new Path();

        path.addRect(200,300,-100,-100, Path.Direction.CW);
        Path path1 = new ShapeStar().num(6).R(100f).r(50f).formPath();
        src.addPath(path1);



        path.addPath(src,0,200);

        Shape shapeEmpty = new ShapeCommon(path)
                .coo(300f, 400f).b(0f).fs(Color.RED);

        painter.draw(shapeEmpty);

        CanvasUtils.drawGrid(mContext, 50, canvas);
        CanvasUtils.drawCoord(mContext, new Pos(300, 400), 50, canvas);
    }

    private void t2(Path path) {
        //小矩形：顺时针
        path.addRect(-400, 0, 600, 200, Path.Direction.CW);
        //大矩形：逆时针
        path.addRect(-200, -200, 400, 400, Path.Direction.CCW);

//        mPath.setFillType(Path.FillType.EVEN_ODD);
//        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
//        mPath.setFillType(Path.FillType.WINDING);
//        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        // 添加大正方形

    }


    @NonNull
    private Path t1(Path path) {

        path.lineTo(200, -200);// lineTo
        path.lineTo(200, 0);
        path.close();
        return path;
    }
}
