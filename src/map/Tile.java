package map;

import java.awt.*;
import java.util.Objects;

public class Tile {
    public static int CLEAR = 0, WALL = 1, ROUGH = 2, WATER = 3;
    public Color tileColor;
    public String tileName;
    public int type;
    public Tile(){
        this("grass");
    }
    public Tile(String tileName){
        this.tileName = tileName;
        if(Objects.equals(tileName, "grass")){
            tileColor = Color.GREEN;
        }
        if(Objects.equals(tileName, "darkgrass")){
            tileColor = Color.GRAY;
        }
        if(Objects.equals(tileName, "road")){
            tileColor = Color.DARK_GRAY;
        }
        if(Objects.equals(tileName, "road")){
            tileColor = Color.YELLOW;
        }
    }
}
