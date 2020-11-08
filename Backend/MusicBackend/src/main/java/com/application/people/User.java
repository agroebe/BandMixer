package com.application.people;

import org.springframework.data.jpa.repository.Modifying;

import com.application.View;
import com.application.skill_level.AppliedSkillLevel;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;
import com.application.posts.Post;

/**
 * User entity, contains all the information for a user
 */
@Entity
@Table(name="USERS")
public class User 
{
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    private String email;

	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    private String username;

	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    private String password;
    
    @JsonView(View.UserView.class)
	@OneToMany(mappedBy="owner")
    @MapKey(name="id")
	private Map<Long,Post> posts;

    @JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Column(name = "is_signed_in", nullable=false)
    private Integer staySignedIn;

    /**
     * Default constructor
     */
    public User() {}

    /**
     * Constructor that accepts all the user fields
     * @param email
     * @param username
     * @param password
     * @param staySignedIn
     */
    public User(String email, String username, String password, Integer staySignedIn){
        this.email = email;
        this.username = username;
        this.password = password;
        this.staySignedIn = staySignedIn;
    }

    /**
     *
     * @return the Id of the user
     */
    public Long getId(){
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password){this.password = password;}

    /**
     *
     * @return the password of the user
     */
    public String getPassword(){return password;}

    /**
     *
     * @return the name of the user
     */
    public String getName(){
        return username;
    }

    /**
     *
     * @param name
     */
    public void setName(String name){
        this.username = name;
    }

    /**
     *
     * @return the email of the user
     */
    public String getEmail(){
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email){ this.email = email;}

    /**
     *
     * @param staySignedIn
     * @return
     */
    public boolean setStaySignedIn(boolean staySignedIn)
    {
        this.staySignedIn = (staySignedIn ? 1 : 0);
        return true;
    }

    /**
     *
     * @return if the user wants to stay signed in or not
     */
    public Boolean getStaySignedIn()
    {
        return staySignedIn==0 ? false : true;
    }

    /**
     *
     * @return a list of posts that the user owns
     */
    public Map<Long, Post> getPosts()
    {
    	if(posts == null)
    	{
    		posts = new HashMap<Long,Post>();
    	}
    	return posts;
    }
}
