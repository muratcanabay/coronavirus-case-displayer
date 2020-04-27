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
package com.muratcanabay.coronaviruscasereporter.service;

import com.muratcanabay.coronaviruscasereporter.domain.Reports;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * CoronaVirusDataService
 *
 * @author Murat Can Abay
 * @since 0.21.0
 */

@Service
public class CoronaVirusDataService {

    private static String CORONA_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String CORONA_VIRUS_DEATH_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    private List<Reports> reportsList = new ArrayList<>();

    /**
     * Url'e istekte bulunur ve csv response'u döner.
     *
     * @throws IOException
     * @throws InterruptedException
     * @see: PostContruct = CoronaVirusDataService'dan instance oluşturduğunda,
     * instance oluştuktan sonra bu methodu çağır
     */
    @PostConstruct

    /**
     * Datalar her gün yenilendiğinden bu methodun her gün çalışması gerekiyor. Main methodda @EnableScheduling yapmayı unutma
     * @see: @Scheduled = Her 20 dkda bir çalışmasını istiyoruz
     */
    @Scheduled(cron = "* * 1 * * *")
    public void fetchCoronaVirusData() throws IOException, InterruptedException {

        List<Reports> newReportsList = new ArrayList<>();

        HttpClient httpClient = getHttpClient();
        HttpRequest httpRequest = getHttpRequest(CORONA_VIRUS_DATA_URL);
        HttpResponse<String> httpResponse = getHttpResponse(httpRequest, httpClient);

        StringReader csv = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv);
        for (CSVRecord record : records) {
            Reports newReport = new Reports();

            Long totalCase = Long.valueOf(record.get(record.size() - 1));
            Long previousDayCase = Long.valueOf(record.get(record.size() - 2));
            Long todayCase = totalCase - previousDayCase;

            newReport.setState(record.get("Province/State"));
            newReport.setCountry(record.get("Country/Region"));
            newReport.setTotalCase(totalCase);
            newReport.setTodayCase(todayCase);
            newReportsList.add(newReport);
        }
        reportsList = newReportsList;
    }

    public HttpClient getHttpClient() {
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient;
    }

    public HttpRequest getHttpRequest(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return httpRequest;
    }

    public HttpResponse<String> getHttpResponse(HttpRequest httpRequest, HttpClient httpClient) throws IOException, InterruptedException {
        HttpResponse<String> httpResponse = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse;
    }

    public List<Reports> getReportsList() {
        return reportsList;
    }

    /**
     * Calculates total case number in the world.
     * @param reportsList
     * @return totalCaseWorld
     */
    public int getTotalCase(List<Reports> reportsList){
        int totalCaseWorld = reportsList.stream().mapToInt(reports -> Math.toIntExact(reports.getTotalCase())).sum();
        return totalCaseWorld;
    }
}
