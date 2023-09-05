package entity;

import entity.player.Player;

import java.awt.*;
import java.util.ArrayList;

// TODO: Add Comments

public class EntityHandler{
    public static Player player;
    public static ArrayList<Entity> entities = new ArrayList<Entity>();;
    public static ArrayList<Attack> attacks = new ArrayList<Attack>();
    public EntityHandler(){

    }
    public static void start(){
        entities.add(player);
        for(int i = 0; i < 20; i++){
            entities.add(new Sheep(player));
        }
        for(int i = 0; i < 5; i++){
            entities.add(new Golem(player));
        }
        for(int i = 0; i < 5; i++){
            entities.add(new SmallSheep(player));
        }
    }
    public static void setPlayer(Player p){
        player = p;
    }
    public static void addEntity(Mob e){
        entities.add(e);
    }
    public static void update(){
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

    public static void draw(Graphics2D gr){
        for(Entity e: entities){
            e.draw(gr);
        }
        for(Attack e: attacks){
            e.draw(gr);
        }
    }
}
