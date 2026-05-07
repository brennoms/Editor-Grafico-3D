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
	public Raio(Raio raio) {
		this.pontoInicial = new Ponto(pontoInicial);
		this.vetor = new Vetor(vetor);
	}


	// =========================
    // METODOS IMUTÁVEIS
    // =========================


	public boolean intersepta(Ponto p) {
    	Vetor AP = p.subtrair(pontoInicial);

	    if (!AP.isParalelo(vetor)) {
	        return false;
	    }

	    double t = AP.produtoEscalar(vetor) / vetor.produtoEscalar(vetor);

	    return t >= 0;
	}

	public boolean intersepta(Triangulo triangulo) {
	    final double EPS = 1e-9;

	    Ponto origem = pontoInicial;
	    Vetor direcao = vetor;

	    Ponto verticeA = triangulo.getA();
	    Vetor arestaAB = triangulo.getAB();
	    Vetor arestaAC = triangulo.getAC();

	    // Vetor perpendicular entre a direção do raio e a aresta AC
	    Vetor perpendicular = direcao.produtoVetorial(arestaAC);

	    double determinante = arestaAB.produtoEscalar(perpendicular);

	    // Raio paralelo ao triângulo
	    if (Math.abs(determinante) < EPS) return false;

	    // Vetor da origem do raio até o vértice A
	    Vetor origemParaA = origem.subtrair(verticeA);

	    double u = origemParaA.produtoEscalar(perpendicular) / determinante;
	    if (u < -EPS || u > 1.0 + EPS) return false;

	    // Segundo vetor perpendicular
	    Vetor perpendicular2 = origemParaA.produtoVetorial(arestaAB);

	    double v = direcao.produtoEscalar(perpendicular2) / determinante;
	    if (v < -EPS || u + v > 1.0 + EPS) return false;

	    // distancia do raio até o triangulo
	    double t = arestaAC.produtoEscalar(perpendicular2) / determinante;

	    // Interseção válida só se estiver na frente do raio
	    return t > EPS;
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