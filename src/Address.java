public class Address {
    private String label;
    private String house;
    private String society;
    private String city;

    Address() {
        this.label = "";
        this.house = "";
        this.society = "";
        this.city = "";
    }

    public void Address(String label, String house, String society, String city) {
        this.label = label;
        this.house = house;
        this.society = society;
        this.city = city;
    }

    @Override
    public String toString() {
        return label + ": " + house + ", " + society + ", " + city;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
