package entity;

import entity.player.Player;
import main.GamePanel;
import main.MouseHandler;
import tools.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// TODO: Add Comments
public class Blade implements Weapon{
    Player player;
    MouseHandler mouseHandler;
    int delay = 20;
    int size;
    public Blade(Player player, MouseHandler mouseHandler){
        this.player = player;
        this.mouseHandler = mouseHandler;
        this.size = 60;
    }
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
            EntityHandler.attacks.add(new Attack(20, 60, 10,
                    position, player.getTeam(),img));
            return true;
        }
        return false;
    }
}
