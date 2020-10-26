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

@Entity
@Table(name="USERS")
public class User 
{
	@JsonView({View.TagView.class, View.SkillLevelView.class, View.PostView.class, View.UserView.class})
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

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

    public User() {}

    public Long getId(){
        return ID;
    }

    public void setId(Long id){
        this.ID = id;
    }

    public void setPassword(String password){this.password = password;}

    public String getPassword(){return password;}

    public String getName(){
        return username;
    }

    public void setName(String name){
        this.username = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){ this.email = email;}

    public boolean setStaySignedIn(boolean staySignedIn)
    {
        this.staySignedIn = (staySignedIn ? 1 : 0);
        return true;
    }

    public Boolean getStaySignedIn()
    {
        return staySignedIn==0 ? false : true;
    }

    public Map<Long, Post> getPosts()
    {
    	if(posts == null)
    	{
    		posts = new HashMap<Long,Post>();
    	}
    	return posts;
    }
}
