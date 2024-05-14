package entity;

public class Mago extends Heroi {

	private int cura;

	public Mago(String nome, int idade, double altura) {
		super(nome, idade, altura);
		this.cura = 20;
	}

	@Override

	public void evoluir() {
		this.upar();
		if (this.cura + 5 <= 50) {
			this.cura += 5;
		}
	}

	@Override
	public void habilidade(Heroi par) {
		if (this.getMorto() == true) {
			par.recebeCura(0);
		} else {
			if (par.getMorto() == true && par.getRevivido() == false) {
				par.revive();
				par.recebeCura(cura);
				this.evoluir();
				this.getHistorico().add(par);
			} else if (par.getMorto() == true && par.getRevivido() == true) {
				par.recebeCura(0);
			} else
				par.recebeCura(cura);
		}
	}

	@Override

	public String toString() {

		return super.toString() + "  |  Mago ( Vida: " + this.getVida() + "% ; Cura: " + this.cura + " )";

	}
}
