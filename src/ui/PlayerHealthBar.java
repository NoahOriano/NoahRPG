package ui;

import assets.TextureHandler;
import entity.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerHealthBar extends InterfaceElement{
    private Player player;
    Image bar;
    Image fill;
    Image healthFill;
    public PlayerHealthBar(Player player){
        this.player = player;
        bar = TextureHandler.get("baseStatBar");
        fill = TextureHandler.get("darkRedBarFill");
        healthFill = TextureHandler.get("healthBarFill");

    }
    @Override
    public void draw(Graphics2D gr) {
        int edge = Math.min(width, height)/8;
        if(bar != null){
            gr.drawImage(bar, x, y, null);
        }
        else{
            super.draw(gr);
        }
        if(fill != null){
            gr.drawImage(fill, x, y, null);
        }
        else{
            gr.setColor(Color.RED);
            gr.fillRect(x + edge,y+edge,
                    width - 2*edge,height - 2*edge);
        }
        if(healthFill != null && 4 < healthFill.getWidth(null) * player.getHealth()/(double)player.getMaxHealth()){
            Image img = healthFill.getScaledInstance((int)(width* (player.getHealth()/(double)player.getMaxHealth())),height, Image.SCALE_DEFAULT);
            gr.drawImage(img, x, y, null);
        }
        else{
            gr.setColor(Color.RED);
            gr.setColor(Color.GREEN);
            gr.fillRect(x + edge,y+edge,
                    width*player.getHealth()/player.getMaxHealth() - 2*edge,height - 2*edge);
        }
    }
}

