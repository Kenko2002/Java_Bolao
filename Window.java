package trabalho_hilario;


import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener{
	private static JButton botaoCadastrarJogador;
	private static JButton botaoCadastrarAposta;
	private static JButton botaoComecarSorteio;
	private JTextField  text_field_1;
	private JTextField  text_field_2;
	private JTextField  text_field_3;
	public JLabel output;
	public JLabel label_vazia1;
	public JLabel label_vazia2;
	public JLabel lista_jogadores;
	public JLabel lista_apostas;
	
	private boolean flag_cadastrando_jogador=false;
	private boolean flag_cadastrando_aposta=false;
	private int flag_sorteando=1;
	private String[] sorteados_aux;
	
	public Window() {				//construtor Configura a janela principal
        this.setTitle("Bolão do Tio Juca");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1)); // linhas 1 colunas
        
        botaoCadastrarJogador= new JButton("Cadastrar Jogador"); 			
        botaoCadastrarAposta=new JButton("Cadastrar Aposta");
        botaoComecarSorteio=new JButton("Começar Sorteio!");
        text_field_1=(JTextField) new JTextField("1");
        text_field_2=(JTextField) new JTextField("2");
        text_field_3=(JTextField) new JTextField("3");
        text_field_1.setVisible(false);
        text_field_2.setVisible(false);
        text_field_3.setVisible(false);
        output=new JLabel(" ");
        lista_jogadores=new JLabel("");
        lista_apostas=new JLabel("");
        label_vazia1=new JLabel("");
        label_vazia2=new JLabel("");
        
        add(botaoCadastrarJogador);
        add(botaoCadastrarAposta);
        add(botaoComecarSorteio);
        add(label_vazia1);
        
        add(text_field_1);
        add(text_field_2);
        add(text_field_3);
        add(label_vazia2);
        
        add(output);
        
        add(lista_jogadores);
        add(lista_apostas);
																							
        
        botaoCadastrarJogador.addActionListener(this);		
        botaoCadastrarAposta.addActionListener(this);	
        botaoComecarSorteio.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botaoCadastrarJogador) {
			if(flag_cadastrando_jogador==false) {
				flag_cadastrando_jogador=true;
				flag_cadastrando_aposta=false;
				text_field_1.setText("Insira aqui seu Nome!");
				text_field_2.setText("Insira aqui seu CPF!");
				text_field_3.setText("Insira aqui seu PIX!");
				output.setText("Preencha os Campos Corretamente e Clique Novamente no botão de Cadastrar Jogador.");
		        text_field_1.setVisible(true);
		        text_field_2.setVisible(true);
		        text_field_3.setVisible(true);
			}
			else{
				if(!text_field_1.getText().equals("Insira aqui seu Nome!")) {
					flag_cadastrando_jogador=false;
					flag_cadastrando_aposta=false;
					text_field_1.setVisible(false);
					text_field_2.setVisible(false);
					text_field_3.setVisible(false);
					Main.bolao.cadastrarJogador(text_field_1.getText(),text_field_2.getText(),text_field_3.getText()); 
				}
			}
		}
		else if(e.getSource() == botaoCadastrarAposta) {
			if(flag_cadastrando_aposta==false) {
				flag_cadastrando_aposta=true;
				flag_cadastrando_jogador=false;
				text_field_1.setText("Insira aqui os numeros apostados separados por espaço.");
				text_field_2.setText("Insira aqui o CPF do Organizador!");
				text_field_3.setText("Insira aqui o CPF dos participantes separados por espaço.");
				output.setText("Preencha os Campos Corretamente e Clique Novamente no botão de Cadastrar Aposta.");
		        text_field_1.setVisible(true);
		        text_field_2.setVisible(true);
		        text_field_3.setVisible(true);
			}
			else {
				if(!text_field_1.getText().equals("Insira aqui os numeros apostados separados por espaço.")&&text_field_1.getText().split(" ").length>=6&&text_field_1.getText().split(" ").length<=15) {
					flag_cadastrando_aposta=false;
					flag_cadastrando_jogador=false;
					text_field_1.setVisible(false);
					text_field_2.setVisible(false); 
					text_field_3.setVisible(false);
					ArrayList<Integer> numeros;
					Jogador organizador;
					ArrayList<Jogador>participantes;
					String[]participantes_cpf;
					String[]numeros_aux;
					numeros_aux=text_field_1.getText().split(" ");
					participantes_cpf=text_field_3.getText().split(" ");
					if (!Main.bolao.validarJogadores(participantes_cpf)){
						mudarOutput("Você digitou um CPF invalido!");
					}
					else {
						numeros=new ArrayList<Integer>();
						for(int i=0;i<numeros_aux.length;i++) {
							numeros.add(Integer.parseInt(numeros_aux[i]));
						}
						String organizador_cpf;
						organizador_cpf=text_field_2.getText();
						organizador=Main.bolao.getOrganizador(organizador_cpf);
						if(!organizador.nome.equals("")) {
							participantes=Main.bolao.getJogadoresByCpfs(participantes_cpf);
							Main.bolao.cadastrarAposta(numeros,organizador,participantes);
							mudarOutput("Aposta Cadastrada com Sucesso!");
							this.atualizarListaDeApostas();
						}	
					}
				}
			}
		}
		else if(e.getSource() == botaoComecarSorteio) {
			if(flag_sorteando==1) {
				flag_sorteando=2;
				botaoCadastrarJogador.setVisible(false);
				botaoCadastrarAposta.setVisible(false);
				botaoComecarSorteio.setText("OK");
				text_field_1.setText("Insira aqui os Números Sorteados separados por Espaço.");
				text_field_1.setVisible(true);
				text_field_2.setVisible(false);
				text_field_3.setVisible(false);
			}
			else if(flag_sorteando==2&&text_field_1.getText().split(" ").length==6){
				flag_sorteando=3;
				sorteados_aux=text_field_1.getText().split(" ");
				text_field_1.setText("Insira aqui o Prêmio do sorteio. (Ex:500.00)");
				mudarOutput("Insira agora o prêmio do sorteio.");
			}
			else if(flag_sorteando==3) {
				flag_sorteando=4;
				double premio=Double.parseDouble(text_field_1.getText());
				ArrayList<Integer> numeros_sorteados=new ArrayList<Integer>();
				for(int i=0;i< sorteados_aux.length;i++) {
					numeros_sorteados.add(Integer.parseInt(sorteados_aux[i]));
				}
				text_field_1.setVisible(false);
				this.atualizarListaDeApostas();
				Main.bolao.inserirSorteio(numeros_sorteados,premio);
					}
			else if(flag_sorteando==4) {
				Main.minhaJanela.dispose();
			}
		}
	}
	
	
	public void appendOutput(String texto) {
		this.output.setText(output.getText()+texto);
	}
	
	public void mudarOutput(String texto) {
		this.output.setText(texto);
	}
	
	public void atualizarListaDeJogadores() {
		ArrayList<Jogador> listajogadores;
		String exibicao="";
		listajogadores=Main.bolao.getJogadores();
		for(int i=0;i<listajogadores.size();i++){
			exibicao=exibicao+listajogadores.get(i).nome+"\n"+"   ";
		}
		lista_jogadores.setText(exibicao);
	}
	
	public void atualizarListaDeApostas() {
		ArrayList<Aposta> listaapostas;
		String exibicao="";
		listaapostas=Main.bolao.getApostas();
		for(int i=0;i<listaapostas.size();i++) {
			exibicao=exibicao+"Aposta do(a) "+listaapostas.get(i).getOrganizador().nome+": "+listaapostas.get(i).getNumeros()+"\n"+" Grupo: "+listaapostas.get(i).getParticipantesAsStringList()+"\n"+"   ";
		}
		lista_apostas.setText(exibicao);
	}
}