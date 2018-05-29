package jogopi;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Jogo extends JPanel implements KeyListener {

    Link link = new Link(this);
    Boomer mobBoomer = new Boomer(this);
    private char pause = 'j';
    private BufferedImage spriteVida, spriteFlecha, spriteBomba;
    private JLabel vidaHUD, bombaHUD, flechaHUD;
    private final String programPath = System.getProperty("user.dir");   //caminho do programa
    private final String imgPath = programPath + "\\src\\img\\";   //pasta das imagens

    public Jogo() {
        JFrame frame = new JFrame("Mini Tennis");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener((KeyListener) this);

        vidaHUD = new JLabel();
        bombaHUD = new JLabel();
        flechaHUD = new JLabel();
        frame.getContentPane().add(vidaHUD, BorderLayout.PAGE_START);
        frame.getContentPane().add(bombaHUD, BorderLayout.NORTH);
        frame.getContentPane().add(flechaHUD, BorderLayout.LINE_END);
    }

    public void move() {
        link.move();
        mobBoomer.move();
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(mobBoomer.x, mobBoomer.y, 40, 40);
    }
    
    public boolean collision() {
        return this.getBounds().intersects(link.getBounds());
    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'p':
                if (pause == 'p') {
                    pause = 'j';
                } else if (pause == 'j') {
                    pause = 'p';
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 's':
                this.link.down = true;
                break;
            case 'w':
                this.link.up = true;
                break;
            case 'd':
                this.link.right = true;
                break;
            case 'a':
                this.link.left = true;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 's':
                this.link.down = false;
                break;
            case 'w':
                this.link.up = false;
                break;
            case 'd':
                this.link.right = false;
                break;
            case 'a':
                this.link.left = false;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        link.paint(g2d);
        if (mobBoomer.vidaB > 0) {
            mobBoomer.paint(g2d);
        }
        if (pause == 'p') {
            g2d.setColor(Color.blue);
            g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
            g2d.fillRect(this.getWidth() - 30, 5, 10, 30);
        }
        HUD(g);
    }

    public void run() {
        while (true) {
            //tick++;
            //switch (pause): 
            if (pause == 'p') {
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            } else if (pause == 'j') {
                this.move();
                //this.HUD();
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public BufferedImage SpriteUpdate(String Sprite) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(Sprite));
        } catch (IOException ex) {
            System.out.println("ERRO, Imagem: (" + Sprite + ") Nao encontrada");
        }

        return img;
    }

    public void HUD(Graphics g) {

        switch (link.vida) {
            case 0:
                spriteVida = SpriteUpdate(imgPath + "heart_0.png");   //atualiza o sprite
                g.drawImage(spriteVida, 0, 0, this);
                break;
            case 1:
                spriteVida = SpriteUpdate(imgPath + "heart_1.png");   //atualiza o sprite
                g.drawImage(spriteVida, 0, 0, this);
                break;
            case 2:
                spriteVida = SpriteUpdate(imgPath + "heart_2.png");   //atualiza o sprite
                g.drawImage(spriteVida, 0, 0, this);
                break;
            case 3:
                spriteVida = SpriteUpdate(imgPath + "heart_3.png");   //atualiza o sprite
                g.drawImage(spriteVida, 0, 0, this);
                break;
        }

        switch (link.bombas) {
            case -1:    //Não equipado
                spriteBomba = SpriteUpdate(imgPath + "bomb_-1.png");   //atualiza o sprite
                g.drawImage(spriteBomba, this.getWidth() - 150, 0, this);
                break;
            case 0:     //Equipado mas sem bombas
                spriteBomba = SpriteUpdate(imgPath + "bomb_0.png");   //atualiza o sprite
                g.drawImage(spriteBomba, this.getWidth() - 150, 0, this);
                break;
            default:    //equipado com bombas > 0
                spriteBomba = SpriteUpdate(imgPath + "bomb_1.png");   //atualiza o sprite
                g.drawImage(spriteBomba, this.getWidth() - 150, 0, this);
                break;
        }

        switch (link.flechas) {
            case -2:    //bumerangue
                spriteFlecha = SpriteUpdate(imgPath + "arrow_1.png");
                g.drawImage(spriteFlecha, this.getWidth() / 2, 0, this);
                break;
            case -1:     //nao equipado
                spriteFlecha = SpriteUpdate(imgPath + "arrow_-1.png");
                g.drawImage(spriteFlecha, this.getWidth() / 2, 0, this);
                break;
            default:
                spriteFlecha = SpriteUpdate(imgPath + "arrow_1.png");
                g.drawImage(spriteFlecha, this.getWidth() / 2, 0, this);
                break;
        }
    }
}
