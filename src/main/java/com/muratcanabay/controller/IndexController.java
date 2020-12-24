package com.muratcanabay.controller;

import com.muratcanabay.domain.Reports;
import com.muratcanabay.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
