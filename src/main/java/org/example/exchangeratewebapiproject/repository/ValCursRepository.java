package org.example.exchangeratewebapiproject.repository;

import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ValCursRepository  extends JpaRepository<ValCurs, Long> {
    ValCurs getValCursByDate(String date);

    boolean existsByDate(String formattedDate);

    void deleteByDate(String date);

}
