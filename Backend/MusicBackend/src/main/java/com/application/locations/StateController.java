package com.application.locations;

import com.application.people.Profile;
import com.application.people.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping(path="/states") // This means the URLs will start with /experiment
public class StateController {
    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    @GetMapping(path="")
    @CrossOrigin
    public @ResponseBody Iterable<State> getAllStates(){
        return stateRepository.findAll();
    }

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

    @PostMapping(path = "/city")
    public @ResponseBody String addCityToState(@RequestBody City city, @RequestParam String stateName){
        State state = stateRepository.findByStateName(stateName);
        if(state != null){
            City toAddCity = new City(city);
            cityRepository.save(toAddCity);
            state.addCity(city);
            stateRepository.save(state);
            return "City: " + city.getCityName() + "has been added to State:" + state.getStateName();
        }else{
            return "State doesn't exist";
        }
    }
}
