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
package com.muratcanabay.coronaviruscasereporter.domain;

/**
 * Reports
 *
 * @author Murat Can Abay
 * @since 0.21.0
 */
public class Reports {
    private String state;
    private String country;
    /**
     * totalCase: By country
     */
    private Long totalCase;
    private Long todayCase;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(Long totalCase) {
        this.totalCase = totalCase;
    }

    public Long getTodayCase() {
        return todayCase;
    }

    public void setTodayCase(Long todayCase) {
        this.todayCase = todayCase;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCase=" + totalCase +
                ", todayCase=" + todayCase +
                '}';
    }
}
