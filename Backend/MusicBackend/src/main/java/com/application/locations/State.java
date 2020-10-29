package com.application.locations;

import com.application.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STATES")
public class State {

    @Id
    @JsonView(View.StateView.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    @JsonView(View.StateView.class)
    @Column(name = "stateName")
    private String stateName;

    @JsonView(View.StateView.class)
    @OneToMany(mappedBy = "state")
    private Set<City> cities;

    public State(){}

    public State(State state){
        this.ID = state.ID;
        this.stateName = state.stateName;
        this.cities = state.cities;
    }

    public Long getID(){return this.ID;}
    public String getStateName(){return this.stateName;}
    public Set<City> getCities(){return this.cities;}
    public void setID(Long ID){this.ID = ID;}
    public void setStateName(String stateName){this.stateName = stateName;}
    public void setCities(Set<City> cities){this.cities = cities;}

}
