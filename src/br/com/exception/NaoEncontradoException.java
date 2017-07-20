package br.com.exception;
public class NaoEncontradoException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public NaoEncontradoException(String msg){
        super (msg);
    }
}
