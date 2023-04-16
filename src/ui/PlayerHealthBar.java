package ui;

import entity.Player;

import java.awt.*;

public class PlayerHealthBar extends InterfaceElement{
    private Player player;
    public PlayerHealthBar(Player player){
        this.player = player;
    }
    @Override
    public void draw(Graphics2D gr) {
        super.draw(gr);
        int edge = Math.min(width, height)/8;
        gr.setColor(Color.RED);
        gr.fillRect(x + edge,y+edge,
                width - 2*edge,height - 2*edge);
        gr.setColor(Color.GREEN);
        gr.fillRect(x + edge,y+edge,
                width*player.getHealth()/player.getMaxHealth() - 2*edge,height - 2*edge);
    }
}

