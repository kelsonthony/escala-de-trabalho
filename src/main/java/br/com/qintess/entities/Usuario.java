package br.com.qintess.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7485092339472089435L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long usuarioId;

    @NotBlank
    @Column(name = "LOGIN", unique=true, length = 125, nullable = false)
    private String login;

    @Column(length = 8, nullable = false, unique = true)
    private String matricula;

    @Column(name = "SENHA", length = 255, nullable = false)
    private String senha;

    @NotBlank
    @Column(name = "NOME", length = 125, nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "SOBRENOME", length = 255, nullable = false)
    private String sobrenome;

    @NotBlank
    @Column(length = 255,nullable = false)
    private String email;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "TB_PERFIL_USUARIO",
            joinColumns = { @JoinColumn(name = "USUARIO_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PERFIL_ID") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Perfil> perfis = new ArrayList<Perfil>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
        if(perfis != null) {
            for(Perfil perfil : perfis) {
                autorizacoes.addAll(perfil.getAutorizacoes());
            }
        }
        return autorizacoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return usuarioId;
    }

    public void setId(Long id) {
        this.usuarioId = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
}
