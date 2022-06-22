package br.com.qintess.exceptions;

public class EscalaException extends RuntimeException {

    private int codigo;

    public EscalaException(int codigo, String message) {
        super(message);
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
