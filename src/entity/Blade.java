package entity;

import main.GamePanel;
import main.MouseHandler;
import map.MapGrid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Blade implements Weapon{
    Player player;
    MouseHandler mouseHandler;
    EntityHandler entityHandler;
    int delay = 5;
    int size;
    public Blade(Player player, MouseHandler mouseHandler, EntityHandler entityHandler){
        this.player = player;
        this.mouseHandler = mouseHandler;
        this.entityHandler = entityHandler;
        this.size = 60;
    }
    /*
    @Override
    public boolean attack(Player player, MouseHandler mouseHandler, int charge) {
        if(charge > delay){
            Position position = new Position((player.getCol() + (mouseHandler.mouseX- GamePanel.screenWidth/2)/ MapGrid.ZonePixels),
                    (player.getRow() + (mouseHandler.mouseY- GamePanel.screenHeight/2)/ MapGrid.ZonePixels),
                    (player.getX() + (mouseHandler.mouseX- GamePanel.screenWidth/2)%MapGrid.ZonePixels),
                    (player.getY() + (mouseHandler.mouseY- GamePanel.screenHeight/2)%MapGrid.ZonePixels));
            entityHandler.attacks.add(new Attack(30, 10, 10,
                    player,position,10,10, entityHandler));
            return true;
        }
        return false;
    }
    */
    public boolean attack(Player player, MouseHandler mouseHandler, int charge) {
        if(charge > delay){
            //src/assets/equipment/sword/swordDownLeft.png
            Image img =null;
            String name = "src/assets/equipment/sword/sword";
            int xDir = mouseHandler.mouseX - GamePanel.screenWidth/2;
            int yDir = mouseHandler.mouseY - GamePanel.screenHeight/2;
            if(yDir < 0){
                name = name + "Up";
            }
            else name = name + "Down";
            if(xDir < 0){
                name = name + "Left";
            }
            else name = name +"Right";
            name += ".png";
            try {
                img = ImageIO.read(new File(name));
                img = img.getScaledInstance(GamePanel.tileSize,GamePanel.tileSize,img.SCALE_DEFAULT);
            }
            catch (IOException e) {
                System.out.println("Error");
            }
            Position position = new Position(player.getCol(),
                    player.getRow(),
                    player.getX() + (int)(size*(xDir/Math.sqrt(xDir*xDir + yDir*yDir))),
                    player.getY() + (int)(size*(yDir/Math.sqrt(yDir*yDir + xDir*xDir))),
                    size, size);
            entityHandler.attacks.add(new Attack(10, 60, 10,
                    player,position,entityHandler, player.getTeam(),img));
            return true;
        }
        return false;
    }
}
