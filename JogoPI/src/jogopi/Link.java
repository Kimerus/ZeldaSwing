package jogopi;

import java.awt.*;
import java.io.IOException;

public class Link {

    int x = 400;
    int y = 300;
    int xa = 0;
    int ya = 0;

    int vida = 3;

    boolean bumerangue = false,
            bomba = false,
            arco = false;

    int bombas = 0, flechas = 0;

    boolean up = false,
            down = false,
            left = false,
            right = false,
            dano = false;
    Jogo jogo;

    public int rupies = 0;
    
    char lastDirec;

    public Link(Jogo jogo) {
        this.jogo = jogo;
    }

    Colisor hitBox = new Colisor(this.x, this.y, 50, 50, true,"link", this.jogo);
    
    public void paint(Graphics2D g) {
        g.setColor(Color.black);
        g.fillOval(x, y, 50, 50);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }

    public void move() throws IOException {
        //System.out.println("up:" + up + " down:" + down + " left:" + left + " right:" + right);

        if (right) {
            lastDirec = 'r';
            this.xa = 2;
        }

        if (left) {
            lastDirec = 'l';
            xa = -2;
        }

        if (down) {
            lastDirec = 'd';
            this.ya = 2;
        }

        if (up) {
            lastDirec = 'u';
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

        x += xa;
        y += ya;
        this.hitBox.UpdatePos(x, y);
        
        for (Colisor[] c : jogo.lvAtual.paredes) {
            for (Colisor col : c) {
                if (this.hitBox.Colidiu(col)) {
                    if (col.isSolido()) {
                        if (this.up || this.down) {
                            y -= ya;
                        }
                        
                        if (this.left || this.right) {
                            x -= xa;
                        }
                    }
                    if(col.transition())
                    {
                        int side = -1;
                        if(y < 50){
                            side = 0;
                        }
                        if(x > 700){
                            side = 1;
                        }
                        if(y > 500){
                            side = 2;
                        }
                        if(x < 50){
                            side = 3;
                        }
//                        System.out.println(side);
                        jogo.lvAtual = new level(jogo, col.Destino(), side);
                    }
                }
            }
        }

    }

    public void Dano(int i){
        
    }
}
