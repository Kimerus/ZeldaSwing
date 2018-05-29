/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogopi;

import java.awt.*;

public class Link {

    int x = 50;
    int y = 500;
    int xa = 0;
    int ya = 0;
    int vida = 3;
    int bombas = -1;
    int flechas = -1;
    boolean up = false,
            down = false,
            left = false,
            right = false,
            dano = false;
    Jogo jogo;

    public Link(Jogo jogo) {
        this.jogo = jogo;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 50, 50);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }

    public void move() {
        if (right) {
            this.xa = 2;
        }

        if (left) {
            xa = -2;
        }

        if (down) {
            this.ya = 2;
        }

        if (up) {
            ya = -2;
        }

        if (!left && !right) {
            xa = 0;
        }

        if (!up && !down) {
            ya = 0;
        }

        if (vida == 0) {
            jogo.gameOver();
        }

        if (jogo.collision()) {
            if (!dano) {
                vida--;
                dano = true;
            }
        } else {
            dano = false;
        }

        x += xa;
        y += ya;

    }

}
