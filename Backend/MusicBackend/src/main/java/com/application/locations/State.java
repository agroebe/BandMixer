package com.application.locations;

import com.application.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

/**
 * State entity that contains a list of cities associated with it.
 */
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

    /**
     * Default constructor
     */
    public State(){}

    /**
     * Copy constructor
     * @param state
     */
    public State(State state){
        this.ID = state.ID;
        this.stateName = state.stateName;
        this.cities = state.cities;
    }

    /**
     *
     * @return the ID of the state
     */
    public Long getID(){return this.ID;}

    /**
     *
     * @return the name of the state
     */
    public String getStateName(){return this.stateName;}

    /**
     *
     * @return the list of cities attached to this state
     */
    public Set<City> getCities(){return this.cities;}

    /**
     *
     * @param ID
     */
    public void setID(Long ID){this.ID = ID;}

    /**
     *
     * @param stateName
     */
    public void setStateName(String stateName){this.stateName = stateName;}

    /**
     *
     * @param cities
     */
    public void setCities(Set<City> cities){this.cities = cities;}

}
