package com.application.people;

import com.application.View;
import com.application.tagging.TagController;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for managing the list of profiles
 */
@RestController
@CrossOrigin
@RequestMapping(path="/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="")
    @CrossOrigin
    public @ResponseBody Iterable<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    /**
     * Returns the profile of the user associated with the userId
     * @param userId
     * @return the profile of the user associated with the userId
     */
    @GetMapping(path= {"/{userId}"})
    @CrossOrigin
    public @ResponseBody Profile getByUserId(@PathVariable Long userId){
        User user = userRepository.findByid(userId);
        if(user != null){
            return profileRepository.findByOwner(user);
        }
        throw new ProfileController.ProfileNotFoundException("userId not valid");
    }

    /**
     * Creates a profile for the given userId
     * @param userId
     * @param profile
     * @return a notification if the profile was created successfully or not
     */
    @PostMapping(path="/{userId}")
    @CrossOrigin
    public String createProfileForUser(@PathVariable Long userId, @RequestBody RequestProfile profile){
        User user = userRepository.findByid(userId);
        if(profileRepository.findByOwner(user) == null){
            Profile toAdd = new Profile();
            toAdd.setTitle(profile.getTitle());
            toAdd.setIsSearch(profile.getIsSearch());
            toAdd.setProfilePicture(profile.getProfilePicture());
            toAdd.setPhoneNumber(profile.getPhoneNumber());
            toAdd.setLocation(profile.getLocation());
            toAdd.setContentType(profile.getContentType());
            toAdd.setUsername(profile.getUsername());
            toAdd.setContentPath(profile.getContentPath());
            toAdd.setTextContent(profile.getTextContent());
            toAdd.setOwner(profile.getOwner());
            profileRepository.save(toAdd);
            return "User profile for user ID number:" + profile.getOwner().getId() + " has been created";
        }else{
            return "User already has a profile";
        }
    }

    /**
     * Updates the profile of the given userId with new profile data
     * @param userId
     * @param profile
     * @return a notification if the profile was updated successfully or not
     */
    @PostMapping(path="/{userId}/update")
    @CrossOrigin
    public @ResponseBody String updateProfileForUser(@PathVariable Long userId, @RequestBody Profile profile){
        User user = userRepository.findByid(userId);
        Profile toUpdate = profileRepository.findByOwner(user);
        if(toUpdate != null){
            toUpdate.setLocation(profile.getLocation());
            toUpdate.setPhoneNumber(profile.getPhoneNumber());
            toUpdate.setProfilePicture(profile.getProfilePicture());
            profileRepository.save(toUpdate);
            return "user " + user.getId() + " has been updated";
        }else{
            return "invalid userId";
        }
    }


    private class ProfileNotFoundException extends IllegalArgumentException
    {
        public ProfileNotFoundException() {super();}
        public ProfileNotFoundException(String message) {super(message);}
    }
}
