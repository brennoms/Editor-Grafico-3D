package core;

public class Triangulo {

	private Ponto A;
	private Vetor AB;
	private Vetor AC;

	public Triangulo(Ponto A, Vetor AB, Vetor AC) {
		this.A = A;
		this.AB = AB;
		this.AC = AC;
	}
	public Triangulo(Ponto A, Ponto B, Ponto C) {
		this.A = A;
		this.AB = new Vetor(A, B);
		this.AC = new Vetor(A, C);
	}
	public Triangulo(double x, double y, double z) {
		this.A = new Ponto(x, 0, 0);
		this.AB = new Vetor(A, new Ponto(0, y, 0));
		this.AC = new Vetor(A, new Ponto(0, 0, z));
	}
	public Triangulo(Triangulo triangulo) {
		this.A = new Ponto(triangulo.A);
		this.AB = new Vetor(triangulo.AB);
		this.AC = new Vetor(triangulo.AC);
	}

	// =========================
    // METODOS IMUTÁVEIS
    // =========================

    public boolean contem(Ponto P) {
    	// usa coordenadas baricêntricas
	    final double EPS = 1e-9;

	    Vetor AP = P.subtrair(A);

	    double dot00 = AC.produtoEscalar(AC);
	    double dot01 = AC.produtoEscalar(AB);
	    double dot02 = AC.produtoEscalar(AP);
	    double dot11 = AB.produtoEscalar(AB);
	    double dot12 = AB.produtoEscalar(AP);

	    double denom = dot00 * dot11 - dot01 * dot01;

	    if (Math.abs(denom) < EPS) return false;

	    double u = (dot11 * dot02 - dot01 * dot12) / denom;
	    double v = (dot00 * dot12 - dot01 * dot02) / denom;

	    return (u >= -EPS) && (v >= -EPS) && (u + v <= 1 + EPS);
	}

	public Vetor ortogonal() {
		return AB.produtoVetorial(AC);
	}

	private Ponto getA() {
		return A;
	}

    private Ponto getB() {
    	return A.somar(AB);
    }

    private Ponto getC() {
    	return A.somar(AC);
    }

    private Vetor getAB() {
    	return new Vetor(AB);
    }

    private Vetor getAC() {
    	return new Vetor(AC);
    }

    private Vetor getBC() {
    	return new Vetor(AC.subtrair(AB));
    }

    @Override
    public String toString() {
        return String.format(
        	"Triangulo:\n %s\n %s\n %s \n %s -> %.2f \n %s -> %.2f \n %s -> %.2f",
        	A.toString().replace("Ponto", "A"),
        	this.getB().toString().replace("Ponto", "B"),
        	this.getC().toString().replace("Ponto", "C"),
        	AB.toString().replace("Vetor", "AB"), AB.modulo(),
        	AC.toString().replace("Vetor", "AC"), AC.modulo(),
        	this.getBC().toString().replace("Vetor", "BC"), this.getBC().modulo()
        );
    }

}