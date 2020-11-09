package com.application.people;


/**
 * Profile wrapper for the proper construction of a profile
 */
public class RequestProfile {

    private String username;

    private String location;

    private String phoneNumber;

    private String profilePicture;

    private String title;

    private String textContent;

    private String contentPath;

    private String contentType;

    private User owner;

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
        this.title = profile.title;
        this.contentPath = profile.contentPath;
        this.textContent = profile.textContent;
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
