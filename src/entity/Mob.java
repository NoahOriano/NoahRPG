package entity;

import main.GamePanel;
import map.MapGrid;
import tools.Position;
import tools.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

// TODO: Add Comments, Convert into base method for all other entities to extend

public class Mob implements Entity {
    public boolean alive = true;
    public int team;
    public int health;
    public Position position;
    public int speed;
    public boolean moving = false;
    public double moveDelta = 0;
    public Color color;
    protected Player player;
    Image img;

    Mob(Player player){
        this.team = WILD;
        this.health = 10;
        this.speed = 3;
        position = new Position(player.getCol()+1, player.getRow()+1, player.getX(), player.getY(),
                GamePanel.tileSize, GamePanel.tileSize);
        this.player = player;
        this.position.setWidth(GamePanel.tileSize);
        this.position.setHeight(GamePanel.tileSize);
        this.position.setDy(0);
        this.position.setDx(0);
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
        Vector relDraw = this.position.relativeDrawLocationToPlayer();
        int relX =(int)relDraw.list[0];
        int relY =(int)relDraw.list[1];
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

    }
    public void checkCollisions(){
        for(Entity e : EntityHandler.entities){
            if(e != this)position.checkCollisionPush(e.pos());
        }
    }
    public void move(){

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
