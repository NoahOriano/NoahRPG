package entity;

import main.GamePanel;
import map.MapGrid;
import tools.Position;

import java.awt.*;
// TODO: Add Comments
public class Attack {
    int durability = 1;
    int duration = 5;
    int strength = 1;
    Position position;
    int delay = 10;
    int delta = 0;
    int team = -1;
    Image img;
    public Attack(){

    }
    public Attack(int duration, int delay, int strength, Position position,
                  int team, Image img){
        this.img = img;
        this.duration = duration;
        this.delay = delay;
        this.team = team;
        this.strength = strength;
        this.position = position;
    }
    public void update(){
        move();
        if(durability <= 0){
            duration = 1;
        }
        if(delta == 0){
            checkCollisions();
            delta = delay;
        }
        else delta--;
        duration--;
    }
    public void move(){
        position.move();
    }
    public void draw(Graphics2D gr){
        gr.setColor(Color.BLACK);
        int relX = position.getX() - EntityHandler.player.getX() +(position.getCol()-EntityHandler.player.getCol())* MapGrid.ZonePixels;
        int relY = position.getY() - EntityHandler.player.getY() +(position.getRow()-EntityHandler.player.getRow())* MapGrid.ZonePixels;
        if(img != null){
            gr.drawImage(img, (GamePanel.screenWidth/2-position.getWidth()/2) + relX,
                    (GamePanel.screenHeight/2-position.getWidth()/2) + relY, position.getWidth(), position.getHeight(),null);
        }
        else {
            gr.fillRect((GamePanel.screenWidth / 2 - position.getWidth() / 2) + relX,
                    (GamePanel.screenHeight / 2 - position.getWidth() / 2) + relY, position.getWidth(), position.getHeight());
        }
    }
    public void checkCollisions(){
        int i = 0;
        int xOff = 0;
        int yOff = 0;
        while(i < EntityHandler.entities.size()){
            Entity entity = EntityHandler.entities.get(i);
            if(!(entity.getTeam() == this.team)) {
                if(position.checkCollision(entity.pos())){
                    entity.damage(this.strength);
                    durability--;
                }
            }
            i++;
        }
    }
    public int getDuration(){
        return this.duration;
    }
}
