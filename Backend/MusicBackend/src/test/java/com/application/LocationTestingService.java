package com.application;

import com.application.locations.City;
import com.application.locations.CityRepository;
import com.application.locations.State;
import com.application.locations.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class LocationTestingService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    public String addNewState( State state){
        if(stateRepository.findByStateName(state.getStateName())== null){
            State toAdd = new State(state);
            stateRepository.save(toAdd);
            return "State: " + state.getStateName() + " has been added";
        }else{
            return "State: " + state.getStateName() + " already exists";
        }
    }


    public State getStateByName(@PathVariable String stateName){
        return stateRepository.findByStateName(stateName);
    }


    public String addCityToState(City city){
        State state = stateRepository.findByStateName(city.getState().getStateName());
        if(state != null){
            if(cityRepository.findByCityName(city.getCityName()) == null){
                City toAddCity = new City(city);
                city.setState(state);
                cityRepository.save(city);
                return "City: " + city.getCityName() + " has been added to State:" + state.getStateName();
            }else{
                return "City already exists for this state";
            }
        }else{
            return "State doesn't exist";
        }
    }

    public String removeState(String stateName){
        State state = stateRepository.findByStateName(stateName);
        if(state != null){
            for(City c : state.getCities()){
                cityRepository.delete(c);
            }
            stateRepository.delete(state);
            return "state deleted";
        }else{
            return "state doesn't exist";
        }
    }

}
