package questao;

import java.math.BigDecimal;
import java.time.LocalDate;
//Questão 2
public class Funcionario extends Pessoa implements Comparable<Funcionario>{
	
	BigDecimal salario;
	String funcao;
	
	public Funcionario() {
		
	}
	
	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}
	
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public int compareTo(Funcionario outroFuncionario) {
		return getNome().compareTo((outroFuncionario).getNome());
	}

	
}
