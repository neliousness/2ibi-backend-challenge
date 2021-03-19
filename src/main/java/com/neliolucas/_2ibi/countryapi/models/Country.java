package com.neliolucas._2ibi.countryapi.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uid;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String capital;

    @Column(nullable = false)
    @NotNull
    private String region;

    @Column(nullable = false)
    @NotNull
    private String subRegion;

    @Column(nullable = false)
    @NotNull
    private double area;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void updateCountry(Country country)
    {
        this.name = country.getName();
        this.capital = country.getCapital();
        this.region = country.getRegion();
        this.subRegion = country.getSubRegion();
        this.area = country.getArea();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return uid == country.uid &&
                Double.compare(country.area, area) == 0 &&
                Objects.equals(name, country.name) &&
                Objects.equals(capital, country.capital) &&
                Objects.equals(region, country.region) &&
                Objects.equals(subRegion, country.subRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capital, region, subRegion, area);
    }

    @Override
    public String toString() {
        return "Country{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", subRegion='" + subRegion + '\'' +
                ", area=" + area +
                '}';
    }
}
