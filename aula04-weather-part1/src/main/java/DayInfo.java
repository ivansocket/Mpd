/*
   #The day information is available in following format:-
   #date,maxtempC,maxtempF,mintempC,mintempF,sunrise,sunset,moonrise,moonset,moon_phase,moon_illumination
   #2019-01-01,17,63,8,46,07:55 AM,05:26 PM,03:25 AM,02:25 PM,Waning Crescent,34
*/

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DayInfo {
    // indexes corresponding to fields to use
    private static final int DATE       =   0;
    private static final int MAX_TEMPC  =   1;
    private static final int MIN_TEMPC  =   3;
    private static final int SUNRISE    =   5;
    private static final int SUNSET     =   6;
    private static final int MOONRISE   =   7;
    private static final int MOONSET    =   8;
    private static final int MOON_PHASE =   9;
    private static final int MOON_ILLUM =   10;

    private final LocalDate date;
    private final int tempCMax;
    private final int tempCMin;
    private final String sunRise;
    private final String sunSet;
    private final String moonRise;
    private final String moonSet;
    private final String moonPhase;
    private final int moonIlum;



    public DayInfo(LocalDate date, int tempCMax, int tempCMin, String sunRise, String sunSet, String moonRise, String moonSet, String moonPhase, int moonIlum) {
        this.date = date;
        this.tempCMax = tempCMax;
        this.tempCMin = tempCMin;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.moonRise= moonRise;
        this.moonSet= moonSet;
        this.moonPhase= moonPhase;
        this.moonIlum= moonIlum;
    }

    public static DayInfo valueofDay(String line) {
        String[] parts = line.split(",");
        //Erro provém de não haver essa informação no array parts, visto não fazer parte da linha das informações diárias
        return new DayInfo(
                LocalDate.parse(parts[DATE]),
                Integer.parseInt(parts[MAX_TEMPC]),
                Integer.parseInt(parts[MIN_TEMPC]),
                parts[SUNRISE],
                parts[SUNSET],
                parts[MOONRISE],
                parts[MOONSET],
                parts[MOON_PHASE],
                Integer.parseInt(parts[MOON_ILLUM]));


    }


    public int getMoonIlum() {
        return moonIlum;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public String getMoonSet() {
        return moonSet;
    }

    public String getMoonRise() {
        return moonRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getSunRise() {
        return sunRise;
    }

    public int getTempCMin() {
        return tempCMin;
    }

    public int getTempCMax() {
        return tempCMax;
    }

    public LocalDate getDate() {
        return date;
    }
}
