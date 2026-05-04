package app;

import core.*;

public class Main {

	public static void main(String[] args) {

		Vetor vetor = new Vetor(0, 1, 0);
		Raio raio = new Raio(0, 0, 0, vetor);
		Ponto ponto = new Ponto(0, -257, 0);

		System.out.println(vetor);
		System.out.println(raio);
		System.out.println(ponto);

		System.out.println(raio.intercepta(ponto));
	}

}