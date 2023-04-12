package entity;

import java.awt.*;
import java.util.ArrayList;

// TODO: Add Comments

public class EntityHandler{
    public Player player;
    public ArrayList<Entity> entities;
    public ArrayList<Attack> attacks;
    public EntityHandler(){
        this.entities = new ArrayList<Entity>();
        this.attacks = new ArrayList<Attack>();
    }
    public void start(){
        entities.add(player);
        for(int i = 0; i < 20; i++){
            entities.add(new Sheep(player));
        }
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void addEntity(Mob e){
        entities.add(e);
    }
    public void update(){
        for(Entity e: entities){
            e.update();
        }
        for(Attack e: attacks){
            e.update();
        }
        int i = 0;
        while(i < entities.size()){
            if(entities.get(i).getHealth() <= 0){
                if(entities.get(i).isAlive()) entities.get(i).die();
                entities.remove(i);
            }
            else i++;
        }
        i = 0;
        while(i < attacks.size()){
            if(attacks.get(i).getDuration() <= 0)attacks.remove(i);
            else i++;
        }
    }

    public void draw(Graphics2D gr){
        for(Entity e: entities){
            e.draw(gr);
        }
        for(Attack e: attacks){
            e.draw(gr);
        }
    }
}
