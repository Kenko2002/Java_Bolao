package trabalho_hilario;

public class Jogador extends Pessoa{

protected String pix;

	public Jogador(String nome,String cpf,String pix){
		super(nome,cpf);
		this.pix=pix;
	}
	public void  listarDados() {
		super.listarDados();
		System.out.println("Pix: "+this.pix);
	}
	
	
}
