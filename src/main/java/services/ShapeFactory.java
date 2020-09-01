package services;

import model.Canvas;
import model.Line;
import model.Rectangle;
import model.Shape;

public class ShapeFactory {

    private static ShapeValidator shapeValidator;

    private static ShapeValidator getShapeValidator() {
        if(shapeValidator == null) {
            shapeValidator = new ShapeValidatorImpl();
        }

        return shapeValidator;
    }

    public static Canvas getCanvas(int width, int height) {
        Canvas canvas = new Canvas(width,height);
        if(getShapeValidator().isValidDimension(canvas))
            return canvas;

        return null;
    }

    public static Shape getShape(char shapeType, int x1, int y1, int x2, int y2) throws ClassNotFoundException {
        Shape resultShape;
        boolean isValidShape = false;

        switch (shapeType) {
            case 'L':
                resultShape = new Line(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
                isValidShape = !getShapeValidator().isDiagonalLine(resultShape) && !getShapeValidator().isOutOfBounds(resultShape);
                break;

            case 'R':
                resultShape =  new Rectangle(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
                isValidShape = !getShapeValidator().isOutOfBounds(resultShape);
                break;

            default:
                throw new ClassNotFoundException("Shape " + shapeType + " doesn't exist");
        }

        if (isValidShape)
            return resultShape;

        throw new IndexOutOfBoundsException("Shape dimensions are out of bounds");
    }
}
