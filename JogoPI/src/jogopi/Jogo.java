package jogopi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.RenderingHints;
import javax.swing.*;
import java.awt.event.KeyListener;

public class Jogo extends JPanel implements KeyListener {

<<<<<<< HEAD
    private boolean pause;
=======
    Link link = new Link(this);
    Boomer mobBoomer = new Boomer(this);
    private char pause = 'm', menuSelect = 'p';
    private BufferedImage spriteVida, spriteFlecha, spriteBomba;
    private JLabel vidaHUD, bombaHUD, flechaHUD;
    private final String programPath = System.getProperty("user.dir");   //caminho do programa
    private final String imgPath = programPath + "\\src\\img\\";   //pasta das imagens
>>>>>>> parent of 1a050b9... agora

    public Jogo() {
        JFrame frame = new JFrame("Mini Tennis");
        frame.add(this);
<<<<<<< HEAD
        frame.setSize(300, 300);
=======
        frame.setSize(800, 600);
>>>>>>> parent of 1a050b9... agora
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener((KeyListener) this);
<<<<<<< HEAD
=======

        vidaHUD = new JLabel();
        bombaHUD = new JLabel();
        flechaHUD = new JLabel();
        frame.getContentPane().add(vidaHUD, BorderLayout.PAGE_START);
        frame.getContentPane().add(bombaHUD, BorderLayout.NORTH);
        frame.getContentPane().add(flechaHUD, BorderLayout.LINE_END);
>>>>>>> parent of 1a050b9... agora
    }
    Ball ball = new Ball(this);

    public void move() {
<<<<<<< HEAD
        ball.move();
=======
        link.move();
        mobBoomer.move();
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(0);
    }

//    public Rectangle getBounds() {
//        return new Rectangle(mobBoomer.x, mobBoomer.y, 40, 40);
//    }
    public boolean collision() {
        return mobBoomer.getBounds().intersects(link.getBounds());
>>>>>>> parent of 1a050b9... agora
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Sem impressao.
        switch (e.getKeyChar()) {
            case 'p':
                pause = !pause;
                break;
<<<<<<< HEAD
=======
            case 'l':
                if (menuSelect == 'p') {
                    pause = 'j';
                } else if (menuSelect == 'e') {
                    System.exit(0);
                }
                break;
            case 'm':
                System.exit(0);
                break;
>>>>>>> parent of 1a050b9... agora
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 's':
<<<<<<< HEAD
                this.ball.down = true;
                break;
            case 'w':
                this.ball.up = true;
=======
                if (pause == 'j') {
                    this.link.down = true;
                } else if (pause == 'm') {
                    switch (menuSelect) {
                        case 'p':
                            menuSelect = 'e';
                            break;
                        case 'e':
                            menuSelect = 'p';
                            break;
                    }
                }
                break;
            case 'w':
                if (pause == 'j') {
                    this.link.up = true;
                } else if (pause == 'm') {
                    switch (menuSelect) {
                        case 'p':
                            menuSelect = 'e';
                            break;
                        case 'e':
                            menuSelect = 'p';
                            break;
                    }
                }
>>>>>>> parent of 1a050b9... agora
                break;
            case 'd':
                this.ball.right = true;
                break;
            case 'a':
                this.ball.left = true;
                break;
        }
<<<<<<< HEAD
        

=======
>>>>>>> parent of 1a050b9... agora
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()){
            case 's':
                this.ball.down = false;
                break;
            case 'w':
                this.ball.up = false;
                break;
            case 'd':
                this.ball.right = false;
                break;
            case 'a':
                this.ball.left = false;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
<<<<<<< HEAD
        ball.paint(g2d);
        if (pause) {
            ball.paint(g2d);
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
            g2d.fillRect(this.getWidth() - 30, 5, 10, 30);

        }
    }    

    public void run() {
        while (true) {
            if (pause) {
=======
        if (pause == 'j') {
            link.paint(g2d);
            if (mobBoomer.vidaB > 0) {
                mobBoomer.paint(g2d);
            }
            HUD(g);
        } else if (pause == 'p') {
            link.paint(g2d);
            if (mobBoomer.vidaB > 0) {
                mobBoomer.paint(g2d);
            }
            HUD(g);
            g2d.setColor(Color.blue);
            g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
            g2d.fillRect(this.getWidth() - 30, 5, 10, 30);
        } else if (pause == 'm') {
            Font fnt = new Font("arial", Font.ITALIC, 90);
            g.setFont(fnt);
            g.setColor(Color.GREEN);
            g.drawString("Zelda", 290, 180);

            fnt = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.BLUE);
            g.drawString("Play", 375, 330);
            g2d.setColor(Color.white);
            g.drawRect(290, 300, 227, 40);

            g.setColor(Color.RED);
            g.drawString("Exit", 380, 470);
            g2d.setColor(Color.white);
            g.drawRect(290, 440, 227, 40);

            switch (menuSelect) {
                case 'p':
                    g2d.setColor(Color.white);
                    g2d.fillArc(270, 305, 30, 30, 135, 90);
                    break;
                case 'e':
                    g2d.setColor(Color.white);
                    g2d.fillArc(270, 445, 30, 30, 135, 90);
                    break;
            }
        }
    }

    public void run() {
        while (true) {
            //tick++;
            //switch (pause): 
            if (pause == 'j') {
                this.move();
>>>>>>> parent of 1a050b9... agora
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
<<<<<<< HEAD
            } else {
                this.move();
=======
            } else if (pause == 'p') {
                //this.HUD();
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            } else if (pause == 'm') {
>>>>>>> parent of 1a050b9... agora
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

<<<<<<< HEAD
=======
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
>>>>>>> parent of 1a050b9... agora
}
