package jogopi;

import java.awt.*;

public class Link {

    int x = 200;
    int y = 500;
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
    
    Colisor hitBox = new Colisor(this.x,this.y,50,50,true);
    

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
        //System.out.println("up:" + up + " down:" + down + " left:" + left + " right:" + right);

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

        if (collision()) {
            if (!dano) {
                vida--;
                dano = true;
            }
        }else{
            dano = false;
        }


//        if (y+ya > jogo.getHeight() - 50){
//            jogo.gameOver();
//        }
        x += xa;
        y += ya;

    }

    private boolean collision() {
        return this.getBounds().intersects(jogo.getBounds());
    }

}
