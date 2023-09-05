package ui;
import entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
public class StatWindow extends InterfaceElement{
    private ArrayList<Stat> stats;
    private int statCount;
    private Player player;
    public StatWindow(Player player){
        this(player,6);
    }
    public StatWindow(Player player, int c){
        stats = new ArrayList<Stat>();
        for(int i = 0; i < c; i++){
            stats.add(new Stat());
        }
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
        for(int i = 0; i < c && i < colors.length; i++){
            stats.get(i).baseColor = colors[i];
        }
        this.statCount = c;
        this.player = player;
    }
    @Override
    public void setPlace(int x, int y, int width, int height){
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        for(int i = 0; i < this.statCount; i++){
            stats.get(i).setPlace(x+((height)/(statCount+2)), y+((height*(1+i))/(statCount+2)),
                    width - ((width)/(statCount+2)), ((height)/(statCount+2)));
        }
    }
    @Override
    public void draw(Graphics2D gr){
        super.draw(gr);
        for(Stat e: stats){
            e.draw(gr);
        }
    }
}
