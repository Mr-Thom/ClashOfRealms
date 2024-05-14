package entity;

public class Arqueiro extends Heroi {

	private int dano;
	private int flechas;

	public Arqueiro(String nome, int idade, double altura) {
		super(nome, idade, altura);
		this.dano = 40;
		this.flechas = 1;
	}

	@Override

	public void evoluir() {
		this.upar();
		if (this.getNivel() == 3) {
			this.flechas++;
		}
		if (this.getNivel() == 5) {
			this.flechas++;
		}
		if (this.getNivel() % 2 == 1) {
			if (this.dano + 1 <= 50) {
				this.dano++;
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
		} else
			for (int i = this.flechas; i != 0; i--) {
				int danoReal = (int) (Math.random() * this.dano);
				if (par.getVida() - danoReal > 0) {
					par.recebeDano(danoReal);
				} else {
					par.recebeDano(par.getVida());
					this.evoluir();
					par.morre();
					this.getHistorico().add(par);
					break;
				}
			}
	}

	@Override

	public String toString() {

		return super.toString() + "  |  Arqueiro ( Vida: " + this.getVida() + "% ; Dano Máximo: " + this.dano + " ; Aljava: "
				+ this.flechas + " flecha(s) )";
	}
}