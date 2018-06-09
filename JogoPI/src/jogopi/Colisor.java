package jogopi;

import java.awt.*;

/**
 * @author kerwin rocha pereira
 */
public class Colisor {
    
    private int posX, posY, width, height;
    private boolean solido;

    public boolean isSolido() {
        return solido;
    }

    public void setSolido(boolean solido) {
        this.solido = solido;
    }

    public Colisor(int posX, int posY, int width, int height, boolean solido) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.solido = solido;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, this.width, this.height);
    }
    
    public boolean Colidiu(Colisor outro){
        return this.getBounds().intersects(outro.getBounds());
   }
}
