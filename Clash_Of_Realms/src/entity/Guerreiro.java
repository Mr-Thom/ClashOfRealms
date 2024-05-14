package entity;

public class Guerreiro extends Heroi {

	private int dano;

	public Guerreiro(String nome, int idade, double altura) {
		super(nome, idade, altura);
		this.dano = 30;
	}

	@Override

	public void evoluir() {
		this.upar();
		for (int i = 5; i != 0; i--) {
			if (this.dano + i <= 70) {
				this.dano += i;
				break;
			}
		}
	}

	@Override
	public void habilidade(Heroi par) {
		if (this.getMorto() == true) {
			par.recebeDano(0);
		} else if (par == this) {
			par.recebeDano(0);
		} else if (par.getVida() == 0) {
			par.recebeDano(0);
		} else {
			if (par.getVida() - this.dano <= 0) {
				par.recebeDano(par.getVida());
				par.morre();
				this.evoluir();
				this.getHistorico().add(par);
			} else {
				par.recebeDano(dano);
				if ((this.dano + 1) <= 70) {
					this.dano += 1;
				}
			}
		}
	}

	@Override

	public String toString() {
		return super.toString() + "  |  Guerreiro ( Vida: " + this.getVida() + "% ; Dano: " + this.dano + " )";
	}
}
