package com.application.people;

import com.application.View;
import com.application.skill_level.AppliedSkillLevel;
import com.application.tagging.TagSkillLevelKey;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import java.util.Map;

/**
 * Profile wrapper for the proper construction of a profile
 */
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

    /**
     *Default constructor
     */
    public RequestProfile(){}

    /**
     * Copy constructor
     * @param profile
     */
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

    /**
     *
     * @return the username of the profile
     */
    public String getUsername(){return username;}

    /**
     *
     * @return the location of the profile
     */
    public String getLocation(){return location;}

    /**
     *
     * @return the phone number of the profile
     */
    public String getPhoneNumber(){return phoneNumber;}

    /**
     *
     * @return the profile picture file location of the profile
     */
    public String getProfilePicture(){return profilePicture;}

    /**
     *
     * @return the owner of the profile
     */
    public User getOwner(){return owner;}

    /**
     *
     * @return the title of the profile "post"
     */
    public String getTitle(){return title;}

    /**
     *
     * @return the content type of the profile, it will always return profile
     */
    public String getContentType(){return contentType;}
    public String getTextContent(){return textContent;}
    public String getContentPath(){return contentPath;}
    public Boolean getIsSearch(){return isSearch == 1;}
    public void setUsername(String username){
        this.username = username;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;

    }

    /**
     *
     * @param profilePicture
     */
    public void setProfilePicture(String profilePicture){
        this.profilePicture = profilePicture;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(User owner){
        this.owner = owner;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;

    }

    /**
     *
     * @param contentType
     */
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     *
     * @param textContent
     */
    public void setTextContent(String textContent){
        this.textContent = textContent;
    }

    /**
     *
     * @param contentPath
     */
    public void setContentPath(String contentPath){
        this.contentPath = contentPath;
    }

    /**
     *
     * @param isSearch
     */
    public void setIsSearch(Integer isSearch){
        this.isSearch = isSearch;
    }
}
