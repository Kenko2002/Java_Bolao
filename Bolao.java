package trabalho_hilario;

import java.util.ArrayList;


public class Bolao {
	private ArrayList<Aposta> apostas=new ArrayList<Aposta>();
	private ArrayList<Jogador> jogadores=new ArrayList<Jogador>();
	
	public Bolao(ArrayList<Aposta> apostas,ArrayList<Jogador> jogadores) {
		this.apostas=apostas;
		this.jogadores=jogadores;
	}
	public Bolao() {
		
	}
	
	public void cadastrarJogador(String nome,String cpf,String pix) {
		boolean flag=true;
		if(this.jogadores.size()>0) {
			for(int i=0;i<this.jogadores.size();i++) {
				if(this.jogadores.get(i).cpf.equals(cpf)) {
					flag=false;
				}
			}
		}
		if(flag==true) {
			this.jogadores.add(new Jogador(nome,cpf,pix));
			Main.minhaJanela.mudarOutput("Cadastrado com Sucesso!");
			Main.minhaJanela.atualizarListaDeJogadores();
			
			/*INSERIR AQUI FUNCIONALIDADE DE SALVAR JOGADORES*/
			
		}
		else {
			Main.minhaJanela.mudarOutput("Erro: CPF já cadastrado!");
		}
		
	}
	public void cadastrarAposta(ArrayList<Integer>numeros,Jogador organizador,ArrayList<Jogador>participantes) {
		this.apostas.add(new Aposta(numeros,organizador,participantes));
		
			/*INSERIR AQUI FUNÇÃO DE SALVAR APOSTAS*/
	}
	
	private ArrayList<Aposta> vencedoras(ArrayList<Integer>numeros_sorteados){
		ArrayList<Aposta>apostas_premiadas=new ArrayList<>();
		for(int i=0;i<apostas.size();i++) {
			if(this.apostas.get(i).vencedora(numeros_sorteados)) {
				apostas_premiadas.add(this.apostas.get(i));
			}
		}
		return apostas_premiadas;
	}
	public void inserirSorteio(ArrayList<Integer>numeros_sorteados,double premio) {
		ArrayList<Aposta> apostas_vencedoras=new ArrayList<>();
		apostas_vencedoras=this.vencedoras(numeros_sorteados);
		premio=premio/apostas_vencedoras.size();
		Main.minhaJanela.mudarOutput("VENCEDORES:");
		int cont=0;
		for(int i=0;i<apostas_vencedoras.size();i++) {
			cont++;
			Main.minhaJanela.appendOutput("Grupo: "+cont+" ");
			apostas_vencedoras.get(i).listarVencedores(premio);
		}
	}
	
	public ArrayList<Jogador> getJogadores(){
		return this.jogadores;
	}
	
	public ArrayList<Aposta> getApostas(){
		return this.apostas;
	}
	
	public Jogador getOrganizador(String cpf) {
		int index_organizador=0;
		boolean flag=false;
		for(int i=0;i<jogadores.size();i++) {
			if(cpf.equals(jogadores.get(i).cpf)) {
				index_organizador=i;
				flag=true;
			}
		}
		if(flag==true) {
			Jogador resultado=jogadores.get(index_organizador);
			return resultado;
		}
		else {
			Main.minhaJanela.mudarOutput("Erro: Organizador Não encontrado");
			return new Jogador("","","");
		}
		
	}
	
	public boolean validarJogadores(String[]cpfs) {
		boolean flag=false;
		boolean flag2=true;
		
		for(int i=0;i<cpfs.length;i++) {
			for(int j=0;j<this.jogadores.size();j++) {
				if(cpfs[i].equals(this.jogadores.get(j).cpf)) {
					flag=true;
				}
			}
			if (flag==false) {flag2=false;}
			flag=false;
		}
		return flag2;
	}
	
	public ArrayList<Jogador> getJogadoresByCpfs(String[]cpfs) {
		ArrayList<Jogador> JogadoresEncontrados=new ArrayList<Jogador>();
		for(int i=0;i<cpfs.length;i++) {
			for(int j=0;j<this.jogadores.size();j++) {
				if(cpfs[i].equals(jogadores.get(j).cpf)) {
					JogadoresEncontrados.add(jogadores.get(j));
				}
			}
		}
		return JogadoresEncontrados;
	}
	
}
