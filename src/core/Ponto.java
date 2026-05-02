package core;

public class Ponto {

	private double[] coordenadas = new double[3];

	public Ponto(double x, double y, double z) {
		reposicionar(x, y, z);
	}

	public double getX() { return coordenadas[0]; }
	public double getY() { return coordenadas[1]; }
	public double getZ() { return coordenadas[2]; }

	public double[] coordenadas() {
		return coordenadas.clone();
	}

	public void reposicionar(double x, double y, double z) {
		coordenadas[0] = x;
		coordenadas[1] = y;
		coordenadas[2] = z;
	}

	public void reposicionar(Ponto ponto) {
		coordenadas[0] = ponto.getX();
		coordenadas[1] = ponto.getY();
		coordenadas[2] = ponto.getZ();
	}

	public void reposicionar(double[] coordenadas) {
		this.coordenadas[0] = coordenadas[0];
		this.coordenadas[1] = coordenadas[1];
		this.coordenadas[2] = coordenadas[2];
	}

	public String toString() {
		return String.format("Ponto( %.2f , %.2f , %.2f )", coordenadas[0], coordenadas[1], coordenadas[2]);
	} 

}