package entity;

import main.GamePanel;
import map.MapGrid;
// TODO: Add Comments
public class Position {
    private int x;
    private int y;
    private int col;
    private int row;
    private int width;
    private int height;
    private int mass;
    private int dx;
    private int dy;
    private boolean moving;
    Position(int col, int row, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        this.mass = 100;
    }
    public int xOffset(Position position){
        return (position.getX()-this.getX() + MapGrid.ZonePixels*(position.getCol() - this.getCol()));
    }
    public int yOffset(Position position){
        return (position.getY()-this.getY() + MapGrid.ZonePixels*(position.getRow() - this.getRow()));
    }
    public boolean checkCollision(Position position){
        int xOff = position.getX() + position.getWidth() / 2 - this.getX() - this.getWidth()/2+ (position.getCol() - this.getCol()) * MapGrid.ZonePixels;
        int yOff = position.getY() + position.getHeight() / 2 - this.getY() - this.getHeight()/2+ (position.getRow() - this.getRow()) * MapGrid.ZonePixels;
        if (Math.abs(xOff) < (position.getWidth() / 2 + this.width / 2)) {
            if (Math.abs(yOff) < (position.getHeight() / 2 + this.height / 2)) {
                return true;
            }
        }
        return false;
    }
    public boolean onScreen(Player player){
        int xOff = this.getX()-player.getX() + MapGrid.ZonePixels*(this.getCol()- player.getCol()) - this.width/2 + player.pos().width/2;
        int yOff = this.getY()-player.getY() + MapGrid.ZonePixels*(this.getRow()- player.getRow()) - this.height/2 + player.pos().height/2;
        return (Math.abs(xOff) < GamePanel.screenWidth/2 && Math.abs(yOff) < GamePanel.screenHeight);
    }
    public boolean checkCollisionPush(Position position){
        // Need to maintain ratio of xOff to yOff somehow
        int xOff = position.getX() + position.getWidth() / 2 - this.getX() - this.getWidth()/2 + (position.getCol() - this.getCol()) * MapGrid.ZonePixels;
        int yOff = position.getY() + position.getHeight() / 2 - this.getY() - this.getHeight()/2+ (position.getRow() - this.getRow()) * MapGrid.ZonePixels;
        if (Math.abs(xOff) < (position.getWidth() / 2 + this.width / 2)) {
            if (Math.abs(yOff) < (position.getHeight() / 2 + this.height / 2)) {
                double moveThis = position.mass/(this.mass +0.0+ position.mass);
                double moveThat = this.mass/(this.mass +0.0+ position.mass);
                if(Math.abs(xOff) > Math.abs(yOff)){ // If it is a side collision
                    if(xOff <= 0 ){ // If this position right of other
                        this.move((int) (moveThis*((position.getWidth() / 2.0 + this.width / 2.0)+xOff)),0);
                        position.move((int) (-moveThat*((position.getWidth() / 2.0 + this.width / 2.0)+xOff)),0);
                    }
                    if(xOff >= 0){ // If this position left of other
                        this.move((int) (-moveThis*((position.getWidth() / 2.0 + this.width / 2.0)-xOff)),0);
                        position.move((int) (moveThat*((position.getWidth() / 2.0 + this.width / 2.0)-xOff)),0);
                    }
                }
                else{ // Else is top or bottom collision
                    if(yOff <= 0){ // If this position below of other
                        this.move(0,(int) (moveThis*((position.getHeight() / 2.0 + this.height / 2.0)+yOff)));
                        position.move(0,(int) (-moveThat*((position.getHeight() / 2.0 + this.height / 2.0)+yOff)));
                    }
                    if(yOff >= 0){ // If this position above of other
                        this.move(0,(int) (-moveThis*((position.getHeight() / 2.0 + this.height / 2.0)-yOff)));
                        position.move(0,(int) (moveThat*((position.getHeight() / 2.0 + this.height / 2.0)-yOff)));
                    }
                }

            }
        }
        return false;
    }
    public void move(){
        this.move(this.dx, this.dy);
    }
    public void move(int x, int y){
        this.x += x;
        this.y += y;
        if(this.x < 0){
            this.col --;
            this.x += MapGrid.ZonePixels;
        }
        if(this.x > MapGrid.ZonePixels){
            this.col ++;
            this.x -= MapGrid.ZonePixels;
        }
        if(this.y < 0){
            this.row --;
            this.y +=MapGrid.ZonePixels;
        }
        if(this.y > MapGrid.ZonePixels){
            this.row ++;
            this.y -=MapGrid.ZonePixels;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }
    public void speedUp(int dx, int dy) {
        this.dx += dx;
        this.dy += dy;
    }
}
