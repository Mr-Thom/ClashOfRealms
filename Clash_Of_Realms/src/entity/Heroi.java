package entity;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Heroi implements Comparable {

	private int vida;
	private final String nome;
	private int idade;
	private final double altura;
	private int nivel;
	private boolean morto;
	private boolean revivido;
	private final ArrayList<Heroi> historico;

	public Heroi(String nome, int idade, double altura) {

		Objects.requireNonNull(nome, "nome nao pode ser nulo");
		
		if (nome.equals("")) {
			throw new IllegalArgumentException("Nome deve ser preenchido");
		}
		this.nome = nome;

		if (idade < 0) {
			throw new IllegalArgumentException("Idade nao pode ser negativa");
		}
		
		if (idade > 122) {
			throw new IllegalArgumentException("A idade máxima permitida é 122 Anos, idade que Jeanne Calment (a pessoa mais velha do mundo) morreu.");

		}
		this.idade = idade;

		if (altura < 0) {
			throw new IllegalArgumentException("altura nao pode ser negativo");
		}
		
		if (altura > 2.74) {
			throw new IllegalArgumentException("Altura máxima permitida é de 2.74m, altura esta do maior ser humano da história, Robert Pershing Wadlow");
		}
		this.altura = altura;

		this.vida = 100;

		this.nivel = 1;

		this.morto = false;
		
		this.revivido = false;

		this.historico = new ArrayList<Heroi>();

	}

	public String getNome() {
		return this.nome;
	}

	public double getAltura() {
		return this.altura;
	}

	public int getIdade() {
		return this.idade;
	}

	public int getVida() {
		return this.vida;
	}

	public int getNivel() {
		return this.nivel;
	}

	public ArrayList<Heroi> getHistorico() {
		return this.historico;
	}

	public void upar() {
		this.nivel++;
	}

	public boolean getMorto() {
		return this.morto;
	}
	
	public boolean getRevivido() {
		return this.revivido;
	}
	
	public void aniversario() {
		this.idade++;
	}

	public void morre() {
		this.morto = true;
	}

	public void revive() {
		this.morto = false;
		this.revivido = true;
	}

	public void recebeDano(int val) {
		for (int i = val; i != 0; i--) {
			if (this.vida - i < 0) {
				continue;
			} else {
				this.vida -= val;
				break;
			}
		}
	}

	public void recebeCura(int val) {
		for (int i = val; i != 0; i--) {
			if (this.vida + i > 100) {
				continue;
			} else {
				this.vida += val;
				break;
			}
		}
	}

	abstract public void habilidade(Heroi par);

	abstract public void evoluir();

	@Override

	public int compareTo(Object par) {

		Heroi aux = (Heroi) par;

		// Idade
		if (this.getIdade() < aux.getIdade())
			return -1;
		else if (this.getIdade() > aux.getIdade())
			return 1;

		// Altura
		else if (this.getAltura() < aux.getAltura())
			return -1;
		else if (this.getAltura() > aux.getAltura())
			return 1;

		// Vida
		else if (this.getVida() < aux.getVida())
			return -1;
		else if (this.getVida() > aux.getVida())
			return 1;

		// Iguais
		else
			return 0;
	}

	@Override

	public String toString() {
		String vivo;

		if (this.getMorto() == true && this.revivido == true) {
			vivo = "MORTO";
		} else if (this.getMorto() == true && this.revivido == false){
			vivo = "ATORDOADO";
		} else vivo = "VIVO";
		
		return this.getNome() + " (" + vivo + ")  |  Nível: " + this.nivel + "  |  "+ this.getIdade() + " Anos e " + this.getAltura() + "m";
	}

}
