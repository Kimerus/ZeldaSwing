package jogopi;

import java.awt.Graphics;

/**
 *
 * @author Kerwin
 */
public class Drop extends Interativos {

    Sprite[] sprites = new Sprite[7];
    Sprite sprite;
    int posX, posY;
    Jogo jogo;
    String[] listaIDs = new String[7];
    String id;

    public Drop(int posX, int posY, Jogo jogo) {
        super(new Colisor(posX, posY, 50, 50, false, "loot", jogo),
                new Colisor(posX, posY, 50, 50, false, "loot", jogo));
        this.posX = posX;
        this.posY = posY;
        this.jogo = jogo;
        this.Sorteio();
        

        sprites[0] = new Sprite("misc\\extLive.png", posX, posY, 50, 50);
        sprites[1] = new Sprite("misc\\arrowX1.png", posX, posY, 50, 50);
        sprites[2] = new Sprite("misc\\arrowX3.png", posX, posY, 50, 50);
        sprites[3] = new Sprite("misc\\bombX1.png", posX, posY, 50, 50);
        sprites[4] = new Sprite("misc\\bombX3.png", posX, posY, 50, 50);
        sprites[5] = new Sprite("misc\\rupieX5.png", posX, posY, 50, 50);
        sprites[6] = new Sprite("misc\\rupieX1.png", posX, posY, 50, 50);

        listaIDs[0] = "heart";
        listaIDs[1] = "flechaX1";
        listaIDs[2] = "flechaX3";
        listaIDs[3] = "bombaX1";
        listaIDs[4] = "bombaX3";
        listaIDs[5] = "rupieX5";
        listaIDs[6] = "rupieX1";
        
        this.Sorteio();

    }

    @Override
    public void Interact() {
            this.Draw();
        if (this.hitBox.Colidiu(jogo.link.hitBox)) {
            switch (id) {
                case "heart":
                    jogo.link.vida += 1;
                    break;
                case "flechaX1":
                    jogo.link.flechas += 1;
                    break;
                case "flechaX3":
                    jogo.link.flechas += 3;
                    break;
                case "bombaX1":
                    jogo.link.bombas += 1;
                    break;
                case "bombaX3":
                    jogo.link.bombas += 3;
                    break;
                case "rupieX5":
                    jogo.link.rupies += 5;
                    break;
                case "rupieX1":
                    jogo.link.rupies += 1;
                    break;
            }
            this.existe = false;
        }
    }

    private void Sorteio() {
        boolean bomba, arco, vida;
        int i;

        bomba = jogo.link.bomba;
        arco = jogo.link.arco;
        if (jogo.link.vida < 3) {
            vida = true;
        }

        i = Math.toIntExact(Math.round(Math.random() * 10));
        switch (i) {
            /////////////////////////////   drop heart
            case 0:
            case 1:
                if (vida = true) {
                    this.sprite = sprites[0];
                    this.id = listaIDs[0];
                    break;
                }
            /////////////////////////////   drop flechas x1
            case 2:
            case 3:
                if (arco = true && jogo.link.flechas<30) {
                    this.sprite = sprites[1];
                    this.id = listaIDs[1];
                    break;
                }
            /////////////////////////////   drop flechas x3
            case 4:
                if (arco = true && jogo.link.flechas<30) {
                    this.sprite = sprites[2];
                    this.id = listaIDs[2];
                    break;
                }
            /////////////////////////////   drop bomba x1
            case 5:
            case 6:
                if (bomba = true && jogo.link.bombas<20) {
                    this.sprite = sprites[3];
                    this.id = listaIDs[3];
                    break;
                }
            /////////////////////////////   drop bomba x3
            case 7:
                if (bomba = true && jogo.link.bombas<20) {
                    this.sprite = sprites[4];
                    this.id = listaIDs[4];
                    break;
                }
            /////////////////////////////   drop rupie x5
            case 8:
                this.sprite = sprites[5];
                this.id = listaIDs[5];
                break;

            /////////////////////////////   drop rupie x1        
            default:
                this.sprite = sprites[6];
                this.id = listaIDs[6];
        }

    }

    @Override
    public void Draw() {
        Graphics g = jogo.getGraphics();
        g.drawImage(this.sprite.img, this.posX, this.posY, jogo);
    }
    
}
