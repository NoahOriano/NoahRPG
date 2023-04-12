package entity;

import assets.TextureHandler;
import main.GamePanel;
import map.MapGrid;

import java.awt.*;
import java.lang.Math;

// TODO: Add Comments, Extend Mob and move all mob shared methods & variables to Mob class

public class Sheep implements Entity {
    public boolean alive = true;
    public int team;
    public int health;
    public Position position;
    public int speed;
    public int dy, dx;
    public boolean moving = false;
    public double moveDelta = 0;
    public Color color;
    private Player player;
    Image img;

    Sheep(Player player){
        this.team = WILD;
        this.health = 10;
        this.speed = 3;
        position = new Position(player.getCol()+1, player.getRow()+1, player.getX(), player.getY(),
                GamePanel.tileSize, GamePanel.tileSize);
        this.player = player;
        this.position.setWidth(GamePanel.tileSize);
        this.position.setHeight(GamePanel.tileSize);
        this.dy = 0;
        this.dx = 0;
        int ran = (int)(Math.random()*4);
        if(ran == 0) this.color = Color.MAGENTA;
        if(ran == 1) this.color = Color.PINK;
        if(ran == 2) this.color = Color.ORANGE;
        if(ran == 3) this.color = Color.RED;
        updateGraphic();
    }
    @Override
    public void update(){
        move();
        checkCollisions();
    }
    @Override
    public void draw(Graphics2D gr){
        int relX = (position.getCol() - player.getCol())*MapGrid.ZonePixels + position.getX() - player.getX();
        int relY = (position.getRow() - player.getRow())*MapGrid.ZonePixels + position.getY() - player.getY();
        if(img != null){
            gr.drawImage(img, GamePanel.screenWidth/2 + relX - position.getWidth()/2,
                    GamePanel.screenHeight/2 + relY - position.getHeight()/2, null);
        }
        else {
            gr.setColor(this.color);
            gr.fillRect(GamePanel.screenWidth / 2 + relX, GamePanel.screenHeight / 2 + relY, position.getWidth(), position.getHeight());
        }
    }
    public void updateGraphic(){
        img = TextureHandler.get("sheep");
    }
    public void checkCollisions(){
        for(Entity e : player.entityHandler.entities){
            if(e != this)position.checkCollisionPush(e.pos());
        }
    }
    public void move(){
        moveDelta --;
        if(moveDelta <= 0){
            moveDelta = (int) (Math.random()*100);
            moving = !moving;
            dx = (int)(Math.random()*(speed + 0.5)) - speed/2;
            dy = (int)(Math.random()*(speed + 0.5)) - speed/2;
        }
        if(moving){
            position.move(dx, dy);
        }
    }
    public int getHealth(){
        return this.health;
    }

    @Override
    public Position pos(){
        return this.position;
    }

    @Override
    public void damage(int dam) {
        this.health -= dam;
    }

    @Override
    public int getTeam() {
        return team;
    }

    @Override
    public void die() {
        player.gainMoney(2);
        player.gainXp(1);
    }

    @Override
    public boolean isAlive() {
        if(alive){
            alive = false;
            return true;
        }
        return false;
    }
}
