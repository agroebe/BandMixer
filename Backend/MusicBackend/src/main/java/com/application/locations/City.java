package com.application.locations;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
public class City {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(name = "cityName")
    private String cityName;

    public City(){}

    public City(City city){
        this.state = city.state;
        this.cityName = city.cityName;
        this.ID = city.ID;
    }

    public Long getID() { return ID; }
    public State getState(){return state;}
    public String getCityName(){return cityName;}
    public void setID(Long ID){this.ID = ID;}
    public void setState(State state){this.state = state;}
    public void setCityName(String cityName){this.cityName = cityName;}
}
