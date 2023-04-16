package entity;

import tools.Position;

import java.awt.*;

// TODO: Add Comments

public interface Entity {

    public final int ALLY = 0, NEUTRAL = 1, WILD = 2, ENEMY = 3;
    public int getHealth();
    public void update();
    public void draw(Graphics2D gr);
    public Position pos();
    public void damage(int dam);
    public int getTeam();
    public void die();
    public boolean isAlive();

}
