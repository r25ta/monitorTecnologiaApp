package br.com.pamcary.ir.monitortecnologia.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaFilterExample {

	public static void main(String[] args) {
		List<String> versions = new ArrayList<>();
		versions.add("Cleverson");
		versions.add("Claudio");
		versions.add("Renato");
		versions.add("Vitor");
		versions.add("Elizeu");
		versions.add("Leonardo");
		versions.add("Jonas");
		versions.add("Mariana");
		versions.add("Dilmar");
		versions.add("João");
		versions.add("Arthur");
		versions.add("Luciano");
		versions.add("Henrique");
		versions.add("Ronaldo");
		
		System.out.println(" \n Filtrar todos os nomes que tenham mais de 6 letras");
		versions.stream().filter(s -> s.length() > 7 ).forEach(System.out::println);
		
		System.out.println("\n Filtrar todos o nomes que se inicia com a letra 'R'");
		String first = versions.stream().filter(s -> s.contains("D")).findFirst().get();
		System.out.println("\n " + first);

		
		System.out.println("\n Utilizando multiplos filtros ao mesmo tempo -> Todos os nomes com mais de 6 letras e inicie com letra 'C'");
		
		versions.stream().filter(s -> s.length() > 7 )
						 .filter(s -> s.startsWith("C"))
						 .forEach(System.out::println);
		
		
		
		/*
		List<Integer> listOfNumbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		Integer lcm = listOfNumbers.stream().filter(i -> i % 2 == 0)
											.filter(i -> i % 3 == 0)
											.findFirst().get();
		
		System.out.println("\n Primeiro numero divisivel por 2 e 3 da list é : " + lcm);
		
*/		
	}

}
