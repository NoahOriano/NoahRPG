package entity;

import assets.TextureHandler;
import tools.Position;
import tools.Vector;

import java.awt.*;

// TODO: Add Comments, Extend Mob and move all mob shared methods & variables to Mob class

public class Golem extends Mob implements Entity{
    int attackDelay = 40;
    int attackDelta = 0;
    int sightRange = 400;
    int attackRange = 200;
    int projectileSpeed = 10;
    int projectileSize = 40;
    Golem(Player player){
        super(player);
        this.pos().setWidth(98);
        this.pos().setHeight(98);
        this.pos().setMass(500);
        this.speed = 2;
        this.updateGraphic();
        this.health =  100;
        this.team = ENEMY;
    }
    @Override
    public void update(){
        attack();
        move();
        checkCollisions();
    }

    private void attack() {
        if(position.getDistance(player.pos()) < attackRange){
            attackDelta ++;
            if(attackDelta>attackDelay){
                attackDelta = 0;
                Image aImg = TextureHandler.get("boulder");
                Position pos = this.position.copy();
                pos.setWidth(projectileSize);
                pos.setHeight(projectileSize);
                Vector dir = this.position.getDirection(player.pos());
                pos.setDy((int)(dir.list[1] * projectileSpeed));
                pos.setDx((int)(dir.list[0] * projectileSpeed));
                Attack ack = new Attack(60,3,10, pos, this.team, aImg);
                EntityHandler.attacks.add(ack);
            }
        }
    }

    @Override
    public void updateGraphic(){
        img = TextureHandler.get("golem");
        img = img.getScaledInstance(position.getWidth(), position.getHeight(), Image.SCALE_DEFAULT);
    }
    @Override
    public void move(){
        if(position.getDistance(player.pos()) < sightRange){
            Vector direction = position.getDirection(player.pos());
            position.setDx((int)(direction.list[0] * speed));
            position.setDy((int)(direction.list[1] * speed));
            position.move();
        }
        else {
            moveDelta--;
            if (moveDelta <= 0) {
                moveDelta = (int) (Math.random() * 100);
                moving = !moving;
                position.setDx((int) (Math.random() * (speed + 0.5)) - speed / 2);
                position.setDy((int) (Math.random() * (speed + 0.5)) - speed / 2);
            }
            if (moving) {
                position.move();
            }
        }
    }

    @Override
    public void die() {
        player.gainMoney(5);
        player.gainXp(100);
    }
}
