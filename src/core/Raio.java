package core;

public class Raio {

	private Ponto pontoInicial;
	private Vetor vetor;

	public Raio(Ponto pontoInicial, Vetor vetor) {
		this.pontoInicial = pontoInicial;
		this.vetor = vetor;
	}
	public Raio(double x0, double y0, double z0, Vetor vetor) {
		this.pontoInicial = new Ponto(x0, y0, z0);
		this.vetor = vetor;
	}


	// =========================
    // METODOS IMUTÁVEIS
    // =========================


	public boolean intercepta(Ponto ponto) {
		return ponto.subtrair(pontoInicial).isParalelo(vetor);
	}

	@Override
    public String toString() {
        return String.format(
        	"P(t) = %s + t %s",
        	pontoInicial.toString().replace("Ponto", ""),
        	vetor.toString().replace("Vetor", "")
        );
    }

}