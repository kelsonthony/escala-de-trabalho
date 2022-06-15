package br.com.qintess.entities_old;


import java.util.Objects;
import java.util.UUID;


public class PerfilEntity {

  private UUID id;
  private String nome;

  public PerfilEntity(UUID id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public PerfilEntity() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "PerfilEntity{" +
      "id=" + id +
      ", nome='" + nome + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PerfilEntity that = (PerfilEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(nome, that.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome);
  }
}
