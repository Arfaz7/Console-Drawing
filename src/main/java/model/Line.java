package model;

public class Line extends Shape {
    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Canvas canvas) {
        for(int x = this.getX1(); x < this.getX2() +1; x++) {
            for(int y = this.getY1(); y < this.getY2() +1; y++) {
                canvas.getContent()[x][y] = "x";
            }
        }
    }
}
