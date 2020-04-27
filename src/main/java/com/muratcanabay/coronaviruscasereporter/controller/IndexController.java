/*
 * Copyright 2020 Universal Bilgi Teknolojileri.
 *
 * UKL 1.1 lisansı ile lisanslanmıştır. Bu dosyanın lisans koşullarına uygun
 * olmayan şekilde kullanımı yasaklanmıştır. Lisansın bir kopyasını aşağıdaki
 * linkten edinebilirsiniz.
 *
 * http://www.uni-yaz.com/lisans/ukl_1_1.pdf
 *
 * Yasalar aksini söylemediği veya yazılı bir sözleşme ile aksi belirtilmediği sürece,
 * bu yazılım mevcut hali ile hiç bir garanti vermeden veya herhangi bir şart ileri
 * sürmeden dağıtılır. Bu yazılımın edinim izinleri ve limitler konusunda lisans
 * sözleşmesine bakınız.
 *
 */
package com.muratcanabay.coronaviruscasereporter.controller;

import com.muratcanabay.coronaviruscasereporter.domain.Reports;
import com.muratcanabay.coronaviruscasereporter.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * IndexController
 *
 * @author Murat Can Abay
 * @since 0.21.0
 */
@Controller
public class IndexController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String index(Model model) {
        List<Reports> reportsList = coronaVirusDataService.getReportsList();
        int totalCase = coronaVirusDataService.getTotalCase(reportsList);
        model.addAttribute("dataList", reportsList);
        model.addAttribute("totalCasesReported", totalCase);
        return "index";
    }
}
