package br.com.qintess.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
                    .antMatchers("/gerenciamento/**").hasRole("GERENCIAMENTO")
                    .antMatchers("/funcionarios/cadastrar").hasRole("FUNCIONARIO")
                    .antMatchers("/funcionarios/listar").hasRole("FUNCIONARIO_LISTAR")
                    .antMatchers("/cargos/cadastrar").hasRole("CARGO")
                    .antMatchers("/cargos/listar").hasRole("CARGO_LISTAR")
                    .antMatchers("/equipes/cadastrar").hasRole("EQUIPE")
                    .antMatchers("/equipes/listar").hasRole("EQUIPE_LISTAR")
                    .antMatchers("/escalas/cadastrar").hasRole("ESCALA")
                    .antMatchers("/escalas/listar").hasRole("ESCALA_LISTAR")
                    .antMatchers("/feriados/cadastrar").hasRole("FERIADO")
                    .antMatchers("/feriados/listar").hasRole("FERIADO_LISTAR")
                    .antMatchers("/turnos/cadastrar/fixo").hasAnyRole("TURNO")
                    .antMatchers("/turnos/cadastrar/alternado").hasAnyRole("TURNO")
                    .antMatchers("/turnos/listar").hasAnyRole("TURNO_LISTAR")
                    .antMatchers("/log/**").hasAnyRole("LOG")
                    .antMatchers("/usuario/**").hasAnyRole("USUARIO")
                    .antMatchers("/perfil/**").hasAnyRole("PERFIL")
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
