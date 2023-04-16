package ui;

import assets.TextureHandler;
import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class UserInterface {
    private ArrayList<InterfaceElement> elements = new ArrayList<InterfaceElement>();
    private Player player;
    public UserInterface(Player player){
        this.player = player;
    }
    public void generateElements(){
        PlayerHealthBar bar = new PlayerHealthBar(player);
        bar.setPlace(0,0, GamePanel.screenWidth/4, GamePanel.screenHeight/10);
        elements.add(bar);
    }
    public void draw(Graphics2D gr){
        for(InterfaceElement e : elements){
            e.draw(gr);
        }
    }
    public void update(){
        for(InterfaceElement e : elements){
            e.update();
        }
    }
}
