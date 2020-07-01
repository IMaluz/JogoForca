package jogoForca;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		jogo(sc);
		
		while(true) {
			System.out.println("Deseja jogar novamente? ( S ou N )");
			String voto = sc.nextLine();
			if(voto.toUpperCase().equals("N")) {
				System.out.println("Obrigada pela jogatina :)");
				break;
			}
			jogo(sc);
		}
		sc.close();
	}
	
	public static void jogo(Scanner sc) {
		List<String> corpo = new ArrayList<String>();
		List<String> palavras = new ArrayList<String>();
		String palavraEscolhida = "";
		
		Random random = new Random();
		
		corpo = novoCorpo();
		
		System.out.println("Escreva as palavras do jogo, quando acabar digite: 1");
		while(true) {
			String palavra = sc.nextLine();
			if(palavra.equals("1")) {
				break;
			} else {
			palavras.add(palavra.toUpperCase());
			}
		}
		
		System.out.println("As palavras cadastradas foram: " + palavras);
		int randomIndex = random.nextInt(palavras.size());
		palavraEscolhida = palavras.get(randomIndex);
		
		String palavraCortada[] = palavraEscolhida.split("");
		String palavraDigitada[] = palavraEscolhida.split("");
		for(int i = 0; i < palavraCortada.length; i++) {
			palavraDigitada[i] = "_";
		}
		
		while(true) {
			if(corpo.size() == 0) {
				System.out.println("Você Perdeu!");
				System.out.println("Loooser!");
				break;
			}
			if(completouPalavra(palavraDigitada, palavraCortada)) {
				System.out.println("Voce Ganhou!!!");
				System.out.print("A Palavra era: " );
				for(String letra: palavraDigitada) {
					System.out.print(letra);
					System.out.print("");
				}
				System.out.println();
				break;
			}
			System.out.println("Partes Intactas: " + corpo);
			System.out.print("Palavra até agora: ");
			for(String letra: palavraDigitada) {
				System.out.print(letra);
				System.out.print(" ");
			}
			System.out.println();
			System.out.println("Digite uma letra: ");
			String tentativa = sc.nextLine().toUpperCase();
			
			boolean acerto = false;
			for(int i = 0; i < palavraDigitada.length; i++) {
				if(tentativa.toUpperCase().equals(palavraCortada[i].toUpperCase())) {
					palavraDigitada[i] = tentativa;
					acerto = true;
				} else if(i == palavraDigitada.length - 1 && acerto == false) {
					removeParte(corpo);
				}
				
			}
		}
	}
	
	public static List<String> novoCorpo(){
		List<String> corpo = new ArrayList<String>();
		corpo.add("cabeça");
		corpo.add("peito");
		corpo.add("braço direito");
		corpo.add("braço esquerdo");
		corpo.add("perna direita");
		corpo.add("perna esquerda");
		return corpo;
	}
	
	public static List<String> removeParte(List<String> corpo){
		corpo.remove(corpo.size() -1);
		return corpo;
	}
	
	public static boolean completouPalavra(String palavraDigitada[], String palavraCortada[] ) {
		for (String letra : palavraDigitada) {
			if(letra == "_") {
				return false;
			}
		}
		return true;
	}
}
