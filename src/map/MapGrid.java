package map;

import entity.Player;
import main.GamePanel;

import java.lang.Math;
import java.awt.*;

public class MapGrid {
    public final int mapCol = 84;
    public final int mapRow = 84;
    public static final int ZonePixels = MapZone.zoneSize * GamePanel.baseTileSize * GamePanel.scale;
    public MapZone[][] zoneGrid = new MapZone[mapCol][mapRow];

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
    }
    public void draw(Graphics2D graphics, Player player){
        int col = player.getCol();
        int row = player.getRow();
        for(int i = 0; i < mapCol; i++){
            for(int j = 0; j < mapRow; j++){
                if(col <= i+1+GamePanel.screenWidth/ZonePixels && col >= i-1-GamePanel.screenWidth/ZonePixels){
                    if(row <= j+1+GamePanel.screenHeight/ZonePixels && row >= j-1-GamePanel.screenHeight/ZonePixels){
                        zoneGrid[i][j].draw(graphics, player, i-col, j-row);
                    }
                }
            }
        }
    }

}
