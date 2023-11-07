package model;

public class Internship implements Comparable<Internship> {
    private String title;
    private String description;
    private String town;
    private String adress;
    private String speciality;
    private Integer year;
    private String company;
    private String site;
    private String country;

    public Internship(String _title, String _description, String _town, String _adress, String _speciality, Integer _year, String _company, String _site, String _country) {
        title = _title;
        description = _description;
        town = _town;
        adress = _adress;
        speciality = _speciality;
        year = _year;
        company = _company;
        site = _site;
        country = _country;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAdress() {
        return adress;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Integer getYear() {
        return year;
    }

    public String getCompany() {
        return company;
    }

    public String getSite() {
        return site;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }
}
