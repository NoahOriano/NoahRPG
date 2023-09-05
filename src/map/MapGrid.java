package map;

import entity.player.Player;
import main.GamePanel;

import java.awt.*;

public class MapGrid {
    public static final int mapCol = 3;
    public static final int mapRow = 3;
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
        int colOnScreen = 1+GamePanel.screenWidth/ZonePixels;
        int rowOnScreen = 1+GamePanel.screenHeight/ZonePixels;
        int col = player.getCol();
        int row = player.getRow();
        for(int i = -colOnScreen; i < colOnScreen; i++){
            for(int j = -rowOnScreen; j < rowOnScreen; j++){
                zoneGrid[(col+i+mapCol*2)%mapCol][(row+j+mapCol*2)%mapCol].draw(graphics, player, i, j);

            }
        }
    }

}
