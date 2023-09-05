package assets;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class TextureHandler {
    public static TreeMap<String, Image> tileTextures = new TreeMap<String, Image>();
    public static int baseBarHeight = GamePanel.screenHeight/10;
    public static int baseBarWidth = GamePanel.screenWidth/3;
    public static void start(){
        generateTileTextures();
        generateEntityTextures();
    }
    public static void generateTileTextures(){
        Image img = null;
        for(int i = 0; i < TextureGenerator.numTiles; i++){
            for(int j = 0; j < TextureGenerator.numTiles; j++){
                try {
                    img = ImageIO.read(new File("src/assets/tiles/grass/"+i+j+".png"));
                    tileTextures.put("grass"+i+j, img);
                }
                catch (IOException e) {
                    System.out.println("Error");
                }
                try {
                    img = ImageIO.read(new File("src/assets/tiles/sand/"+i+j+".png"));
                    tileTextures.put("sand"+i+j, img);
                }
                catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }
    public static void generateEntityTextures(){
        Image img = null;
        try {
            img = ImageIO.read(new File("src/assets/entities/player/right.png"));
            tileTextures.put("player", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/entities/sheep/right.png"));
            tileTextures.put("sheep", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/entities/golem/front.png"));
            tileTextures.put("golem", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/equipment/projectiles/boulder.png"));
            tileTextures.put("boulder", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/ui/baseStatBar.png"));
            tileTextures.put("baseStatBar", img.getScaledInstance(baseBarWidth, baseBarHeight, Image.SCALE_DEFAULT));
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/ui/darkRedBarFill.png"));
            tileTextures.put("darkRedBarFill", img.getScaledInstance(baseBarWidth, baseBarHeight, Image.SCALE_DEFAULT));
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/ui/healthBarFill.png"));
            tileTextures.put("healthBarFill", img.getScaledInstance(baseBarWidth, baseBarHeight, Image.SCALE_DEFAULT));
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }
    public static Image get(String name){
        return tileTextures.get(name);
    }
}
