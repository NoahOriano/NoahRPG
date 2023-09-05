package tools;

import entity.player.Player;
import main.GamePanel;
import map.MapGrid;
import entity.EntityHandler;

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
    public Position(int col, int row, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.width = width;
        this.height = height;
        this.mass = 100;
    }
    public int xOffset(Position position){
        int colOff = position.getCol()-this.getCol();
        if(colOff > 0){
            if(Math.abs(colOff) > Math.abs(MapGrid.mapCol-colOff)){
                colOff = colOff - MapGrid.mapCol;
            }
        }
        if(colOff < 0){
            if(Math.abs(colOff) > Math.abs(MapGrid.mapCol+colOff)){
                colOff = colOff + MapGrid.mapCol;
            }
        }
        return MapGrid.ZonePixels*(colOff) + position.x - this.x;
    }
    public int yOffset(Position position){
        int rowOff = position.getRow()-this.getRow();
        if(rowOff > 0){
            if(Math.abs(rowOff) > Math.abs(MapGrid.mapCol-rowOff)){
                rowOff = rowOff - MapGrid.mapCol;
            }
        }
        if(rowOff < 0){
            if(Math.abs(rowOff) > Math.abs(MapGrid.mapCol+rowOff)){
                rowOff = rowOff + MapGrid.mapCol;
            }
        }
        return MapGrid.ZonePixels*(rowOff) + position.y - this.y;
    }
    public boolean checkCollision(Position position){
        if (Math.abs(this.xOffset(position)) < (position.getWidth() / 2 + this.width / 2)) {
            if (Math.abs(this.yOffset(position)) < (position.getHeight() / 2 + this.height / 2)) {
                return true;
            }
        }
        return false;
    }
    public boolean onScreen(Player player){
        return (Math.abs(this.xOffset(player.pos())) < GamePanel.screenWidth/2 &&
                Math.abs(this.yOffset(player.pos())) < GamePanel.screenHeight);
    }
    public boolean checkCollisionPush(Position position){
        // Need to maintain ratio of xOff to yOff somehow
        int trueWidth = (position.getWidth() + this.getWidth())/2;
        int trueHeight = (position.getHeight() + this.getHeight())/2;
        int xOff = this.xOffset(position);
        int yOff = this.yOffset(position);
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
        // Check for map wraparounds
        if(this.col < 0){
            this.col = MapGrid.mapCol-1;
        }
        if(this.col >= MapGrid.mapCol){
            this.col = 0;
        }
        if(this.row < 0){
            this.row = MapGrid.mapCol-1;
        }
        if(this.row >= MapGrid.mapRow){
            this.row = 0;
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

    public double getDistance(Position pos) {
        int xx = xOffset(pos);
        int yy = yOffset(pos);
        return Math.sqrt(xx*xx + yy*yy);
    }
    public Vector getDirection(Position pos){
        return Vector.getUnitVector(xOffset(pos), yOffset(pos));
    }
    public Position copy() {
        return new Position(this.col, this.row, this.x, this.y, this.width, this.height);
    }
    public Vector relativeDrawLocationToPlayer(){
        return new Vector(-this.xOffset(EntityHandler.player.pos()),-this.yOffset(EntityHandler.player.pos()));
    }
}
