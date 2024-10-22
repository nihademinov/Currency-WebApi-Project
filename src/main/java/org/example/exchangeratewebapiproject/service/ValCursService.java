package org.example.exchangeratewebapiproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValCursDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.bussiness.management.ValCursManager;
import org.example.exchangeratewebapiproject.exceptionHandler.AlreadyExistsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ValCursService {

    private final ValCursManager valCursManager;

    public List<ValCursMapDto> getAllValCurs() {
        return valCursManager.getAllValCurs();
    }

    public ValCursMapDto getValCursByDate(LocalDate date) {
        return valCursManager.getValCursByDate(date);
    }


    public String syncCurrData(LocalDate localDate) {
       return valCursManager.syncCurrDataCreate(localDate);
    }

    public ValCursDto getVaCursBySpecificValuteAndNominal(LocalDate date, Double nominal, String valuteCode) {
        return valCursManager.getValCursByValute(date, nominal, valuteCode);
    }

    @Transactional
    public String deleteValCursByDate(LocalDate date) {

        valCursManager.deleteValCursByDate(date);
        return "Successfully deleted";
    }


}


