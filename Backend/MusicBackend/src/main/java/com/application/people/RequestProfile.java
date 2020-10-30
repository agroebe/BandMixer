package com.application.people;


public class RequestProfile {

    private String username;

    private String location;

    private String phoneNumber;

    private String profilePicture;

    private String title;

    private String textContent;

    private String contentPath;

    public RequestProfile(){}

    public RequestProfile(RequestProfile profile){
    	
        this.username = profile.username;
        this.location = profile.location;
        this.phoneNumber = profile.phoneNumber;
        this.profilePicture = profile.profilePicture;
        this.title = profile.title;
        this.contentPath = profile.contentPath;
        this.textContent = profile.textContent;
    }

    public String getUsername(){return username;}
    public String getLocation(){return location;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getProfilePicture(){return profilePicture;}
    public String getTitle(){return title;}
    public String getTextContent(){return textContent;}
    public String getContentPath(){return contentPath;}
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
    
    public void setTitle(String title){
        this.title = title;

    }
    
    public void setTextContent(String textContent){
        this.textContent = textContent;
    }
    public void setContentPath(String contentPath){
        this.contentPath = contentPath;
    }

}
