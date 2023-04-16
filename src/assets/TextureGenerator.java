package assets;
import main.GamePanel;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class TextureGenerator {
    public static int tileSize = 48;
    public static int numTiles = 5;
    public static int width = numTiles*tileSize, height = numTiles*tileSize;
    public static int lightGreen = (255 << 24) | (119 << 16) | (255 << 8) | 89;
    public static int mediumGreen =(255 << 24) | (85 << 16) | (207 << 8) | 96;
    public static int darkGreen =(255 << 24) | (52 << 16) | (151 << 8) | 58;
    public static int yellowGreen =(255 << 24) | (99 << 16) | (185 << 8) | 13;
    public static void main(String args[]) throws IOException {
        generateSand();
        generateGrass();
    }
    static void generateGrass(){
        BufferedImage img = null;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int lightGreen = (255 << 24) | (119 << 16) | (255 << 8) | 89;
        int mediumGreen =(255 << 24) | (85 << 16) | (207 << 8) | 96;
        int darkGreen =(255 << 24) | (52 << 16) | (151 << 8) | 58;
        int yellowGreen =(255 << 24) | (99 << 16) | (185 << 8) | 13;

        // file object
        File f = null;

        // create random values pixel by pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //pixel
                int p = lightGreen;
                img.setRGB(x, y, p);
            }
        }
        boolean draw = true;
        for(int i = 0; i < 10; i++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    //pixel
                    draw = ((int) (Math.random() * 100)) <= 5;
                    double ran = Math.random();
                    int p = (255 << 24) | (52 + (int) (52 * ran) << 16) | (151 + (int) (100 * ran) << 8) | 50 + (int) (52 * ran);
                    if (draw) {
                        int end = 10 + ((int) (Math.random() * 10));
                        for (int j = 0; j < end; j++) {
                            img.setRGB(x, ((y + j * (numTiles * GamePanel.tileSize - 1))) % height, p);
                        }
                    }
                }
            }
        }

        // write image
        try {
            f = new File("src/assets/generated/random.png");
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        BufferedImage img2;
        for(int i = 0; i < numTiles; i++){
            for(int j = 0; j < numTiles; j++){
                try {
                    f = new File("src/assets/tiles/grass/"+i+j+".png");
                    img2 = img.getSubimage(i*tileSize,j*tileSize, tileSize,tileSize);
                    ImageIO.write(img2, "png", f);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            }
        }
    }
    static void generateSand(){
        BufferedImage img = null;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int lightYellow = (255 << 24) | (238 << 16) | (221 << 8) | 10;
        int mediumYellow =(255 << 24) | (202 << 16) | (185 << 8) | 8;
        int darkYellow =(255 << 24) | (177 << 16) | (141 << 8) | 8;
        int brown =(255 << 24) | (137 << 16) | (106 << 8) | 52;

        // file object
        File f = null;

        // create random values pixel by pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //pixel
                double ran = Math.random();
                int p = (255 << 24) | (130+(int)(125*ran) << 16) | ((130+(int)(125*ran) << 8)) | (5+(int)(40*ran));
                img.setRGB(x, y, p);
            }
        }
        /*
        boolean draw = true;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //pixel
                draw = true;
                int p = ;

                int r = (int)(Math.random()*8);
                if(r <= 5) draw = false;
                else if(r == 6) p = mediumYellow;
                else if(r == 7) p =  darkYellow;
                else p = brown;
                if(draw) {
                    img.setRGB(x, y, p);
                }

            }
        }
        */
        // write image
        try {
            f = new File("src/assets/generated/random.png");
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        BufferedImage img2;
        for(int i = 0; i < numTiles; i++){
            for(int j = 0; j < numTiles; j++){
                try {
                    f = new File("src/assets/tiles/sand/"+i+j+".png");
                    img2 = img.getSubimage(i*tileSize,j*tileSize, tileSize,tileSize);
                    ImageIO.write(img2, "png", f);
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            }
        }
    }
}

