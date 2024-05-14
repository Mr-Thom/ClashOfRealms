package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import entity.*;

public class App {
	public static void main(String[] args) {
		ArrayList<Heroi> arena = new ArrayList<Heroi>();// nao sei se eh heroi //é lista

		for (int i = 1; i != 0; i++) {

			System.out.println("\n+----------------------------------------+");
			System.out.println("|     Bem vindo ao Clash of Realms       |");
			System.out.println("+----------------------------------------+");
			System.out.println("|      (0) Sair                          |");
			System.out.println("|      (1) Crie seu Guerreiro            |");
			System.out.println("|      (2) Crie seu Arqueiro             |");
			System.out.println("|      (3) Crie seu Mago                 |");
			System.out.println("|      (4) Retirar Herói                 |");
			System.out.println("|      (5) Mostrar Heróis                |");
			System.out.println("|      (6) Ordene os Heróis              |");
			System.out.println("|      (7) Aniversario de um Herói       |");
			System.out.println("|      (8) Usar habilidade de um Herói   |");
			System.out.println("+----------------------------------------+");

			Scanner teclado = new Scanner(System.in);
			String opcaoTexto = teclado.nextLine();
			int opcao = Integer.parseInt(opcaoTexto);

			if (opcao == 0) {
				System.out.println("Realmente deseja sair?");
				System.out.println("\n0 - Sair		1 - Cancelar");
				System.out.println(
						"\nAVISO: Jogo sem salvamento automático. Caso saia, todo seu progresso será PERDIDO!!!!");

				String valTexto = teclado.nextLine();
				int val = Integer.parseInt(valTexto);

				if (val == 0) {
					System.out.println("Obrigado por sua serventia");
					break;
				} else
					continue;
			}

			if (opcao == 1) {
				System.out.println("Nome do Heroi : ");
				String nome = teclado.nextLine();

				System.out.println("Idade do Heroi : ");
				String idadeTexto = teclado.nextLine();
				int idade = Integer.parseInt(idadeTexto);
				
				System.out.println("Altura do Heroi : ");
				String alturaTexto = teclado.nextLine();
				double altura = Double.parseDouble(alturaTexto);

				arena.add(new Guerreiro(nome, idade, altura));
			}
			if (opcao == 2) {
				System.out.println("Nome do Heroi : ");
				String nome = teclado.nextLine();

				System.out.println("Idade do Heroi : ");
				String idadeTexto = teclado.nextLine();
				int idade = Integer.parseInt(idadeTexto);

				System.out.println("Altura do Heroi : ");
				String alturaTexto = teclado.nextLine();
				double altura = Double.parseDouble(alturaTexto);

				arena.add(new Arqueiro(nome, idade, altura));

			}

			if (opcao == 3) {
				System.out.println("Nome do Heroi : ");
				String nome = teclado.nextLine();

				System.out.println("Idade do Heroi : ");
				String idadeTexto = teclado.nextLine();
				int idade = Integer.parseInt(idadeTexto);

				System.out.println("Altura do Heroi : ");
				String alturaTexto = teclado.nextLine();
				double altura = Double.parseDouble(alturaTexto);

				arena.add(new Mago(nome, idade, altura));
			}

			if (opcao == 4) {
				System.out.println("Qual Herói deseja retirar?  (Digite o código identificador)");
				String idTexto = teclado.nextLine();
				int id = Integer.parseInt(idTexto);

				System.out.println("Herói removido da Arena:" + arena.get(id).toString());

				if (arena.get(id) instanceof Guerreiro || arena.get(id) instanceof Arqueiro) {
					System.out.println("\nAbates:");
					for (Heroi par : arena.get(id).getHistorico()) {
						System.out.println(par.toString() + "\n");
					}
				}

				if (arena.get(id) instanceof Mago) {
					System.out.println("\nHeróis Salvos:\n");
					for (Heroi par : arena.get(id).getHistorico()) {
						System.out.println(par.toString() + "\n");
					}
				}

				arena.remove(id);

			}

			if (opcao == 5) {
				int aux = 0;

				if (arena.size() != 0) {
					System.out.println("Heróis:\n");

					for (Heroi par : arena) {
						System.out.println(aux + " - " + par.toString());
						aux++;
					}
				} else
					System.out.println("Nenhum Herói existente!!!");

			}

			if (opcao == 6) {
				Collections.sort(arena);

				int aux = 0;

				if (arena.size() != 0) {
					System.out.println("Heróis Ordenados!!!\n");
					for (Heroi par : arena) {
						System.out.println(aux + " - " + par.toString());
						aux++;
					}
				} else
					System.out.println("Nenhum Herói existente!!!");
			}

			if (opcao == 7) {
				System.out.println("Qual é o Herói aniversariante?  (Digite o código identificador)");
				String aniversarianteTexto = teclado.nextLine();
				int aniversariante = Integer.parseInt(aniversarianteTexto);

				if (aniversariante == 0 || aniversariante <= arena.size() - 1) {
					arena.get(aniversariante).aniversario();
				} else
					System.out.println("O Herói informado é inexistente");
			}

			if (opcao == 8) {
				System.out.println("Qual Herói utilizará a habilidade?  (Digite o código identificador)");
				String p1Texto = teclado.nextLine();
				int p1 = Integer.parseInt(p1Texto);

				if (p1 == 0 || p1 <= arena.size() - 1) {
					System.out.println("Qual será afetado pela habilidade?  (Digite o código identificador)");
					String p2Texto = teclado.nextLine();
					int p2 = Integer.parseInt(p2Texto);
					if (p2 == 0 || p2 <= arena.size() - 1) {
						arena.get(p1).habilidade(arena.get(p2));
					} else
						System.out.println("O Herói informado é inexistente");
				} else
					System.out.println("O Herói informado é inexistente");
			}

			if (opcao < 0 || opcao > 8) {
				System.out.println("Número Inválido");
			}
		}

	}
}