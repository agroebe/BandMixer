package com.application.people;

import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name="USERS")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ID;

    private String email;

    private String username;

    private String password;

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

}
