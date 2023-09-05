package entity;

import assets.TextureHandler;
import entity.player.Player;

import java.awt.*;

// TODO: Add Comments, Extend Mob and move all mob shared methods & variables to Mob class

public class SmallSheep extends Mob implements Entity{

    SmallSheep(Player player){
        super(player);
        this.pos().setWidth(16);
        this.pos().setHeight(16);
        this.speed = 10;
        this.updateGraphic();
    }
    @Override
    public void update(){
        move();
        checkCollisions();
    }
    @Override
    public void updateGraphic(){
        img = TextureHandler.get("sheep");
        img = img.getScaledInstance(16,16, Image.SCALE_DEFAULT);
    }
    @Override
    public void move(){
        moveDelta --;
        if(moveDelta <= 0){
            moveDelta = (int) (Math.random()*100);
            moving = !moving;
            position.setDx((int)(Math.random()*(speed + 0.5)) - speed/2);
            position.setDy((int)(Math.random()*(speed + 0.5)) - speed/2);
        }
        if(moving){
            position.move();
        }
    }

    @Override
    public void die() {
        player.gainMoney(2);
        player.gainXp(1);
    }
}
