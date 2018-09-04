//package com.toly1994.logic_canvas.core.format;
//
//import android.graphics.Path;
//
//import com.toly1994.logic_canvas.core.shape.Shape;
//import com.toly1994.logic_canvas.core.path.BasePath;
//import com.toly1994.logic_canvas.logic.Parse;
//
///**
// * 作者：张风捷特烈<br/>
// * 时间：2018/9/4 0004:8:53<br/>
// * 邮箱：1981462002@qq.com<br/>
// * 说明：
// */
//public class PathLine extends BasePath {
//
//    public PathLine(Shape shape) {
//        super(shape);
//    }
//
//    @Override
//    public Path formPath(Shape shape) {
//        Path path = new Path();
//
//        shape.k((float) Math.tan(shape.mang));
//        Shape line = Parse.getLine(shape);
//        path.moveTo((line.mp0.x), (line.mp0.y).floatValue());
//        path.lineTo((line.mp1.x), (line.mp1.y).floatValue());
//        return path;
//    }
//}
