//import sun.security.util.IOUtils;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherWebApi {
    private static final String API_KEY;
    private static final String WEATHER_SERVICE =  "http://api.worldweatheronline.com/premium/v1/";
    private static final String WEATHER_PAST_TEMPLATE =
            "past-weather.ashx?q=%s&date=%s&enddate=%s&tp=24&format=csv&key=%s";
    private static final String WEATHER_SEARCH_TEMPLATE = "search.ashx?query=%s&format=tab&key=%s";

    /**
     * Retrieve API-KEY from resources
     * @return
     */
    private static String getApiKeyFromResources() {
        try {
            URL keyFile = ClassLoader.getSystemResource("worldweatheronline-app-key.txt");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(keyFile.openStream()))) {
                return reader.readLine();
            }

        }
        catch(IOException e) {
            throw new IllegalStateException(
                    "YOU MUST GET a KEY from developer.worldweatheronline.com and place it in src/main/resources/worldweatheronline-app-key.txt");
        }
    }

    /**
     * Static Constructor
     */
    static {
        API_KEY = getApiKeyFromResources();
    }

    /**
     * Get WeatherInfo's from a GPS local given a date interval
     * @param latitude
     * @param longitude
     * @param from
     * @param to
     * @return
     */
    public Iterable<WeatherInfo> pastWeather( double latitude, double longitude, LocalDate from, LocalDate to) {
        String query = latitude + "," + longitude;
        String path =  WEATHER_SERVICE + String.format(WEATHER_PAST_TEMPLATE, query, from, to, API_KEY);


        List<WeatherInfo> result = new ArrayList<>(); // where the WeatherInfo instances are collected

        try {
            // used to do the HTTP request to worldweatheronline service
            URL url = new URL(path);

            // the stream used to get the response to the service request
            InputStream respStream = url.openStream();
            Iterator<String> it;
            it = getContentLines(respStream).iterator();

            int ct=1;
            while(it.hasNext()){                    //"Saltar" as linhas que representam comentários
                if(!(it.next().contains("#"))){
                    break;
                }
            }
            String line=it.next();                  //line declarado para análise no debugger
            while(it.hasNext()) {

                if (it.hasNext() && ct % 2 == 0) {
                    result.add(WeatherInfo.valueOf(it.next()));
                    ct=0;
                    if(it.hasNext()) {
                        it.next();
                    }
                }
                ct++;
            }

//            int[] position = new int[]{0};
/*            BufferedReader reader = new BufferedReader(new InputStreamReader(respStream));


            while(reader.ready()) {

                oddline++;
                String line = reader.readLine();
                if(oddline%2==0 && oddline >9) {                      //Se não for linha par adiciona à Lista o Objeto Weather.Info
                    result.add(WeatherInfo.valueOf(line));

                }

            }
*/
/*
            Iterable<String> iterable = getContentLines(respStream);
            (iterable).forEach( s ->{

                position[0]++;
                if(position[0]%2==0 && position[0] >9){
                    result.add(WeatherInfo.valueOf(s));
                }

            });
*/

        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }

        return result;
    }

    /**
     * Get DayInfo's from a GPS local given a date interval
     * @param latitude
     * @param longitude
     * @param from
     * @param to
     * @return
     */
    public Iterable<DayInfo> pastDays( double latitude, double longitude, LocalDate from, LocalDate to) {
        String query = latitude + "," + longitude;
        String path =  WEATHER_SERVICE + String.format(WEATHER_PAST_TEMPLATE, query, from, to, API_KEY);


        List<DayInfo> result = new ArrayList<>(); // where the WeatherInfo instances are collected

        try {
            // used to do the HTTP request to worldweatheronline service
            URL url = new URL(path);

            // the stream used to get the response to the service request
            InputStream respStream = url.openStream();
            Iterator<String> it;
            it = getContentLines(respStream).iterator();

            int ct=1;
            while(it.hasNext()){                    //Passar as linhas que são comentário
                if(!(it.next().contains("#"))){
                    break;
                }
            }
                                //A linha impar é para contabilizar, logo não se chama o next() à cabeça
            while(it.hasNext()) {

                if (it.hasNext() && ct % 2 != 0) {
                    result.add(DayInfo.valueofDay(it.next()));
                    ct=0;
                    if(it.hasNext()) {
                        it.next();
                    }
                }
                ct++;
            }

        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }

    /**
     * Get local info given the name of the local
     * @param location
     * @return
     */
    public Iterable<Location> search(String location) {

        String path =  WEATHER_SERVICE + String.format(WEATHER_SEARCH_TEMPLATE, location, API_KEY);

        List<Location> result = new ArrayList<>();

        try {
            // used to do the HTTP request to worldweatheronline service
            URL url = new URL(path);

            // the stream used to get the response to the service request
            InputStream respStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(respStream));


                while(reader.ready()) {
                    String line = reader.readLine();
                    if(!line.contains("#")) {
                        result.add(Location.valueOfLocation(line));
                    }
                }

        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }


    /**
     * Get local info given the name of the local
     * @param input, path
     * @return iter String
     */             // ALTEREI OS PARAMS DE ENTRADA DO MÉTODO PARA RECEBER TAMBÉM PATH
    private Iterable<String> getContentLines(InputStream input){

        List<String> result = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));


            while(reader.ready()) {

                String line = reader.readLine();

                    result.add(line);
            }

        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }

        return result;
    }       //A ideia vai ser passar o iterável de Strings à função de recolha das infos que interessam (na aletração do método)
}
