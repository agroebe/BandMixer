package com.application;

import com.application.locations.City;
import com.application.locations.CityRepository;
import com.application.locations.State;
import com.application.locations.StateRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationAddingTest {

    @Autowired
    private LocationTestingService service;

    @MockBean
    private StateRepository stateRepository;

    @MockBean
    private CityRepository cityRepository;

    @Test
    public void addNewStateTest(){
        State newState = new State();
        newState.setStateName("TestState");
        Mockito.when(stateRepository.save(newState)).thenReturn(newState);
        assertEquals("State: " + newState.getStateName() + " has been added", service.addNewState(newState));
    }

    @Test
    public void getStateByNameTest(){
        State newState = new State();
        String stateName = "TestState";
        newState.setStateName(stateName);
        Mockito.when(stateRepository.findByStateName(stateName)).thenReturn(newState);
        assertEquals(newState, service.getStateByName(stateName));
    }


}
