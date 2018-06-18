package jogopi;

/**
 *
 * @author Kerwin
 */
public abstract class Enemy {

    protected int vida;
    protected Colisor hitBox, visao;
    protected boolean existe;

    public Enemy(int vida, Colisor hitBox, Colisor visao) {
        this.vida = vida;
        this.hitBox = hitBox;
        this.visao = visao;
        this.existe = true;
    }
    
    public abstract void Move();

    public void Dano(int i) {
        this.vida -= i;
    }
}
