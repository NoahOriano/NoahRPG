package map;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

import entity.Player;
import main.GamePanel;

import static assets.Textures.numTiles;

public class MapZone {
    public static final int zoneSize = 16; // Number of tiles width and height per zone
    public String zoneType = "GRASSLAND";
    public Tile[][] tileGrid = new Tile[zoneSize][zoneSize];
    public MapZone(){
        this("grassland");
    }
    public Color baseColor = Color.GREEN;
    TreeMap<String, Image> tileTextures;
    public MapZone(String type){
        zoneType = type;
        generateGrid();
    }
    public boolean generateGrid(){
        for(int i = 0; i < zoneSize; i++){
            for(int j = 0; j < zoneSize; j++){
                if(Objects.equals(zoneType, "grassland")){
                    if((i+j)%2 == 0){
                        tileGrid[i][j] = new Tile("grass"+(i%numTiles)+(j%numTiles));
                    }
                    else{
                        tileGrid[i][j] = new Tile("grass"+(i%numTiles)+(j%numTiles));
                    }
                }
                else if(Objects.equals(zoneType, "desert")){
                    tileGrid[i][j] = new Tile("sand"+(i%numTiles)+(j%numTiles));
                }
                else if(Objects.equals(zoneType, "road")){
                    tileGrid[i][j] = new Tile("road");
                }
            }
        }
        return true;
    }
    public boolean draw(Graphics2D gr, Player player, TreeMap<String, Image> tileTextures, int xOff, int yOff){
        this.tileTextures = tileTextures;
        int cornerX = xOff * MapGrid.ZonePixels - player.getX();
        int cornerY = yOff * MapGrid.ZonePixels - player.getY();
        int tile = GamePanel.tileSize;
        int playerX = GamePanel.screenWidth/2;
        int playerY = GamePanel.screenHeight/2;
        Image img = null;
        gr.setColor(baseColor);
        gr.fillRect(cornerX+playerX, cornerY+ playerY, zoneSize*tile,zoneSize*tile);

        int startCol = 0;
        if(cornerX < -GamePanel.screenWidth/2){
            startCol = -(cornerX+GamePanel.screenWidth/2)/GamePanel.tileSize;
        }
        if(startCol < 0) startCol = 0;

        int startRow = 0;
        if(cornerY < -GamePanel.screenHeight/2){
            startRow = -(cornerY+GamePanel.screenHeight/2)/GamePanel.tileSize;
        }
        if(startRow < 0) startRow = 0;

        int endRow = zoneSize;
        if(cornerY > GamePanel.screenWidth/2-MapGrid.ZonePixels){
            endRow = zoneSize - ((cornerY + MapGrid.ZonePixels)-GamePanel.screenHeight)/GamePanel.tileSize;
        }
        if(endRow > zoneSize) endRow = zoneSize;

        int endCol = zoneSize;
        if(cornerX > GamePanel.screenWidth/2-MapGrid.ZonePixels){
            endCol = zoneSize - ((cornerX + MapGrid.ZonePixels)-GamePanel.screenHeight)/GamePanel.tileSize;
        }
        if(endCol > zoneSize) endCol = zoneSize;

        for(int i = startCol; i < endCol; i++){
            for(int j = startRow; j < endRow; j++){
                img = null;
                gr.setColor(tileGrid[i][j].tileColor);
                img = tileTextures.get(tileGrid[i][j].tileName);
                if(img != null){
                    gr.drawImage(img,cornerX+i*tile + playerX, cornerY+j*tile + playerY,null);
                }
            }
        }
        return true;
    }

}
