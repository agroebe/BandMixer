package com.application.people;

import com.application.View;
import com.application.skill_level.AppliedSkillLevel;
import com.application.tagging.TagSkillLevelKey;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import java.util.Map;

public class RequestProfile {

    private String username;

    private String location;

    private String phoneNumber;

    private String profilePicture;

    private User owner;

    private String title;

    private String contentType;

    private String textContent;

    private String contentPath;

    private Integer isSearch;

    public RequestProfile(){}

    public RequestProfile(RequestProfile profile){
        this.username = profile.username;
        this.location = profile.location;
        this.phoneNumber = profile.phoneNumber;
        this.profilePicture = profile.profilePicture;
        this.owner = profile.owner;
        this.title = profile.title;
        this.contentPath = profile.contentPath;
        this.contentType = profile.contentType;
        this.textContent = profile.textContent;
        this.isSearch = profile.isSearch;
    }

    public String getUsername(){return username;}
    public String getLocation(){return location;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getProfilePicture(){return profilePicture;}
    public User getOwner(){return owner;}
    public String getTitle(){return title;}
    public String getContentType(){return contentType;}
    public String getTextContent(){return textContent;}
    public String getContentPath(){return contentPath;}
    public Boolean getIsSearch(){return isSearch == 1;}
    public void setUsername(String username){
        this.username = username;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;

    }
    public void setProfilePicture(String profilePicture){
        this.profilePicture = profilePicture;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }
    public void setTitle(String title){
        this.title = title;

    }
    public void setContentType(String contentType){
        this.contentType = contentType;
    }
    public void setTextContent(String textContent){
        this.textContent = textContent;
    }
    public void setContentPath(String contentPath){
        this.contentPath = contentPath;
    }
    public void setIsSearch(Integer isSearch){
        this.isSearch = isSearch;
    }
}
