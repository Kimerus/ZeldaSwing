package jogopi;

/**
 * @author Kerwin
 */
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class level {

    private final String programPath = System.getProperty("user.dir");   //caminho do programa
    private final String imgPath = programPath + "\\src\\img\\";   //pasta das imagens
    private final String filePath = programPath + "\\src\\arquivos\\";   //pasta dos arquivos
    private ArrayList<String> alfaNum;
    private BufferedImage[][] folder1, folder2, folder3;
    public BufferedImage[][] mapa;
    public BufferedImage fundo;
    private int[][] colisores = new int[12][16]; //linha - coluna
    public Colisor[][] paredes = new Colisor[12][16];

    public Colisor[] objs, enemys;

    public ArrayList<Interativos> interact;

    public Sprite[] extras = new Sprite[50];

    private String id6, id7, id8, id9, id10;

    public String Nome;
    private Jogo jogo;

    public level(Jogo jogo, String area, int side) throws FileNotFoundException, IOException {
        AlfaNumInit();
        String linha, temp[];

        this.jogo = jogo;
        this.Nome = area;

        this.interact = new ArrayList<>();

        FileReader file = new FileReader(filePath + area + ".txt");
        BufferedReader buffer = new BufferedReader(file);
        linha = buffer.readLine();

        int cont = 0;
        while (linha != null) //leitura do arquivo
        {
            temp = linha.split(" ");

            switch (temp[0]) {
                case "FF":
                    String direc = temp[1];
                    String t1[] = temp[2].split("/");
                    if (this.folder1 == null) {
                        this.folder1 = SpriteMatriz(direc, Integer.parseInt(t1[0]), Integer.parseInt(t1[1]));
                    } else if (this.folder2 == null) {
                        this.folder2 = SpriteMatriz(direc, Integer.parseInt(t1[0]), Integer.parseInt(t1[1]));
                    } else if (this.folder3 == null) {
                        this.folder3 = SpriteMatriz(direc, Integer.parseInt(t1[0]), Integer.parseInt(t1[1]));
                    }

                    break;
                case "BG":
                    this.fundo = SpriteUpdate(imgPath + "terreno\\fundo\\" + temp[1]);
                    break;

                case "EE":
                    t1 = temp[1].split(",");
                    if (extras[cont] == null) {
                        extras[cont] = new Sprite(t1[0], Integer.parseInt(t1[1]), Integer.parseInt(t1[2]), Integer.parseInt(t1[3]), Integer.parseInt(t1[4]));
                        cont++;
                    }
                    break;

                case "AA":
                    // mapa de sprites
                    this.mapa = new BufferedImage[12][16];
                    int line = 0,
                     col = 0;
                    linha = buffer.readLine();
                    while (!"AA".equals(linha)) {
                        temp = linha.split(" ");
                        col = 0;
                        for (String elem : temp) {
                            if ("0".equals(elem)) {
                                col++;
                                continue;
                            }
                            t1 = elem.split("-");
                            switch (t1[0]) {
                                case "1":
                                    this.mapa[line][col] = this.folder1[alfaNum.indexOf(t1[1])][Integer.parseInt(t1[2])];
                                    col++;
                                    break;
                                case "2":
                                    this.mapa[line][col] = this.folder2[alfaNum.indexOf(t1[1])][Integer.parseInt(t1[2])];
                                    col++;
                                    break;
                                case "3":
                                    this.mapa[line][col] = this.folder3[alfaNum.indexOf(t1[1])][Integer.parseInt(t1[2])];
                                    col++;
                                    break;
                            }
                        }
                        line++;
                        linha = buffer.readLine();
                    }

                    break;

                case "6":
                    id6 = temp[1];
                    break;
                case "7":
                    id7 = temp[1];
                    break;
                case "8":
                    id8 = temp[1];
                    break;
                case "9":
                    id9 = temp[1];
                    break;
                case "10":
                    id10 = temp[1];
                    break;

                case "00":
                    //mapa de colisão
                    line = 0;
                    col = 0;

                    linha = buffer.readLine();
                    while (!"00".equals(linha)) {
                        temp = linha.split(" ");
                        col = 0;
                        for (String elem : temp) {
                            this.colisores[line][col] = Integer.parseInt(elem);
                            col++;
                        }
                        line++;
                        linha = buffer.readLine();
                    }
                    CriarParedes(this.colisores);
                    break;

                case "DROP":
                    t1 = temp[1].split(",");

                    interact.add(new Drop(Integer.parseInt(t1[0]), Integer.parseInt(t1[1]), this.jogo));
            }
            linha = buffer.readLine();
        }

        switch (side) {
            case 0:
                jogo.link.y = 450;
                break;
            case 1:
                jogo.link.x = 75;
                break;
            case 2:
                jogo.link.y = 100;
                break;
            case 3:
                jogo.link.x = 650;
                break;
        }
    }

    private BufferedImage[][] SpriteMatriz(String pasta, int qtdPasta, int qtdSprite) {
        BufferedImage[][] matriz;
        matriz = new BufferedImage[qtdPasta][qtdSprite];

        for (int i = 0; i < qtdPasta; i++) {
            for (int j = 0; j < qtdSprite; j++) {
                matriz[i][j] = SpriteUpdate(imgPath + "terreno\\" + pasta + "\\" + alfaNum.get(i) + "\\" + j + ".png");
            }
        }

        return matriz;
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

    private void AlfaNumInit() {
        alfaNum = new ArrayList<>();
        alfaNum.add("A");
        alfaNum.add("B");
        alfaNum.add("C");
        alfaNum.add("D");
        alfaNum.add("E");
        alfaNum.add("F");
        alfaNum.add("G");
        alfaNum.add("H");
        alfaNum.add("I");
        alfaNum.add("J");
        alfaNum.add("K");
        alfaNum.add("L");
        alfaNum.add("M");
        alfaNum.add("N");
        alfaNum.add("O");
        alfaNum.add("P");
        alfaNum.add("Q");
        alfaNum.add("R");
        alfaNum.add("S");
        alfaNum.add("T");
        alfaNum.add("U");
        alfaNum.add("V");
        alfaNum.add("W");
        alfaNum.add("X");
        alfaNum.add("Y");
        alfaNum.add("Z");
    }

    private void CriarParedes(int[][] colisores) {
        int posX = 0, posY = 0;
        String t1[];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {
                switch (colisores[i][j]) {
                    case 0: //vazio
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false, "vazio", this.jogo);
                        posX += 50;
                        break;
                    case 1: //cheio
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, true, "vazio", this.jogo);
                        posX += 50;
                        break;
                    case 2: //meio acima
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 25, true, "vazio", this.jogo);
                        posX += 50;
                        break;
                    case 3: //meio abaixo
                        this.paredes[i][j] = new Colisor(posX, posY + 25, 50, 25, true, "vazio", this.jogo);
                        posX += 50;
                        break;
                    case 4: //meio esquerda
                        this.paredes[i][j] = new Colisor(posX, posY, 25, 50, true, "vazio", this.jogo);
                        posX += 50;
                        break;
                    case 5: //meio direita
                        this.paredes[i][j] = new Colisor(posX + 25, posY, 25, 50, true, "vazio", this.jogo);
                        posX += 50;
                        break;

                    //casos especiais \/\/\/ //
                    case 6:
                        t1 = id6.split(",");
                        this.paredes[i][j] = new Colisor(posX, posY, Integer.parseInt(t1[1]), Integer.parseInt(t1[2]), false, t1[0], this.jogo);
                        posX += 50;
                        break;
                    case 7:
                        t1 = id7.split(",");
                        this.paredes[i][j] = new Colisor(posX, posY, Integer.parseInt(t1[1]), Integer.parseInt(t1[2]), false, t1[0], this.jogo);
                        posX += 50;
                        break;
                    case 8:
                        t1 = id8.split(",");
                        this.paredes[i][j] = new Colisor(posX, posY, Integer.parseInt(t1[1]), Integer.parseInt(t1[2]), false, t1[0], this.jogo);
                        posX += 50;
                        break;
                    case 9:
                        t1 = id9.split(",");
                        this.paredes[i][j] = new Colisor(posX, posY, Integer.parseInt(t1[1]), Integer.parseInt(t1[2]), false, t1[0], this.jogo);
                        posX += 50;
                        break;
                    case 10:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false, id10, this.jogo);
                        posX += 50;
                        break;
                }
            }
            posY += 50;
            posX = 0;
        }
    }

    public Colisor WallCheck(Colisor obj) {
        for (int line = 0; line < this.paredes.length; line++) {
            for (int colu = 0; colu < this.paredes[0].length; colu++) {
                if (this.paredes[line][colu].Colidiu(obj)) {
                    System.out.println(this.paredes[line][colu].getPosX() + " " + this.paredes[line][colu].getPosY());
                    return this.paredes[line][colu];
                }
            }
        }

        return null;
    }

    void run()/* funções do mapa ex: inimigos/itens/etc. */ {
        if (!this.interact.isEmpty()) {
            for (Interativos obj : this.interact) {
                if (obj.existe) {
                    obj.Interact();
                }
            }
            
            //limpa arraylist
            int i = 0;
            for (Interativos obj : this.interact) {
                if(i > 0){
                    this.interact.remove(i);
                }
                if(!obj.existe){
                  i = this.interact.indexOf(obj);
                }
            }
        }
    }
}
