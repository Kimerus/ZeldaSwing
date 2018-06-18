package jogopi;

import java.awt.Graphics;

/**
 * @author Kerwin
 */
public abstract class Interativos {

    Colisor hitBox, zonaInteracao;
    boolean existe;
    private Jogo jogo;
    private Sprite sprite;
    private int posX, posY;

    public Interativos(Colisor hitBox, Colisor zonaInteracao) {
        this.hitBox = hitBox;
        this.zonaInteracao = zonaInteracao;

        this.existe = true;
    }

    public abstract void Interact();

    /**
     *  Graphics g = jogo.getGraphics();
     *  g.drawImage(this.sprite.img, this.posX, this.posY, jogo);
     */
    public abstract void Draw();

}
