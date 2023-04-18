package trabalho_hilario;

public class Pessoa {
	
	protected String nome;
	protected String cpf;
	
	public Pessoa(String nome,String cpf) {
		this.nome=nome;
		this.cpf=cpf;
	}
	public void listarDados() {
		System.out.println("Nome: "+this.nome);
		System.out.println("CPF: "+this.cpf);
	}
	
}
