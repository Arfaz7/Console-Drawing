package model;

public class Rectangle extends Shape {

    public Rectangle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Canvas canvas) {
        for(int x = this.getX1(); x < this.getX2() +1; x++) {
            canvas.getContent()[x][this.getY1()] = "x";
            canvas.getContent()[x][this.getY2()] = "x";
        }

        for(int y = this.getY1(); y < this.getY2() +1; y++) {
            canvas.getContent()[this.getX1()][y] = "x";
            canvas.getContent()[this.getX2()][y] = "x";
        }
    }
}
