package com.application.people;

import com.application.tagging.TagController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping(path="/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepository repository;

    @GetMapping(path="/")
    @CrossOrigin
    public @ResponseBody Iterable<Profile> getAllProfiles(){
        return repository.findAll();
    }

    @GetMapping(path= {"/{userId}"})
    @CrossOrigin
    public @ResponseBody Profile getByUserId(@PathVariable Long userId){
        if(userId != null){
            return repository.findByUserId(userId);
        }
        throw new ProfileController.ProfileNotFoundException("userId not valid");
    }


    @PostMapping(path="/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody String createProfileForUser(@PathVariable Long userId, @RequestBody Profile profile){
        if(repository.findByUserId(profile.getUserId()) == null){
            Profile toAdd = new Profile(profile);
            repository.save(toAdd);
            return "User profile for user ID number:" + profile.getUserId() + " has been created";
        }else{
            return "User already has a profile";
        }
    }


    private class ProfileNotFoundException extends IllegalArgumentException
    {
        public ProfileNotFoundException() {super();}
        public ProfileNotFoundException(String message) {super(message);}
    }
}
