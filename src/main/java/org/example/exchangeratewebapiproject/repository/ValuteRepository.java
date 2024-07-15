package org.example.exchangeratewebapiproject.repository;

import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, Long> {
    Valute findByCode(String code);

}
