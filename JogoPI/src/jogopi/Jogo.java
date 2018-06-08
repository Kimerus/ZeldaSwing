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

public class Jogo extends JPanel implements KeyListener
{

   private char pause = 'j';
   Link link = new Link( this );
   private BufferedImage spriteVida, spriteFlecha, spriteBomba;
   private JLabel vidaHUD, bombaHUD, flechaHUD;
   private final String programPath = System.getProperty( "user.dir" );   //caminho do programa
   private final String imgPath = programPath + "\\src\\img\\";   //pasta das imagens
   private int pauseSelect = 0;
   level lvAtual = new level( this, "area1" );

   public Jogo() throws IOException
   {
      JFrame frame = new JFrame( "Mini Tennis" );
      frame.add( this );
      frame.setSize( 800, 625 );
      frame.setVisible( true );
      frame.setResizable( false );
      frame.setLocationRelativeTo( null );
      this.setBackground( Color.black );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.addKeyListener( ( KeyListener ) this );

      vidaHUD = new JLabel();
      bombaHUD = new JLabel();
      flechaHUD = new JLabel();
      frame.getContentPane().add( vidaHUD, BorderLayout.PAGE_START );
      frame.getContentPane().add( bombaHUD, BorderLayout.NORTH );
      frame.getContentPane().add( flechaHUD, BorderLayout.LINE_END );

   }

   @Override
   public Rectangle getBounds()
   {
      return new Rectangle( 790, 2, 1, 596 );
   }

   public void move()
   {
      link.move();
   }

   public void gameOver()
   {
      JOptionPane.showMessageDialog( this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION );
      System.exit( ABORT );
   }

   @Override
   public void keyTyped( KeyEvent e )
   {

      switch ( e.getKeyChar() )
      {
         case 'p':
            if ( pause == 'p' )
            {
               pause = 'j';
            } else if ( pause == 'j' )
            {
               pause = 'p';
            }
            break;

         //DEBUG
         case 'j':
            this.link.bomba = true;
            break;
         case 'k':
            this.link.arco = true;
            break;
         case 'l':
            this.link.bumerangue = true;
            break;

         case 'u':
            this.link.bomba = false;
            break;
         case 'i':
            this.link.arco = false;
            break;
         case 'o':
            this.link.bumerangue = false;
            break;
         // Fim DEBUG
      }

      if ( pause == 'p' )
      {
         switch ( e.getKeyChar() )
         {
            case 's':
               pauseSelect += 2;
               if ( pauseSelect > 3 )
               {
                  pauseSelect = 0;
               }
               break;
            case 'w':
               pauseSelect -= 2;
               if ( pauseSelect < 0 )
               {
                  pauseSelect += 4;
               }
               break;
            case 'd':
               pauseSelect += 1;
               if ( pauseSelect > 3 )
               {
                  pauseSelect = 0;
               }
               break;
            case 'a':
               pauseSelect -= 1;
               if ( pauseSelect < 0 )
               {
                  pauseSelect += 2;
               }
               break;
         }
      }
   }

   @Override
   public void keyPressed( KeyEvent e )
   {
      switch ( e.getKeyChar() )
      {
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
   public void keyReleased( KeyEvent e )
   {
      switch ( e.getKeyChar() )
      {
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
   public void paint( Graphics g )
   {
      super.paint( g );
      Graphics2D g2d = ( Graphics2D ) g;
      g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

      DrawBG( g );
      link.paint( g2d );
      g2d.setColor( Color.pink );
      g2d.fillRect( 790, 2, 1, 596 );
      if ( pause == 'p' )
      {
         g2d.setColor( Color.blue );
         g2d.fillRect( this.getWidth() - 15, 5, 10, 30 );
         g2d.fillRect( this.getWidth() - 30, 5, 10, 30 );
         DrawPauseMenu( g );
      }
      HUD( g );
   }

   public void run()
   {
      while ( true )
      {
         if ( pause == 'p' )
         {
            this.repaint();
            try
            {
               Thread.sleep( 10 );
            } catch ( InterruptedException ex )
            {
            }
         } else if ( pause == 'j' )
         {
            this.move();
            //this.HUD();
            this.repaint();
            try
            {
               Thread.sleep( 10 );
            } catch ( InterruptedException ex )
            {
            }
         }
      }
   }

   public BufferedImage SpriteUpdate( String Sprite )
   {
      BufferedImage img = null;
      try
      {
         img = ImageIO.read( new File( Sprite ) );
      } catch ( IOException ex )
      {
         System.out.println( "ERRO, Imagem: (" + Sprite + ") Nao encontrada" );
      }

      return img;
   }

   public void HUD( Graphics g )
   {

      switch ( link.vida )
      {
         case 0:
            spriteVida = SpriteUpdate( imgPath + "heart_0.png" );   //atualiza o sprite
            g.drawImage( spriteVida, 0, 0, this );
            break;
         case 1:
            spriteVida = SpriteUpdate( imgPath + "heart_1.png" );   //atualiza o sprite
            g.drawImage( spriteVida, 0, 0, this );
            break;
         case 2:
            spriteVida = SpriteUpdate( imgPath + "heart_2.png" );   //atualiza o sprite
            g.drawImage( spriteVida, 0, 0, this );
            break;
         case 3:
            spriteVida = SpriteUpdate( imgPath + "heart_3.png" );   //atualiza o sprite
            g.drawImage( spriteVida, 0, 0, this );
            break;
      }

      switch ( link.bombas )
      {
         case -1:    //Não equipado
            spriteBomba = SpriteUpdate( imgPath + "bomb_-1.png" );   //atualiza o sprite
            g.drawImage( spriteBomba, this.getWidth() - 150, 0, this );
            break;
         case 0:     //Equipado mas sem bombas
            spriteBomba = SpriteUpdate( imgPath + "bomb_0.png" );   //atualiza o sprite
            g.drawImage( spriteBomba, this.getWidth() - 150, 0, this );
            break;
         default:    //equipado com bombas > 0
            spriteBomba = SpriteUpdate( imgPath + "bomb_1.png" );   //atualiza o sprite
            g.drawImage( spriteBomba, this.getWidth() - 150, 0, this );
            break;
      }

      switch ( link.flechas )
      {
         case -2:    //bumerangue
            spriteFlecha = SpriteUpdate( imgPath + "Bumerang_1.png" );
            g.drawImage( spriteFlecha, this.getWidth() / 2, 0, this );
            break;
         case -1:     //nao equipado
            spriteFlecha = SpriteUpdate( imgPath + "arrow_-1.png" );
            g.drawImage( spriteFlecha, this.getWidth() / 2, 0, this );
            break;
         default:
            spriteFlecha = SpriteUpdate( imgPath + "arrow_1.png" );
            g.drawImage( spriteFlecha, this.getWidth() / 2, 0, this );
            break;
      }
   }

   private void DrawPauseMenu( Graphics g )
   {
      int[][] menu = new int[ 4 ][ 2 ];
      BufferedImage selected, fundo, mapa, espada, arco, bumerangue, bomba;

      selected = SpriteUpdate( imgPath + "select.png" );
      fundo = SpriteUpdate( imgPath + "menu_fundo.png" );
      mapa = SpriteUpdate( imgPath + "miniMapa\\" + lvAtual.Nome + ".png" );
      espada = SpriteUpdate( imgPath + "espada.png" );

      if (  ! this.link.bomba )  //tem ou nao a bomba
      {
         bomba = SpriteUpdate( imgPath + "bomb_-1 - menu.png" );
      } else
      {
         bomba = SpriteUpdate( imgPath + "bomb_1 - menu.png" );
      }

      if (  ! this.link.arco )  //tem ou nao o arco
      {
         arco = SpriteUpdate( imgPath + "arco_0.png" );
      } else
      {
         arco = SpriteUpdate( imgPath + "arco_1.png" );
      }

      if (  ! this.link.bumerangue )  //tem ou nao o bumerangue
      {
         bumerangue = SpriteUpdate( imgPath + "bumerang_0.png" );
      } else
      {
         bumerangue = SpriteUpdate( imgPath + "Bumerang_1.png" );
      }

      menu[ 0 ][ 0 ] = 450; //X slot1
      menu[ 0 ][ 1 ] = 150; //Y slot1

      menu[ 1 ][ 0 ] = 600; //X slot2
      menu[ 1 ][ 1 ] = 150; //Y slot2

      menu[ 2 ][ 0 ] = 450; //X slot3
      menu[ 2 ][ 1 ] = 300; //Y slot3

      menu[ 3 ][ 0 ] = 600; //X slot4
      menu[ 3 ][ 1 ] = 300; //Y slot4

      g.drawImage( fundo, 25, 125, this );
      g.drawImage( mapa, 50, 150, this );

      g.drawImage( espada, menu[ 0 ][ 0 ], menu[ 0 ][ 1 ], this );
      g.drawImage( bomba, menu[ 1 ][ 0 ], menu[ 1 ][ 1 ], this );
      g.drawImage( arco, menu[ 2 ][ 0 ], menu[ 2 ][ 1 ], this );
      g.drawImage( bumerangue, menu[ 3 ][ 0 ], menu[ 3 ][ 1 ], this );

      g.drawImage( selected, menu[ pauseSelect ][ 0 ], menu[ pauseSelect ][ 1 ], this );
   }

   private void DrawBG( Graphics g )
   {
      int posX = 0, posY = 0;
      BufferedImage[][] mapa = lvAtual.mapa;
      g.drawImage( lvAtual.fundo, 0, 0, this);
      for ( int i = 0; i < mapa.length; i ++ )
      {
         posX = 0;
         for ( int j = 0; j < mapa[ 0 ].length; j ++ )
         {
            g.drawImage( mapa[ i ][ j ], posX, posY, this );
            posX += 50;
         }
         posY += 50;
      }
   }
}
