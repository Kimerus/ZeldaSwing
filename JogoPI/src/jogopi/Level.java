package jogopi;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {

    Jogo jogo;
    String path = "\\src\\arquivos\\";
    private String arq;
    String full_path;
    BufferedReader br;
    
    public Level(Jogo jogo, String arq) throws FileNotFoundException, IOException {
        this.jogo = jogo;
        this.arq = arq;
        this.full_path = System.getProperty("user.dir")+this.path+this.arq;
        this.br = new BufferedReader(new FileReader(full_path));
        this.lvl();
    }
    
    public void lvl() throws IOException{
        int x = 0, y = 0;
        for (int i = 0; i < 31; i++) {
            String line[] = br.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                System.out.print(line[j] + " ");
                int h = Integer.parseInt(line[j]);
                switch (h){
                    case 0:
                        x += 22;
                        break;
                    case 1:
                        jogo.walls.add(new Rectangle(x,y,22,22));
                        x += 22;
                        break;
                    default:
                        x += 22;
                        break;
                }
            }
            x = 0;
            y += 22;
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------");
    }

    public void setArq(String arq) throws FileNotFoundException, IOException {
        this.arq = arq;
        this.full_path = System.getProperty("user.dir")+this.path+this.arq;
        br = new BufferedReader(new FileReader(full_path));
        ArrayList <Rectangle> w = jogo.walls;
        jogo.walls.removeAll(w);
        this.lvl();
    }
    
    
}
