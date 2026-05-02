package core;

public class Vetor {

	private double[] componentes = new double[3];
	

	public Vetor(double x, double y, double z) {
		componentes[0] = x;
		componentes[1] = x;
		componentes[2] = x;
	}
	public Vetor(Ponto pontoInicial, Ponto pontoFinal) {
		componentes[0] = pontoFinal.getX() - pontoInicial.getX();
		componentes[1] = pontoFinal.getY() - pontoInicial.getY();
		componentes[2] = pontoFinal.getZ() - pontoInicial.getZ();
	}
	public Vetor(double radianosXY, double radianosZ) {
		componentes[0] = Math.cos(radianosZ) * Math.cos(radianosXY);
		componentes[1] = Math.cos(radianosZ) * Math.sin(radianosXY);
		componentes[2] = Math.sin(radianosZ);
	}


	public double getX() { return componentes[0]; }
	public double getY() { return componentes[1]; }
	public double getZ() { return componentes[2]; }

	public double modulo() {
		return Math.sqrt(
			componentes[0]*componentes[0] + 
			componentes[1]*componentes[1] + 
			componentes[2]*componentes[2]
		);
	}

	public double produtoEscalar(double escalar) {
		return componentes[0]*escalar + componentes[1]*escalar + componentes[2]*escalar;
	}

	public double[] componentes() {
		return componentes.clone();
	}

	public Vetor produtoVetorial(Vetor vetor) {
		return new Vetor(
			componentes[1]*vetor.getZ() - componentes[2]*vetor.getY(), 
			componentes[2]*vetor.getX() - componentes[0]*vetor.getZ(), 
			componentes[0]*vetor.getY() - componentes[1]*vetor.getX()
		);
	}


	public void somarVetor(Vetor vetor) {
		componentes[0] += vetor.getX();
		componentes[1] += vetor.getY();
		componentes[2] += vetor.getZ();
	}

	public void subtrairVetor(Vetor vetor) {
		vetor.multiplicarPorEscalar(-1);
		somarVetor(vetor);
	}

	public void multiplicarPorEscalar(double escalar) {
		componentes[0] *= escalar;
		componentes[1] *= escalar;
		componentes[2] *= escalar;
	}

	public void rotacionar(int eixoDeRotação, double radianos) {
		if (eixoDeRotação == 0) {
			componentes[1] = componentes[1] * Math.cos(radianos) - componentes[2] * Math.sin(radianos);
			componentes[2] = componentes[1] * Math.sin(radianos) + componentes[2] * Math.cos(radianos);
		} else if (eixoDeRotação == 1) {
			componentes[0] = componentes[0] * Math.cos(radianos) + componentes[2] * Math.sin(radianos);
			componentes[2] = componentes[2] * Math.cos(radianos) - componentes[0] * Math.sin(radianos);
		} else {
			componentes[0] = componentes[0] * Math.cos(radianos) - componentes[1] * Math.sin(radianos);
			componentes[1] = componentes[0] * Math.sin(radianos) + componentes[1] * Math.cos(radianos);
		}
	}


	public String toString() {
		return String.format("Vetor( %.2f , %.2f , %.2f )", componentes[0], componentes[1], componentes[2]);
	} 

}