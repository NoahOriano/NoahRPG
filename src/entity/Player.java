package entity;

import assets.TextureHandler;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import tools.Position;

import java.awt.*;

// TODO: Add Comments, Implement Stats, Handle Player Death Behavior
public class Player implements Entity {

    public KeyHandler keyHandler;
    public MouseHandler mouseHandler;
    private int money = 0;
    private int xp = 0;
    private int health = 100;
    private int maxHealth = 100;
    private Position position;
    private int playerSpeed;
    private int attackDelta;
    private Weapon weapon;
    private int team = ALLY;
    Image img;

    public Player(KeyHandler keyHandler, MouseHandler mouseHandler){
        this.keyHandler = keyHandler;
        this.setPlayerValues();
        this.mouseHandler = mouseHandler;
        attackDelta = 0;
        weapon = new Blade(this,mouseHandler);
        position.setMass(100);
        img = TextureHandler.get("player");
    }

    public void setPlayerValues(){
        this.setPlayerValues(100,100,8);
    }

    @Override
    public void update(){
        move();
        if(mouseHandler.mouseClicked){
            if(weapon.attack(this, mouseHandler, attackDelta)) attackDelta = 0;
            mouseHandler.mouseClicked = false;
        }
        attackDelta++;
    }

    @Override
    public void draw(Graphics2D gr) {
        gr.setColor(Color.WHITE);
        if(img!= null){
            gr.drawImage(img, GamePanel.screenWidth/2 - GamePanel.tileSize/2,
                    GamePanel.screenHeight/2 - GamePanel.tileSize/2, null);
        }
        else {
            gr.fillRect(GamePanel.screenWidth / 2 - GamePanel.tileSize / 2, GamePanel.screenHeight / 2 - GamePanel.tileSize / 2,
                    GamePanel.tileSize, GamePanel.tileSize);
        }
    }
    public void move(){
        // MOVE ***************************************************************************
        if(keyHandler.w){
            position.move(0, -playerSpeed);
        }
        if(keyHandler.a){
            position.move(-playerSpeed,0);
        }
        if(keyHandler.s){
            position.move(0, playerSpeed);
        }
        if(keyHandler.d){
            position.move(playerSpeed,0);
        }
    }

    public void setPlayerValues(int x, int y, int speed){
        // Set player's default position
        position = new Position(1, 1, x, y, GamePanel.tileSize, GamePanel.tileSize);
        playerSpeed = speed;
    }
    public void gainMoney(int money){
        this.money += money;
    }
    public void gainXp(int xp){
        this.xp += xp;
    }
    public int getMoney(){
        return this.money;
    }
    public int getXp() {
        return xp;
    }

    public int getX(){
        return position.getX();
    }
    public int getCol(){
        return position.getCol();
    }
    public int getY(){
        return position.getY();
    }
    public int getRow(){
        return position.getRow();
    }
    public int getSpeed(){
        return playerSpeed;
    }
    public int getHealth(){
        return this.health;
    }

    @Override
    public Position pos(){
        return position;
    }

    @Override
    public void damage(int dam) {
        this.health -= dam;
    }
    public int getTeam(){ return team; }

    @Override
    public void die() {

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
