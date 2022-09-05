package br.com.qintess;

import br.com.qintess.dashboard.entities.Dashboard;
import br.com.qintess.dashboard.entities.Especialidade;
import br.com.qintess.entities.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class Teste {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new BCryptPasswordEncoder().encode("q1nt3ss"));

        Mes mes = new Mes();

        Turno t1 = new Turno();
        Turno t2 = new Turno();

        t1.setPadraoSistema(0);
        t1.setTotalHoras("08:00:00");

        t2.setPadraoSistema(0);
        t2.setTotalHoras("04:30:00");

        Dia d1 = new Dia();
        Dia d2 = new Dia();

        d1.setTurno(t1);
        d2.setTurno(t2);



        List<Dia> dias = new ArrayList<>();
        dias.add(d1);
        dias.add(d2);

        mes.setDias(dias);




      System.out.println(mes.getTotalHorasNormais());

    }

}
