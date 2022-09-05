package br.com.qintess.dashboard.repositories;

import br.com.qintess.dashboard.entities.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDashboardRepository extends JpaRepository<Dashboard,Long> {
}
