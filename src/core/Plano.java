package core;

public class Plano {

	private Ponto pontoPlano;
	private Vetor vetorNormal;

	public Plano(Ponto pontoPlano, Vetor vetorNormal) {
		this.pontoPlano = pontoPlano;
		this.vetorNormal = vetorNormal;
	}
	public Plano (Plano plano) {
		this.pontoPlano = new Ponto(plano.pontoPlano);
		this.vetorNormal = new vetorNormal(plano.vetorNormal);
	}


	// =========================
    // METODOS IMUTÁVEIS
    // =========================

	public boolean contem(Ponto ponto) {
		Vetor pontoPlano_ponto = ponto.subtrair(pontoPlano);
		return vetorNormal.isOrtogonal(pontoPlano_ponto);
	}

}