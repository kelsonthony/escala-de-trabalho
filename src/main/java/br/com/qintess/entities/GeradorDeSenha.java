package br.com.qintess.entities;

import java.security.SecureRandom;

public class GeradorDeSenha {


  private  String CARACTERES_ESPECIAIS = "$&*+@#-!?";
  private static final int NUMERO_INICIAL = 33;
  private static final int NUMERO_FINAL = 122;

  public GeradorDeSenha() {
  }

  public String gerarSenha(int tamanho){

    SecureRandom random = new SecureRandom();

    return random.ints(NUMERO_INICIAL, NUMERO_FINAL + 1)
      .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i) || CARACTERES_ESPECIAIS.contains(Character.toString((char) i)))
      .limit(tamanho)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint,
        StringBuilder::append)
      .toString();
  }


}
