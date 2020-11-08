package com.application.people;

//TODO Attach profiles to users

import com.application.View;
import com.application.posts.Post;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

/**
 * Profile entity, extends in order to facilitate searching for profiles. Since we need to be able to add tags to profiles.
 */
@Entity
@Table(name="PROFILES")
public class Profile extends Post {

    @JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Column(name="username")
    private String username;
    @JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Column(name="location")
    private String location;
    @JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Column(name="phoneNumber")
    private String phoneNumber;
    @JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Column(name="profilePicture")
    private String profilePicture;
    //private Long userId;

    /**
     * Default constructor
     */
    public Profile(){
        username = null;
        location = null;
        phoneNumber = null;
        profilePicture=null;
       // userId = null;
        super.setContentType("Profile");
    }

    /**
     * Constructor that accepts all parameters of a profile
     * @param username
     * @param location
     * @param phoneNumber
     * @param profilePicture
     * @param userId
     * @param title
     * @param isSearch
     */
    public Profile(String username,String location, String phoneNumber, String profilePicture, Long userId, String title, Boolean isSearch){
        this.username = username;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        super.setTitle(title);
        super.setIsSearch(isSearch);
        super.setContentType("Profile");
        //this.userId = userId;
    }

    /**
     * Copy constructor
     * @param profile
     */
    public Profile(Profile profile){
        this.username = profile.username;
        this.location = profile.location;
        this.profilePicture = profile.profilePicture;
        this.phoneNumber = profile.phoneNumber;
        super.setTitle(profile.getTitle());
        super.setIsSearch(profile.getIsSearch());
        super.setContentType("Profile");
        //this.userId = profile.userId;
    }

   // public Long getUserId(){return userId;}

    /**
     *
     * @return the location of the profile
     */
    public String getLocation(){ return location;}

    /**
     *
     * @return the phone number of the profile
     */
    public String getPhoneNumber(){return phoneNumber;}

    /**
     *
     * @return the profile picture file path
     */
    public String getProfilePicture(){return profilePicture;}

    /**
     *
     * @return the username of the profile
     */
    public String getUsername(){return username;}

    /**
     *
     * @param username
     */
    public void setUsername(String username){this.username = username;}
    //public void setUserId(Long userId){this.userId = userId;}

    /**
     *
     * @param location
     */
    public void setLocation(String location){this.location = location;}

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    /**
     *
     * @param profilePicture
     */
    public void setProfilePicture(String profilePicture){this.profilePicture = profilePicture;}
    
    @Override
    public void setContentType(String type){}

}
