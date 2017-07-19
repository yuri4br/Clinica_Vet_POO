package br.com.exception;
public class CodigoErradoExcepiton extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodigoErradoExcepiton() {
		super("Esse codico possivelmente este errado ou "
				+ "esse cliente não esta cadastrado no sistema");
	}
}
