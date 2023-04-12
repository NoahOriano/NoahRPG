package entity;

import main.GamePanel;
import map.MapGrid;

import java.awt.*;

public class Attack {
    int duration;
    int strength;
    Player player;
    Position position;
    int delay;
    int delta = 0;
    int team;
    int dx, dy;
    public EntityHandler entityHandler;
    Image img;
    public Attack(int duration, int delay, int strength, Player player, Position position,
                  EntityHandler entityHandler, int team, Image img){
        this.img = img;
        this.duration = duration;
        this.delay = delay;
        this.team = team;
        this.strength = strength;
        this.position = position;
        this.player = player;
        this.entityHandler = entityHandler;
        this.dx = 0;
        this.dy = 0;
    }
    public void update(){
        move();
        if(delta == 0){
            checkColisions();
            delta = delay;
        }
        else delta--;
        duration--;
    }
    public void move(){
        position.move(dx, dy);
    }
    public void draw(Graphics2D gr){
        gr.setColor(Color.BLACK);
        int relX = position.getX() - player.getX() +(position.getCol()-player.getCol())* MapGrid.ZonePixels;
        int relY = position.getY() - player.getY() +(position.getRow()-player.getRow())* MapGrid.ZonePixels;
        if(img != null){
            gr.drawImage(img, (GamePanel.screenWidth/2-position.getWidth()/2) + relX,
                    (GamePanel.screenHeight/2-position.getWidth()/2) + relY, position.getWidth(), position.getHeight(),null);
        }
        else {
            gr.fillRect((GamePanel.screenWidth / 2 - position.getWidth() / 2) + relX,
                    (GamePanel.screenHeight / 2 - position.getWidth() / 2) + relY, position.getWidth(), position.getHeight());
        }
    }
    public void checkColisions(){
        int i = 0;
        int xOff = 0;
        int yOff = 0;
        while(i < entityHandler.entities.size()){
            Entity entity = entityHandler.entities.get(i);
            if(!(entity.getTeam() == this.team)) {
                if(position.checkCollision(entity.pos())){
                    entity.damage(this.strength);
                }
            }
            i++;
        }
    }
    public int getDuration(){
        return this.duration;
    }
}
