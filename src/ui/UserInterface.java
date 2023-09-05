package ui;

import entity.player.Player;
import main.GamePanel;
import main.MouseHandler;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class UserInterface {
    private ArrayList<InterfaceElement> elements = new ArrayList<InterfaceElement>();
    private KeyListener keyListener;
    private MouseHandler mouseHandler;
    private Player player;
    public UserInterface(Player player){
        this.player = player;
    }
    public void generateElements(){
        PlayerHealthBar bar = new PlayerHealthBar(player);
        bar.setPlace(0,0, GamePanel.screenWidth/4, GamePanel.screenHeight/10);
        elements.add(bar);
        StatWindow window = new StatWindow(player);
        window.setPlace(0, GamePanel.screenHeight/10,GamePanel.screenWidth/4, GamePanel.screenHeight/4);
        elements.add(window);
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
