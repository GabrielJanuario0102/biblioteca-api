package br.edu.ifpb.biblioteca_api.exception;

public class ItemNaoEncontradoException extends RuntimeException{

    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
