package jogopi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Kerwin Rocha Pereira
 */
public class Sprite {
    
    private final String programPath = System.getProperty("user.dir");   //caminho do programa
    private final String imgPath = programPath + "\\src\\img\\";   //pasta das imagens
    
    private int posX, posY, width, heigth;
    Colisor hitBox;
    BufferedImage img;
    String imgName;
    boolean solido;

    public Sprite(String imgPath, int posX, int posY, int width, int heigth ) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.heigth = heigth;
        this.imgName = imgPath;
        this.img = SpriteUpdate(this.imgPath+ this.imgName);
    }

    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public Colisor getHitBox() {
        return hitBox;
    }

    public void setHitBox(Colisor hitBox) {
        this.hitBox = hitBox;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getImgPath() {
        return imgPath;
    }


private BufferedImage SpriteUpdate(String Sprite) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Sprite));
        } catch (IOException ex) {
            System.out.println("ERRO, Imagem: (" + Sprite + ") Nao encontrada");
        }

        return img;
    }
    
}
