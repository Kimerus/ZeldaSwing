/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogopi;

import java.awt.Graphics2D;

public class Ball {
    
    int x = 200;
    int y = 200;
    int xa = 0;
    int ya = 0;
    boolean up = false,
            down = false,
            left = false,
            right = false;
    Jogo jogo;
    
    public Ball(Jogo jogo){
        this.jogo = jogo;
    }
    
    public void paint(Graphics2D g){
        g.fillOval(x, y, 30,30);
    }
    
    public void move(){
        System.out.println("up:" + up + " down:" + down + " left:" + left + " right:" + right);
        
        if (right)
            this.xa = 1;
        
        if (left)
            xa = -1;
        
        if (down)
            this.ya = 1;
        
        if (up)
            ya = -1;
        
        if (!left && !right)
            xa = 0;
        
        if (!up && !down) 
            ya = 0;
        
        x += xa;
        y += ya;
    }
    
}
