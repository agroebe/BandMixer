package com.application.people;

//TODO Attach profiles to users

import com.application.View;
import com.application.posts.Post;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

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

    public Profile(){
        username = null;
        location = null;
        phoneNumber = null;
        profilePicture=null;
       // userId = null;
        super.setContentType("Profile");
    }

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
    public String getLocation(){ return location;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getProfilePicture(){return profilePicture;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    //public void setUserId(Long userId){this.userId = userId;}
    public void setLocation(String location){this.location = location;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setProfilePicture(String profilePicture){this.profilePicture = profilePicture;}
    
    @Override
    public void setContentType(String type){}

}
