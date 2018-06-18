package jogopi;

/**
 * @author Kerwin Rocha Pereira
 */
public class Bomba extends Colisor{
    Sprite[] sprites =new Sprite[3];
    boolean existe;
    Colisor hitBox, explosao;
    Colisor[] atingidos = new Colisor[12];
    int cont=50, posX, posY;
    Jogo jogo;

    public Bomba(int posX, int posY, Jogo jogo) {
        super(posX, posY,50,50,true,"bomba", jogo);
        this.posX = posX;
        this.posY = posY;
        this.existe = true;
        this.Main();
    }

    private void Main() {
        do {
            cont--;
            if(cont >= 0){
                this.Detonar();
                this.existe = false;
            }
        } while (this.existe);
        
    }

    private void Detonar() {
        int temp=0;
        explosao = new Colisor(this.posX-50, this.posY-50,150,150,false,"explosao", this.jogo);
        
        for(Colisor obj:jogo.lvAtual.objs){
            if(this.explosao.Colidiu(obj)){
                atingidos[temp] = obj;
                temp++;
            }
        }
        
        for(Colisor enemy:jogo.lvAtual.enemys){
            if(this.explosao.Colidiu(enemy)){
                atingidos[temp] = enemy;
                temp++;
            }
        }
        
        for(Colisor atg:atingidos){
            atg.Dano(100);
        }
    }
    
    
}
