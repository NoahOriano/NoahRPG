///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Noah Oriano
// KeyHandler
// 4-16-2023
// Handles the input of keys into the game by controlling boolean values, simply holds whether each key
// Is currently pressed or switched on
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;

public class KeyHandler implements KeyListener {
    private class KeyValue{
        public boolean key;
        public boolean swtch;
        public boolean lag;
        public void pressed(){
            key = true;
            if(!lag) swtch = !swtch;
            lag = true;
        }
        public void released(){
            key = false;
            lag = false;
        }
    }
    public TreeMap<String, KeyValue> keys;

    public KeyHandler(){
        keys = new TreeMap<String,KeyValue>();
        for(int i = 0; i < 26; i++){
            keys.put(String.valueOf((char)('a' + i)), new KeyValue());
        }
        String[] list = {};
        for(int i = 0; i < list.length; i++){
            keys.put(list[i], new KeyValue());
        }
    }
    public void update(){

    }
    @Override
    public void keyTyped(KeyEvent e) {
        //Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            keys.get("w").pressed();
        }
        if(code == KeyEvent.VK_S){
            keys.get("s").pressed();
        }
        if(code == KeyEvent.VK_A){
            keys.get("a").pressed();
        }
        if(code == KeyEvent.VK_D){
            keys.get("d").pressed();
        }
        if(code == KeyEvent.VK_L){
            keys.get("l").pressed();
        }
        if(code == KeyEvent.VK_P){
            keys.get("p").pressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            keys.get("w").released();
        }
        if(code == KeyEvent.VK_S){
            keys.get("s").released();
        }
        if(code == KeyEvent.VK_A){
            keys.get("a").released();
        }
        if(code == KeyEvent.VK_D){
            keys.get("d").released();
        }
        if(code == KeyEvent.VK_L){
            keys.get("l").released();
        }
        if(code == KeyEvent.VK_P){
            keys.get("p").released();
        }
    }
    public boolean getPressed(String key){
        return keys.get(key).key;
    }
    public boolean getSwitch(String key){
        return keys.get(key).swtch;
    }
}
