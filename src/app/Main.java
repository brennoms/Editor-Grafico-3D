package app;

import core.*;

public class Main {

	public static void main(String[] args) {

		Raio raio = new Raio(0, 0, -1, new Vetor(0, 0, 1));
		System.out.println(raio + "\n");

		Ponto ponto = new Ponto(0, 0, 5);
		System.out.println(ponto);
		System.out.println("P(t)" + (raio.intersepta(ponto) ? " ":" não ") + "intersepta" + "\n");


		Triangulo triangulo = new Triangulo(new Ponto(-5, 0, 0), new Vetor(10, 10, 0), new Vetor(10, -10, 0));
		System.out.println(triangulo);
		System.out.println("P(t)" + (raio.intersepta(triangulo) ? " ":" não ") + "intersepta" + "\n");

		Plano plano = new Plano(new Ponto(0, 0, 10), new Vetor(0, 0, 1));
		System.out.println(plano);
		System.out.println("P(t)" + (raio.intersepta(plano) ? " ":" não ") + "intersepta" + "\n");

	}

}