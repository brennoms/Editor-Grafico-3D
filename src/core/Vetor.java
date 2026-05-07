package core;

public class Vetor {

    private final double[] componentes;

    public Vetor(double x, double y, double z) {
        this.componentes = new double[]{x, y, z};
    }

    public Vetor(Ponto pontoInicial, Ponto pontoFinal) {
        this.componentes = new double[]{
            pontoFinal.getX() - pontoInicial.getX(),
            pontoFinal.getY() - pontoInicial.getY(),
            pontoFinal.getZ() - pontoInicial.getZ()
        };
    }

    public Vetor(double radianosXY, double radianosZ) {
        this.componentes = new double[]{
            Math.cos(radianosZ) * Math.cos(radianosXY),
            Math.cos(radianosZ) * Math.sin(radianosXY),
            Math.sin(radianosZ)
        };
    }

    public Vetor(Vetor v) {
        this.componentes = new double[]{
            v.getX(),
            v.getY(),
            v.getZ()
        };
    }

    private double getX() { return componentes[0]; }
    private double getY() { return componentes[1]; }
    private double getZ() { return componentes[2]; }


    // =========================
    // METODOS IMUTÁVEIS
    // =========================


    public double modulo() {
        return Math.sqrt(
            componentes[0] * componentes[0] +
            componentes[1] * componentes[1] +
            componentes[2] * componentes[2]
        );
    }

    public double produtoEscalar(Vetor v) {
        return componentes[0] * v.getX() +
               componentes[1] * v.getY() +
               componentes[2] * v.getZ();
    }

    public double[] componentes() {
        return componentes.clone();
    }

    public Vetor produtoVetorial(Vetor v) {
        return new Vetor(
            componentes[1] * v.getZ() - componentes[2] * v.getY(),
            componentes[2] * v.getX() - componentes[0] * v.getZ(),
            componentes[0] * v.getY() - componentes[1] * v.getX()
        );
    }

    public boolean isParalelo(Vetor v) {
    	Vetor vProduto = this.produtoVetorial(v);
    	double EPS = 1e-9;
    	return Math.abs(vProduto.getX()) < EPS &&
		       Math.abs(vProduto.getY()) < EPS &&
		       Math.abs(vProduto.getZ()) < EPS;
    }

    public boolean isOrtogonal(Vetor v) {
        final double EPS = 1e-9;
        return Math.abs(this.produtoEscalar(v)) < EPS;
    }

    public Vetor somar(Vetor v) {
        return new Vetor(
            componentes[0] + v.getX(),
            componentes[1] + v.getY(),
            componentes[2] + v.getZ()
        );
    }

    public Vetor subtrair(Vetor v) {
        return new Vetor(
            componentes[0] - v.getX(),
            componentes[1] - v.getY(),
            componentes[2] - v.getZ()
        );
    }

    public Vetor multiplicar(double escalar) {
        return new Vetor(
            componentes[0] * escalar,
            componentes[1] * escalar,
            componentes[2] * escalar
        );
    }

    public Vetor rotacionar(int eixo, double rad) {
        double x = componentes[0];
        double y = componentes[1];
        double z = componentes[2];

        if (eixo == 0) { // X
            return new Vetor(
                x,
                y * Math.cos(rad) - z * Math.sin(rad),
                y * Math.sin(rad) + z * Math.cos(rad)
            );
        } else if (eixo == 1) { // Y
            return new Vetor(
                x * Math.cos(rad) + z * Math.sin(rad),
                y,
                -x * Math.sin(rad) + z * Math.cos(rad)
            );
        } else { // Z
            return new Vetor(
                x * Math.cos(rad) - y * Math.sin(rad),
                x * Math.sin(rad) + y * Math.cos(rad),
                z
            );
        }
    }

    public Vetor normalizado() {
        double m = modulo();
        return new Vetor(
            componentes[0] / m,
            componentes[1] / m,
            componentes[2] / m
        );
    }


    // =============================
    // METODOS MUTÁVEIS (Instancia)
    // =============================


    public Vetor somarInstancia(Vetor v) {
        componentes[0] += v.getX();
        componentes[1] += v.getY();
        componentes[2] += v.getZ();
        return this;
    }

    public Vetor subtrairInstancia(Vetor v) {
        componentes[0] -= v.getX();
        componentes[1] -= v.getY();
        componentes[2] -= v.getZ();
        return this;
    }

    public Vetor multiplicarInstancia(double escalar) {
        componentes[0] *= escalar;
        componentes[1] *= escalar;
        componentes[2] *= escalar;
        return this;
    }

    public Vetor rotacionarInstancia(int eixo, double rad) {
        double x = componentes[0];
        double y = componentes[1];
        double z = componentes[2];

        if (eixo == 0) {
            componentes[1] = y * Math.cos(rad) - z * Math.sin(rad);
            componentes[2] = y * Math.sin(rad) + z * Math.cos(rad);
        } else if (eixo == 1) {
            componentes[0] = x * Math.cos(rad) + z * Math.sin(rad);
            componentes[2] = -x * Math.sin(rad) + z * Math.cos(rad);
        } else {
            componentes[0] = x * Math.cos(rad) - y * Math.sin(rad);
            componentes[1] = x * Math.sin(rad) + y * Math.cos(rad);
        }

        return this;
    }

    public Vetor normalizarInstancia() {
        double m = modulo();
        componentes[0] /= m;
        componentes[1] /= m;
        componentes[2] /= m;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Vetor( %.2f , %.2f , %.2f )",
            componentes[0], componentes[1], componentes[2]);
    }
}