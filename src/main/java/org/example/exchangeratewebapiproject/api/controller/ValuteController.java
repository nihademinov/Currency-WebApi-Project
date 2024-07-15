package org.example.exchangeratewebapiproject.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.api.dto.ValCursDto;
import org.example.exchangeratewebapiproject.api.dto.mappingDto.ValCursMapDto;
import org.example.exchangeratewebapiproject.service.ValCursService;
import org.example.exchangeratewebapiproject.service.ValTypeService;
import org.example.exchangeratewebapiproject.service.ValuteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class ValuteController {


    private final ValCursService valCursService;


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    public List<ValCursMapDto> getValute() {
        return valCursService.getAllValCurs();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{date}")
    public ValCursMapDto getValuteByDate(@PathVariable @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date) {
        return valCursService.getValCursByDate(date);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{date}")
    public String postValute(@PathVariable @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date) {
        return valCursService.syncCurrData(date);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{date}/{nominal}/{code}")
    public ValCursDto getSpecificValute(@PathVariable @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date, @PathVariable
    double nominal, @PathVariable String code) {

        return valCursService.getVaCursByValute(date, nominal, code);
    }
}
