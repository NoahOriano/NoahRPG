package assets;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class TextureHandler {
    public static TreeMap<String, Image> tileTextures = new TreeMap<String, Image>();
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
                    img = img.getScaledInstance(GamePanel.tileSize,GamePanel.tileSize,img.SCALE_DEFAULT);
                    tileTextures.put("grass"+i+j, img);
                }
                catch (IOException e) {
                    System.out.println("Error");
                }
                try {
                    img = ImageIO.read(new File("src/assets/tiles/sand/"+i+j+".png"));
                    img = img.getScaledInstance(GamePanel.tileSize,GamePanel.tileSize,img.SCALE_DEFAULT);
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
            img = img.getScaledInstance(GamePanel.tileSize,GamePanel.tileSize,img.SCALE_DEFAULT);
            tileTextures.put("player", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
        try {
            img = ImageIO.read(new File("src/assets/entities/sheep/right.png"));
            img = img.getScaledInstance(GamePanel.tileSize,GamePanel.tileSize,img.SCALE_DEFAULT);
            tileTextures.put("sheep", img);
        }
        catch (IOException e) {
            System.out.println("Error");
        }
    }
    public static Image get(String name){
        return tileTextures.get(name);
    }
}
