package entity;

import main.MouseHandler;

public interface Weapon {

    public boolean attack(Player player, MouseHandler mouseHandler, int charge);
}
