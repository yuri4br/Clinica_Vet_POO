package br.com.exception;
public class CPFInvalidoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String cpf;
	
	public CPFInvalidoException(String cpf) {
		super("CPF '" + cpf + "' Nulo ou Inv�lido");
		this.cpf = cpf;
	}
}
