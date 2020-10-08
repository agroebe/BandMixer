package com.application.people;

//TODO Attach profiles to users

import javax.persistence.*;

@Entity
@Table(name="PROFILES")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    private String username;
    private String location;
    private String phoneNumber;
    private String profilePicture;
    private Long userId;

    public Profile(){
        username = null;
        location = null;
        phoneNumber = null;
        profilePicture=null;
        userId = null;
    }

    public Profile(String username,String location, String phoneNumber, String profilePicture, Long userId){
        this.username = username;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.userId = userId;
    }

    public Profile(Profile profile){
        this.username = profile.username;
        this.location = profile.location;
        this.profilePicture = profile.profilePicture;
        this.phoneNumber = profile.phoneNumber;
        this.userId = profile.userId;
    }

    public Long getUserId(){return userId;}
    public String getLocation(){ return location;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getProfilePicture(){return profilePicture;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public void setUserId(Long userId){this.userId = userId;}
    public void setLocation(String location){this.location = location;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setProfilePicture(String profilePicture){this.profilePicture = profilePicture;}

}
