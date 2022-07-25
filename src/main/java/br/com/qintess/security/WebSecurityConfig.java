package br.com.qintess.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/assets/**").permitAll()
                    .antMatchers("/contato-suporte").permitAll()
                    .antMatchers("/cargos/**").hasAnyRole("ROLE_CARGO")
                    .antMatchers("/cargos/listar").hasAnyRole("ROLE_CARGO_LISTAR")
                    .antMatchers("/equipes/**").hasAnyRole("ROLE_EQUIPE")
                    .antMatchers("/equipes/listar").hasAnyRole("ROLE_EQUIPE_LISTAR")
                    .antMatchers("/escalas/**").hasAnyRole("ROLE_ESCALA")
                    .antMatchers("/escalas/listar").hasAnyRole("ROLE_ESCALA_LISTAR")
                    .antMatchers("/escalas/listar/tipo").hasAnyRole("ROLE_ESCALA_LISTAR_TIPOS")
                    .antMatchers("/feriados/**").hasAnyRole("ROLE_FERIADO")
                    .antMatchers("/feriados/listar").hasAnyRole("ROLE_FERIADO_LISTAR")
                    .antMatchers("/funcionarios/**").hasAnyRole("ROLE_FUNCIONARIO")
                    .antMatchers("/funcionarios/listar").hasAnyRole("ROLE_FUNCIONARIO_LISTAR")
                    .antMatchers("/turnos/**").hasAnyRole("ROLE_TURNO")
                    .antMatchers("/turnos/listar").hasAnyRole("ROLE_TURNO_LISTAR")
                    .antMatchers("/gerenciamento/**").hasAnyRole("ROLE_GERENCIAMENTO")
                    .antMatchers("/log/**").hasAnyRole("ROLE_LOG")
                    .antMatchers("/usuario/**").hasAnyRole("ROLE_USUARIO")
                    .antMatchers("/perfil/**").hasAnyRole("ROLE_PERFIL")
                    .anyRequest()
                    .authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .rememberMe();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .inMemoryAuthentication()
                .withUser("admin").password("$2a$10$Z3ZMCO65/.b4p6yZA23eP.8Pt/5sz0IdoiqICYCbfw7V.FNzX554e").roles("USER", "EDITOR", "ADMIN")
                .and()
                .withUser("editor").password("$2a$10$Z3ZMCO65/.b4p6yZA23eP.8Pt/5sz0IdoiqICYCbfw7V.FNzX554e").roles("USER", "EDITOR")
                .and()
                .withUser("user").password("$2a$10$RkTVD0vVnTO9PcU2VbSOButxB3bavOmic/.cuhp4.0a9uml5Vg.bm").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
