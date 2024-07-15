package org.example.exchangeratewebapiproject.repository;

import org.example.exchangeratewebapiproject.api.model.ValCurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ValCursRepository  extends JpaRepository<ValCurs, Long> {
    ValCurs getValCursByDate(String date);

    boolean existsByDate(String formattedDate);

    void deleteByDate(String date);

}
