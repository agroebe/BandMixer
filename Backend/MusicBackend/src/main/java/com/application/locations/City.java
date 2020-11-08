package com.application.locations;

import com.application.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * City entity
 */
@Entity
@Table(name = "CITIES")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @JsonView(View.StateView.class)
    @Column(name = "cityName")
    private String cityName;

    /**
    Default constructor
     */
    public City(){}

    /**
    Copy constructor
     * @param city
     */
    public City(City city){
        this.state = city.state;
        this.cityName = city.cityName;
        this.ID = city.ID;
    }

    /**
     * @return the ID of the city
     */
    public Long getID() { return ID; }
    /**
     * @return the state the city is attached to
     */
    public State getState(){return state;}
    /**
     * @return the name of the city
     */
    public String getCityName(){return cityName;}
    /**
     * @param ID
     */
    public void setID(Long ID){this.ID = ID;}
    /**
     * @param state
     */
    public void setState(State state){this.state = state;}
    /**
     * @param cityName
     */
    public void setCityName(String cityName){this.cityName = cityName;}
}