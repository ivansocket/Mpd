public class Location {
    // indexes corresponding to fields to use
    private static final int NAME       =   0;
    private static final int COUNTRY    =   1;
    private static final int DISTRICT   =   2;
    private static final int LATITUDE   =   3;
    private static final int LONGITUDE  =   4;

    private final String name;
    private final String country;
    private final String district;
    private final String latitude;
    private final String longitude;

    public Location(String name, String country, String district, String latitude, String longitude) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public static Location valueOfLocation(String line) {
    //http://api.worldweatheronline.com/premium/v1/search.ashx?query=Lisbon&num_of_results=3&format=json&key=e1be80afb7594ee29b3135503190303
        String[] parts = line.split("\t");
        System.out.println(line);
        //Erro provém de não haver essa informação no array parts, visto não fazer parte da linha das informações diárias
        return new Location(
                parts[NAME],
                parts[COUNTRY],
                parts[DISTRICT],
                parts[LATITUDE],
                parts[LONGITUDE]);

                    }
    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDistrict() {
        return district;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }
}