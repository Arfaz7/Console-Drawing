package services;

import model.Canvas;
import model.Shape;

public interface ShapeValidator {
    boolean isOutOfBounds(Shape shape);
    boolean isValidDimension(Canvas shape);
    boolean isDiagonalLine(Shape shape);
}
