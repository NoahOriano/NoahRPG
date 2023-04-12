package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean w, s, a, d, l,p;
    @Override
    public void keyTyped(KeyEvent e) {
        //Unused
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            w = true;
        }
        if(code == KeyEvent.VK_S){
            s = true;
        }
        if(code == KeyEvent.VK_A){
            a = true;
        }
        if(code == KeyEvent.VK_D){
            d = true;
        }
        if(code == KeyEvent.VK_L){
            l = true;
        }
        if(code == KeyEvent.VK_P){
            p = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            w = false;
        }
        if(code == KeyEvent.VK_S){
            s = false;
        }
        if(code == KeyEvent.VK_A){
            a = false;
        }
        if(code == KeyEvent.VK_D){
            d = false;
        }
        if(code == KeyEvent.VK_L){
            l = false;
        }
        if(code == KeyEvent.VK_P){
            p = false;
        }
    }
}
