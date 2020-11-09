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

<<<<<<< HEAD
    private Integer isSearch;

    /**
     *Default constructor
     */
=======
>>>>>>> 6a3b621ae0c0be195f8c3518a58248a343b1d661
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
<<<<<<< HEAD

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
=======
    public String getTitle(){return title;}
>>>>>>> 6a3b621ae0c0be195f8c3518a58248a343b1d661
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
<<<<<<< HEAD

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
=======
    
>>>>>>> 6a3b621ae0c0be195f8c3518a58248a343b1d661
    public void setTitle(String title){
        this.title = title;

    }
<<<<<<< HEAD

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
=======
    
>>>>>>> 6a3b621ae0c0be195f8c3518a58248a343b1d661
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

<<<<<<< HEAD
    /**
     *
     * @param isSearch
     */
    public void setIsSearch(Integer isSearch){
        this.isSearch = isSearch;
    }
=======
>>>>>>> 6a3b621ae0c0be195f8c3518a58248a343b1d661
}
