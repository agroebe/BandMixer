package com.application.locations;

import com.application.View;
import com.application.people.Profile;
import com.application.people.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Controller for handling the logic for managing the list of states
 */
@Controller
@CrossOrigin
@RequestMapping(path="/states") // This means the URLs will start with /experiment
public class StateController {
    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    /**
     *
     * @return a list of all the states in the stateRepository
     */
    @JsonView(View.StateView.class)
    @GetMapping(path="")
    @CrossOrigin
    public @ResponseBody Iterable<State> getAllStates(){
        return stateRepository.findAll();
    }

    /**
     * Adds a new state to the state list
     * @param state
     * @return a notification if the state was added successfully or not
     */
    @PostMapping(path ="")
    public @ResponseBody String addNewState(@RequestBody State state){
        if(stateRepository.findByStateName(state.getStateName())== null){
            State toAdd = new State(state);
            stateRepository.save(toAdd);
            return "State: " + state.getStateName() + " has been added";
        }else{
            return "State: " + state.getStateName() + " already exists";
        }
    }

    /**
     * Fetches a state by its name
     * @param stateName
     * @return the state that was found
     */
    @JsonView(View.StateView.class)
    @GetMapping(path="/{stateName}")
    public @ResponseBody State getStateByName(@PathVariable String stateName){
        return stateRepository.findByStateName(stateName);
    }

    /**
     * Adds a city to a specific state
     * @param city
     * @return a notification if the city was added successfully or not
     */
    @PostMapping(path = "/city")
    public @ResponseBody String addCityToState(@RequestBody City city){
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

    /**
     * Removes a state from the state list
     * @param stateName
     * @return a notification if the state was removed successfully or not
     */
    @DeleteMapping(path ="/{stateName}")
    public @ResponseBody String removeState(@PathVariable String stateName){
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

//TODO
//    @DeleteMapping(path = "/city/{cityName}")
//    public @ResponseBody String removeCity(@PathVariable String cityName){
//        City city = cityRepository.findByCityName(cityName);
//        if(city != null){
//            cityRepository.delete(city);
//            return "city deleted";
//        }else{
//            return "city doesn't exist";
//        }
//    }

}
