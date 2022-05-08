package questao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

//Questão 3
public class Principal {
	
	 static DecimalFormat df = new DecimalFormat("###,###,###,###,###.00#");
	 static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");

	public static void main(String[] args) {

		ArrayList<Funcionario> funcionario = new ArrayList<Funcionario>();
		
		//Questão 3.1
		inserirFuncionarios(funcionario);
		
		//Questão 3.2
		removerJoao(funcionario);
		
		//Questão 3.3
		imprimirTodos(funcionario);
		
		//Questão 3.4
		atualizarSalario(funcionario);
		
		//Questão 3.5
		Map<String, List<Funcionario>> mapFuncionario = funcionario.stream().collect(Collectors.groupingBy(f -> f.getFuncao()));
		
		//Questão 3.6
		imprimirPorFuncao(mapFuncionario);
		
		//Questão 3.7 -- Não existe
		
		//Questão 3.8
		imprimirAniversariante(mapFuncionario);
		
		//Questão 3.9
		imprimirMaisVelho(mapFuncionario);
		
		//Questão 3.10
		imprimirEmOrdem(funcionario);
		
		//Questão 3.11
		imprimirTotalSalario(funcionario);
		
		//Questão 3.12
		imprimirSalarioMinimo(funcionario, new BigDecimal(1212.00));
		
	}
	
	public static void inserirFuncionarios(ArrayList<Funcionario> funcionario) {
		
		funcionario.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formato), new BigDecimal(2009.44), "Operador"));
		funcionario.add(new Funcionario("João", LocalDate.parse("12/05/1990", formato), new BigDecimal(2284.38), "Operador"));
		funcionario.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formato), new BigDecimal(9836.14), "Coordenador"));
		funcionario.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formato), new BigDecimal(19119.88), "Diretor"));
		funcionario.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formato), new BigDecimal(2234.68), "Recepcionista"));
		funcionario.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formato), new BigDecimal(1582.72), "Operador"));
		funcionario.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formato), new BigDecimal(4071.84), "Contador"));
		funcionario.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formato), new BigDecimal(3017.45), "Gerente"));
		funcionario.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formato), new BigDecimal(1606.85), "Eletricista"));
		funcionario.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formato), new BigDecimal(2799.93), "Gerente"));	
		
	}
	
	public static void removerJoao(ArrayList<Funcionario> funcionario) {
		funcionario.removeIf(func -> (func.getNome().equals("João")));
	}
	
	public static void imprimirTodos(ArrayList<Funcionario> funcionario) {

		System.out.println("Nome  -  Data de Nascimento  -  Salário    -    Função");
		
		for(Funcionario func: funcionario){
            	System.out.print(func.getNome() +"        ");
            	System.out.print(func.getDataNascimento().format(formato)+"         ");
                System.out.print(df.format(func.getSalario())+"      ");
                System.out.println(func.getFuncao());
         }
		
	}
	
	public static void imprimirPorFuncao(Map<String, List<Funcionario>> funcionario) {

		System.out.println("/-----------------------------------------/");
		
		for(String key: funcionario.keySet()){
			System.out.println("Função: "+ key);
			System.out.println("Nome  -  Data de Nascimento  -  Salário");
			for(Funcionario value: funcionario.get(key)) {
		    	System.out.print(value.getNome() +"        ");
            	System.out.print(value.getDataNascimento().format(formato)+"         ");
                System.out.println(df.format(value.getSalario())+"      ");
		    }
		    System.out.println();
		}
	    
	}
	
	public static void atualizarSalario(ArrayList<Funcionario> funcionario) {
		
		funcionario.forEach(new Consumer<Funcionario>() {
            @Override
            public void accept(Funcionario funcionario) {
            	funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal(1.1)));
            }
        });
		
	}
	
	public static void imprimirAniversariante(Map<String, List<Funcionario>> funcionario) {
		
		System.out.println("-- Aniversariantes dos meses 10 e 12 --");
		
		for(String key: funcionario.keySet()){
			for(Funcionario value: funcionario.get(key)) {
				if(value.getDataNascimento().getMonthValue() == 10 || value.getDataNascimento().getMonthValue() == 12) {
					System.out.print(value.getNome() +"        ");
					System.out.print(value.getDataNascimento().format(formato)+"         ");
					System.out.println(df.format(value.getSalario())+"      ");					
				}
		    }
		}
	    
	}
	
	public static void imprimirMaisVelho(Map<String, List<Funcionario>> funcionario) {
		
		LocalDate dtMaisVelho = null;
		ArrayList<Funcionario> funcionariosMaisVelhos = new ArrayList<Funcionario>();
		
		System.out.println("\n-- Funcionário mais velho (idade) --");
		
		for(String key: funcionario.keySet()){
			for(Funcionario value: funcionario.get(key)) {
				if(dtMaisVelho == null || value.getDataNascimento().isBefore(dtMaisVelho)) {
					dtMaisVelho = value.getDataNascimento();
					funcionariosMaisVelhos.removeAll(funcionariosMaisVelhos);
					funcionariosMaisVelhos.add(value);
				}
				else if(value.getDataNascimento().isEqual(dtMaisVelho)) {
					funcionariosMaisVelhos.add(value);
				}
				
		    }
		}
		
		System.out.println("Nome   -   Idade");

		funcionariosMaisVelhos.forEach(new Consumer<Funcionario>() {
            @Override
            public void accept(Funcionario funcionariosMaisVelhos) {
            	System.out.print(funcionariosMaisVelhos.getNome() +"        ");
            	System.out.println(Period.between(funcionariosMaisVelhos.getDataNascimento(), LocalDate.now()).getYears());
            }
        });
		
		System.out.println();
	    
	}
	
	public static void imprimirEmOrdem(ArrayList<Funcionario> funcionario) {
		
		System.out.println("-- Funcionarios em ordem alfabética --");

		Collections.sort(funcionario);
		
		imprimirTodos(funcionario);
	    
	}
	
	public static void imprimirTotalSalario(ArrayList<Funcionario> funcionario) {
		BigDecimal totalSalario = new BigDecimal(0);
		
		for(Funcionario func: funcionario) {
			totalSalario = totalSalario.add(func.getSalario());
		}
		
		System.out.println("\n- Salário total dos funcionários: "+ df.format(totalSalario) +" -\n");
	    
	}
	
	public static void imprimirSalarioMinimo(ArrayList<Funcionario> funcionario, BigDecimal salarioMinimoAtual) {
		
		for(Funcionario func: funcionario) {
			System.out.println("Nome: "+ func.getNome());
        	System.out.println("Data de nascimento: "+ func.getDataNascimento().format(formato));
            System.out.print("Salário: "+ df.format(func.getSalario())+" - ");
            System.out.println("Aproximadamente "+ func.getSalario().divide(salarioMinimoAtual, 0, RoundingMode.HALF_UP) +" salários mínimos (valor arredondado).");
            System.out.println("Função: "+ func.getFuncao() +"\n");
		}
		
		
	}
	

}
