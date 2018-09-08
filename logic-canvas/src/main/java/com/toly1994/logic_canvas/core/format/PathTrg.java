//package com.toly1994.logic_canvas.core.format;
//
//import android.graphics.Path;
//
//import com.toly1994.logic_canvas.bean.Pos;
//import com.toly1994.logic_canvas.core.shape.Shape;
//import com.toly1994.logic_canvas.bean.Triangle;
//import com.toly1994.logic_canvas.core.path.BasePath;
//import com.toly1994.logic_canvas.logic.Logic;
//import com.toly1994.logic_canvas.logic.Parse;
//
///**
// * 作者：张风捷特烈<br/>
// * 时间：2018/9/4 0004:8:53<br/>
// * 邮箱：1981462002@qq.com<br/>
// * 说明：
// */
//public class PathTrg extends BasePath {
//
//    public PathTrg(Shape shape) {
//        super(shape);
//    }
//
//    @Override
//    public Path formPath(Shape shape) {
//        Path path = new Path();
//        Pos p0 = shape.mp0;
//        Pos p1 = shape.mp1;
//        Pos p2 = shape.mp2;
//
//        float a = Logic.disPos2d(p1, p2);
//        float b = Logic.disPos2d(p0, p2);
//        float c = Logic.disPos2d(p0, p1);
//
//        Triangle triangle = new Triangle(a, b, c);
//        Parse.triangle(triangle);
//
//        if (triangle != null) {
////            path.moveTo(mPos.x, -mPos.y.floatValue());
////            path.moveTo(mPos.x, -mPos.y.floatValue());
////            path.lineTo(p1.x, -p1.y.floatValue());
//            path.lineTo(p1.x, p1.y.floatValue());
//            path.lineTo(p2.x, p2.y.floatValue());
//            path.lineTo(p2.x, p2.y.floatValue());
//            return path;
//        } else {
//            return null;
//        }
//    }
//}
