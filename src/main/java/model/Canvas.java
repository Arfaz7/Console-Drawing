package model;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
    private int width;
    private int height;
    private List<Shape> shapeList;

    public String[][] getContent() {
        return content;
    }

    private String[][] content;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.shapeList = new ArrayList<>();
        this.initContent();
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void print() {
        this.initShapes();

        for(int y = 0; y < this.content[0].length; y++) {
            for(int x = 0; x < this.content.length; x++) {
                System.out.print(this.content[x][y]);
            }

            System.out.print(System.lineSeparator());
        }
    }

    private void initContent() {
        this.content = new String[this.width + 2][this.height + 2];

        for(int y = 0; y < this.content[0].length; y++) {
            if(y == 0 || y == this.content[0].length -1) {
                for(int x = 0; x < this.content.length; x++) {
                    this.content[x][y] = "-";
                }
            }
            else {
                for(int x = 1; x < this.content.length-1; x++) {
                    this.content[x][y] = " ";
                }
                this.content[0][y] = "|";
                this.content[this.content.length - 1][y] = "|";
            }
        }
    }

    private void initShapes() {
        for(Shape shape: this.getShapeList()) {
            shape.draw(this);
        }
    }

    public void fillContent(int x, int y, String symbol) {
        if (this.content[x][y].equalsIgnoreCase(" ")) {
            this.content[x][y] = symbol;

            fillContent(x-1, y, symbol);
            fillContent(x+1, y, symbol);
            fillContent(x, y-1, symbol);
            fillContent(x, y+1, symbol);
        }
        else {
            return;
        }
    }
}
