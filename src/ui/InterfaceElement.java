package ui;

import java.awt.*;

public class InterfaceElement {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Color baseColor = Color.GRAY;
    public InterfaceElement(){

    }
    public void setPlace(int x, int y, int width, int height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public void update() {

    }
    public void draw(Graphics2D gr){
        gr.setColor(baseColor);
        gr.fillRect(x, y, width, height);
    }
}
