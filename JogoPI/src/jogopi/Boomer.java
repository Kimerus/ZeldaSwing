/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogopi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Boomer{

    int x = 120;
    int y = 120;
    int xa = 1;
    int ya = 1;
    int vidaB = 1;
    boolean dano;
    Jogo jogo;

    public Boomer(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 40, 40);
    }

    public void move() {
        if (x + xa < 0) {
            xa = 1;
        }
        if (x + xa > jogo.getWidth() - 40) {
            xa = -1;
        }
        if (y + ya < 100) {
            ya = 1;
        }
        if (y + ya > jogo.getHeight() - 40) {
            ya = -1;
        }
        if (jogo.collision()) {
            vidaB--;
        }
        x = x + xa;
        y = y + ya;

    }
}
