package algebraEgeometria;

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
		// Möller–Trumbore ray-triangle intersection algorithm
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

	public Ponto interseptacao(Plano plano) {
		final double EPS = 1e-9;
		//P + tV; ax + by + cz + d ; d = -(ax1 + by1 + cz1)

		double x = pontoInicial.getX();
		double y = pontoInicial.getY();
		double z = pontoInicial.getZ();
		double a_ = vetor.getX();
		double b_ = vetor.getY();
		double c_ = vetor.getZ();

		Vetor vetorNormalPlano = plano.getVetorNormal();
		double a = vetorNormalPlano.getX();
		double b = vetorNormalPlano.getY();
		double c = vetorNormalPlano.getZ();

		Ponto pontoPlano = plano.getPontoPlano();
		double x2 = pontoPlano.getX();
		double y2 = pontoPlano.getY();
		double z2 = pontoPlano.getZ();

		// a(x+t*a_) + b(y+t*b_) + c(z+t*c_) - a*x2 - b*y2 - c*z2 = 0
		// a*x + a*t*a_ + b*y + b*t*b_ + c*z + c*t*c_ = a*x2 + b*y2 + c*z2
		// t(a * a_ + b*b_ + c*c_) = a*x2 + b*y2 + c*z2 - a*x - b*y - c*z
		// t = (a*x2 + b*y2 + c*z2 - a*x - b*y - c*z) / (a * a_ + b*b_ + c*c_)

		double denominador = a * a_ + b*b_ + c*c_;
		double t = (a*x2 + b*y2 + c*z2 - a*x - b*y - c*z) / denominador;

		if (Math.abs(denominador) < EPS) return null;

		return pontoEm(t);
	}
	public boolean intersepta(Plano plano) {
		return this.interseptacao(plano) != null;
	}

	public Ponto pontoEm(double t) {
    	return pontoInicial.somar(vetor.multiplicar(t));
	}

	@Override
    public String toString() {
        return String.format(
        	"Raio: P(t) = %s + t %s",
        	pontoInicial.toString().replace("Ponto", ""),
        	vetor.toString().replace("Vetor", "")
        );
    }

}