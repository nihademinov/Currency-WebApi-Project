package org.example.exchangeratewebapiproject.service;

import lombok.RequiredArgsConstructor;
import org.example.exchangeratewebapiproject.bussiness.management.ValuteManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValuteService {
    private final ValuteManager valuteManager;
}
