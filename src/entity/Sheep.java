package entity;

import assets.TextureHandler;
import entity.player.Player;

import java.lang.Math;

// TODO: Add Comments, Extend Mob and move all mob shared methods & variables to Mob class

public class Sheep extends Mob implements Entity{

    Sheep(Player player){
        super(player);
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
