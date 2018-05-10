package jogopi;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Jogo extends JPanel implements KeyListener {

    private char pause = 'j';
    //int tick = 0;
    Link link = new Link(this);

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
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(790, 2, 1, 596);
    }

    public void move() {
        link.move();
    }

    public void gameOver() {
        //FAzer delay para sumir o ultimo quadrado de vida
//        int delay=-1;
//        if (delay != tick){
//            delay = tick;
//        }
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Sem impressao.
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
        g2d.setColor(Color.pink);
        g2d.fillRect(790, 2, 1, 596);
        switch (link.vida) {
//            case 0:
//                this.gameOver();
//                break;
            case 1:
                g2d.setColor(Color.red);
                g2d.fillRect(15, 5, 10, 10);
                break;
            case 2:
                g2d.setColor(Color.red);
                g2d.fillRect(15, 5, 10, 10);
                g2d.fillRect(30, 5, 10, 10);
                break;
            case 3:
                g2d.setColor(Color.red);
                g2d.fillRect(15, 5, 10, 10);
                g2d.fillRect(30, 5, 10, 10);
                g2d.fillRect(45, 5, 10, 10);
                break;
        }
        if (pause == 'p') {
            g2d.setColor(Color.blue);
            g2d.fillRect(this.getWidth() - 15, 5, 10, 30);
            g2d.fillRect(this.getWidth() - 30, 5, 10, 30);
        }
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
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

}
