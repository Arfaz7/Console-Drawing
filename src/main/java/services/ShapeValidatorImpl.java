package services;

import app.Application;
import model.Canvas;
import model.Shape;

public class ShapeValidatorImpl implements ShapeValidator {

    @Override
    public boolean isOutOfBounds(Shape shape) {
        return ((shape.getX1() <= 0 || shape.getX1() > Application.canvas.getWidth()) ||
                (shape.getY1() <= 0 || shape.getY1() > Application.canvas.getHeight()) ||
                (shape.getX2() <= 0  || shape.getX2() > Application.canvas.getWidth()) ||
                (shape.getY2() <= 0 || shape.getY2() > Application.canvas.getHeight()));
    }

    @Override
    public boolean isValidDimension(Canvas canvas) {
        return (canvas.getWidth() > 0  && canvas.getHeight() > 0);
    }

    @Override
    public boolean isDiagonalLine(Shape shape) {
        if (shape.getClass().getName().equalsIgnoreCase("Line"))
            return (shape.getX1() != shape.getX2() && shape.getY1() != shape.getY2());

        return false;
    }
}
