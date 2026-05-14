package core;

public class Ponto {

    private final double[] coordenadas;

    public Ponto(double x, double y, double z) {
        this.coordenadas = new double[]{x, y, z};
    }
    public Ponto(Ponto ponto) {
        this.coordenadas = new double[]{
            ponto.getX(),
            ponto.getY(),
            ponto.getZ()
        };
    }

    public double getX() { return coordenadas[0]; }
    public double getY() { return coordenadas[1]; }
    public double getZ() { return coordenadas[2]; }


    // =========================
    // METODOS IMUTÁVEIS
    // =========================


    public double distanciaPara(Ponto p) {
	    double dx = p.getX() - coordenadas[0];
	    double dy = p.getY() - coordenadas[1];
	    double dz = p.getZ() - coordenadas[2];

	    return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

    public Ponto somar(Vetor v) {
        return new Ponto(
            coordenadas[0] + v.getX(),
            coordenadas[1] + v.getY(),
            coordenadas[2] + v.getZ()
        );
    }

    public Ponto subtrair(Vetor v) {
        return new Ponto(
            coordenadas[0] - v.getX(),
            coordenadas[1] - v.getY(),
            coordenadas[2] - v.getZ()
        );
    }

    public Vetor subtrair(Ponto p) {
	    return new Vetor(
	        coordenadas[0] - p.getX(),
	        coordenadas[1] - p.getY(),
	        coordenadas[2] - p.getZ()
	    );
	}

    public double[] coordenadas() {
        return coordenadas.clone();
    }

    // ============================
    // METODOS MUTÁVEIS (Instancia)
    // ============================

    public Ponto reposicionarInstancia(double x, double y, double z) {
        coordenadas[0] = x;
        coordenadas[1] = y;
        coordenadas[2] = z;
        return this;
    }

    public Ponto reposicionarInstancia(Ponto p) {
        coordenadas[0] = p.getX();
        coordenadas[1] = p.getY();
        coordenadas[2] = p.getZ();
        return this;
    }

    public Ponto somarInstancia(Vetor v) {
        coordenadas[0] += v.getX();
        coordenadas[1] += v.getY();
        coordenadas[2] += v.getZ();
        return this;
    }

    public Ponto subtrairInstancia(Vetor v) {
        coordenadas[0] -= v.getX();
        coordenadas[1] -= v.getY();
        coordenadas[2] -= v.getZ();
        return this;
    }

    @Override
    public String toString() {
        return String.format("Ponto( %.2f , %.2f , %.2f )",
            coordenadas[0], coordenadas[1], coordenadas[2]);
    }
}