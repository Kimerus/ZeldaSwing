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

    private boolean pause;

    public Jogo() {
        JFrame frame = new JFrame("Mini Tennis");
        frame.add(this);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener((KeyListener) this);
    }
    Ball ball = new Ball(this);

    public void move() {
        ball.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Sem impressao.
        switch (e.getKeyChar()) {
            case 'p':
                pause = !pause;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 's':
                this.ball.down = true;
                break;
            case 'w':
                this.ball.up = true;
                break;
            case 'd':
                this.ball.right = true;
                break;
            case 'a':
                this.ball.left = true;
                break;
        }
        

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
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            } else {
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
