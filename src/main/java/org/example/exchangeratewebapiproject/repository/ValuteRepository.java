package org.example.exchangeratewebapiproject.repository;

import org.example.exchangeratewebapiproject.api.model.Valute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValuteRepository extends JpaRepository<Valute, Long> {

    @Query("""
            SELECT v
            FROM Valute v
                     JOIN ValType vt on v.valType.id = vt.id
                     JOIN ValCurs vc on vt.valCurs.id = vc.id
            WHERE v.code = :code
            AND vc.date = :date
            """)
    List<Valute> findByCodeAndValCursDate( @Param("date") String date,@Param("code") String code);

}
