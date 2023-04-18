package trabalho_hilario;

import java.util.ArrayList;


public class Aposta {
	private ArrayList<Integer> numeros = new ArrayList<Integer>();
	private Jogador organizador;
	private ArrayList<Jogador>participantes=new ArrayList<Jogador>();
	
	public Aposta(ArrayList<Integer>numeros,Jogador organizador,ArrayList<Jogador>participantes) {
		this.numeros=numeros;
		this.organizador=organizador;
		this.participantes=participantes;
	}
	public boolean vencedora(ArrayList<Integer> sorteados) {
		int numeros_acertados=0;
		int num_sorteado=0;
		int num_chutado=0;
		
		for (int i = 0;  i <sorteados.size();  i++) {
			for(int j=0; j<this.numeros.size();j++) {
				num_sorteado=sorteados.get(i);
				num_chutado=this.numeros.get(j);
				if(num_sorteado==num_chutado) {
					numeros_acertados++;
				}
			}
		}
		if(numeros_acertados>5) {return true;} 
		else {return false;}
	}
	public void listarVencedores(double premio) {
		double lucro_organizador=0;
		double lucro_participantes=0;
		lucro_organizador=lucro_organizador+(0.1*premio);
		premio=(0.9*premio);
		premio=premio/participantes.size();
		lucro_organizador=lucro_organizador+premio;
		lucro_participantes=lucro_participantes+premio;		
		for(int i=0;i<participantes.size();i++) {
			if(participantes.get(i).nome.equals(organizador.nome)) { //confere se o participante analisado é o organizador
				Main.minhaJanela.appendOutput(participantes.get(i).nome+": R$"+lucro_organizador+" Pix:"+participantes.get(i).pix+"\n"+"   ");
			}
			else {//atesta que é um participante, não um organizador, e mostra o lucro dele.
				Main.minhaJanela.appendOutput(participantes.get(i).nome+": R$"+lucro_participantes+" Pix:"+participantes.get(i).pix+"\n"+"   ");
			}	
		}
	}
	
	public Jogador getOrganizador() {
		return this.organizador;
	}
	public ArrayList<Integer> getNumeros(){
		return numeros;
	}
	public ArrayList<String> getParticipantesAsStringList(){
		ArrayList<String>Nomes_Participantes=new ArrayList<String>();
		for(int i=0;i<participantes.size();i++) {
			Nomes_Participantes.add(participantes.get(i).nome);
		}
		return Nomes_Participantes;
	}
}
