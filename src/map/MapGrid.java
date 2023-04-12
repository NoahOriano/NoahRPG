package map;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.awt.*;
import java.util.TreeMap;
import assets.Textures;
import org.w3c.dom.Text;

public class MapGrid {
    public final int mapCol = 84;
    public final int mapRow = 84;
    public static final int ZonePixels = MapZone.zoneSize * GamePanel.baseTileSize * GamePanel.scale;
    public MapZone[][] zoneGrid = new MapZone[mapCol][mapRow];
    public TreeMap<String, Image> tileTextures = new TreeMap<String, Image>();

    public MapGrid(){
        for(int i = 0; i < mapCol; i++){
            for(int j = 0; j < mapRow; j++){
                if((i+j)%2 == 0){
                    zoneGrid[i][j] = new MapZone("grassland");
                }
                else{
                    zoneGrid[i][j] = new MapZone("desert");
                }
            }
        }
        generateTextures();
    }
    public void draw(Graphics2D graphics, Player player){
        int col = player.getCol();
        int row = player.getRow();
        for(int i = 0; i < mapCol; i++){
            for(int j = 0; j < mapRow; j++){
                if(col <= i+1+GamePanel.screenWidth/ZonePixels && col >= i-1-GamePanel.screenWidth/ZonePixels){
                    if(row <= j+1+GamePanel.screenHeight/ZonePixels && row >= j-1-GamePanel.screenHeight/ZonePixels){
                        zoneGrid[i][j].draw(graphics, player,tileTextures, i-col, j-row);
                    }
                }
            }
        }
    }
    public void generateTextures(){
        Image img = null;
        for(int i = 0; i < Textures.numTiles; i++){
            for(int j = 0; j < Textures.numTiles; j++){
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

}
