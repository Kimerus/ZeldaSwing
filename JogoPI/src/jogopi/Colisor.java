package jogopi;

import java.awt.*;

/**
 * @author kerwin rocha pereira
 */
public class Colisor {
    
    private int posX, posY, width, height;
    private final String id;
    private boolean solido;
    private Jogo jogo;
    
    public Colisor(int posX, int posY, int width, int height, boolean solido, String id, Jogo jogo) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.solido = solido;
        this.id = id;
    }
    
    public boolean isSolido() {
        return solido;
    }

    public void setSolido(boolean solido) {
        this.solido = solido;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.posX, this.posY, this.width, this.height);
    }
    
    public boolean Colidiu(Colisor outro){
        return this.getBounds().intersects(outro.getBounds());
   }
    
    public void UpdatePos(int x, int y){
       this.posX = x;
       this.posY = y;
    }
    
    public void UpdateSize(int width, int height){
       this.width = width;
       this.height = height;
    }
    
    public void UpdateState(boolean solido){
       this.solido = solido;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public boolean transition()
    {
        return this.id.startsWith("area")||this.id.startsWith("dungeon");
    }
    
    public String Destino(){
        return this.id;
    }

    public void Dano(int i) {
        //overwrite
    }
}
