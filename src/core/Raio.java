package core;

public class Raio {

	private Ponto pontoInicial;
	private Vetor vetor;

	public Raio(Ponto pontoInicial, Vetor vetor) {
		this.pontoInicial = pontoInicial;
		this.vetor = vetor;
	}
	public Raio(double x, double y, double z, Vetor vetor) {
		this.pontoInicial = new Ponto(x, y, z);
		this.vetor = vetor;
	}


	// =========================
    // METODOS IMUTÁVEIS
    // =========================


	public boolean intercepta(Ponto p) {
    	Vetor AP = p.subtrair(pontoInicial);

	    if (!AP.isParalelo(vetor)) {
	        return false;
	    }

	    double t = AP.produtoEscalar(vetor) / vetor.produtoEscalar(vetor);

	    return t >= 0;
	}

	public Ponto pontoEm(double t) {
    	return pontoInicial.somar(vetor.multiplicar(t));
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