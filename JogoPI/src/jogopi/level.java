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
    private Colisor[][] paredes = new Colisor[12][16];

    public String Nome;

    public level(Jogo jogo, String area) throws FileNotFoundException, IOException {
        AlfaNumInit();
        String linha, temp[];

        this.Nome = area;

        FileReader file = new FileReader(filePath + area + ".txt");
        BufferedReader buffer = new BufferedReader(file);
        linha = buffer.readLine();
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
                    //adiciona sprite especial
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
            }
            linha = buffer.readLine();
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
        for (int i = 0; i < colisores.length; i++) {
            for (int j = 0; j < colisores[0].length; j++) {
                switch (colisores[i][j]) {
                    case 0: //vazio
                        posY += 50;
                        break;
                    case 1: //cheio
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, true);
                        posY += 50;
                        break;
                    case 2: //meio acima
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 25, true);
                        posY += 50;
                        break;
                    case 3: //meio abaixo
                        this.paredes[i][j] = new Colisor(posX, posY + 25, 50, 25, true);
                        posY += 50;
                        break;
                    case 4: //meio esquerda
                        this.paredes[i][j] = new Colisor(posX, posY, 25, 50, true);
                        posY += 50;
                        break;
                    case 5: //meio direita
                        this.paredes[i][j] = new Colisor(posX + 25, posY, 25, 50, true);
                        posY += 50;
                        break;

                    //casos especiais \/\/\/ //
                    case 6:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false);
                        posY += 50;
                        break;
                    case 7:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false);
                        posY += 50;
                        break;
                    case 8:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false);
                        posY += 50;
                        break;
                    case 9:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false);
                        posY += 50;
                        break;
                    case 10:
                        this.paredes[i][j] = new Colisor(posX, posY, 50, 50, false);
                        posY += 50;
                        break;
                }
            }
            posX += 50;
            posY = 0;
        }
    }
}
