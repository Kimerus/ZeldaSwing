package jogopi;


public class HUD{
    private int vida,flechas,bombas;
    private boolean arco = false, bombBag = false, bomerang = false;

    public HUD() {
        this.vida = 3;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }

    public int getBombas() {
        return bombas;
    }

    public void setBombas(int bombas) {
        this.bombas = bombas;
    }

    public boolean hasArco() {
        return arco;
    }

    public void gotArco() {
        this.arco = true;
    }

    public boolean hasBombBag() {
        return bombBag;
    }

    public void gotBombBag() {
        this.bombBag = true;
    }

    public boolean hasBomerang() {
        return bomerang;
    }

    public void gotBomerang() {
        this.bomerang = true;
    }
    
}