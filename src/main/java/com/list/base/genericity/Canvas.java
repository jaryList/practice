package com.list.base.genericity;

import java.util.ArrayList;
import java.util.List;

public class Canvas {

    public void drawAllShape(List<Shape> shapes){
        for (Shape shape : shapes){
            shape.draw(this);
        }
    }

    public void drawAllWildAny(List<?> shapes){
        for (Object object : shapes){
            Shape shape = (Shape)object;
            shape.draw(this);
        }
    }

    public void drawAllWildRange(List<? extends Shape> shapes){
        for (Shape shape : shapes){
             shape.draw(this);
        }
    }

    public static void main(String[] args) {
        List<Circle> circleList = new ArrayList<Circle>();
        Circle circle = new Circle();
        circleList.add(circle);
        Canvas canvas = new Canvas();
        //error, List<Circle>不是List<Shape>的子类
        //canvas.drawAllShape(circleList);
        canvas.drawAllWildAny(circleList);
        canvas.drawAllWildRange(circleList);
    }
}
