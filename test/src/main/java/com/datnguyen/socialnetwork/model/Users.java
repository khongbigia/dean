package com.datnguyen.socialnetwork.model;
// Generated Jan 27, 2015 3:14:30 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;



@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="Users.findUserByEmail", query="SELECT u FROM Users u WHERE u.email = :email"),
	//@NamedQuery(name="Users.findAllUsernameNotFriend", query="SELECT u.userName FROM Users u WHERE u.userName NOT IN (SELECT g.username FROM groupsandusers g where g.groupname = :groupname)")
})

public class Users implements Serializable {


	private String userName;
    private String lastName;
     private String firstName;
     private String email;
     private String password;
     private double longitude;
     private double latitude;
     private boolean enabled;
     private Date currentTime;
     private Date dateModified;
     private boolean shareLocation;
     private Date dateCreated;

    public Users() {
    }

	
   
    public Users(String userName, String lastName, String firstName, double longitude, double latitude, boolean enabled, boolean shareLocation, String email, String password, Date currentTime, Date dateCreated, Date dateModified) {
       this.userName = userName;
       this.lastName = lastName;
       this.firstName = firstName;
       this.email = email;
       this.password = password;
       this.longitude = longitude;
       this.latitude = latitude;
       this.enabled = enabled;
       this.shareLocation = shareLocation;
       this.dateCreated = dateCreated;
       this.currentTime = currentTime;
       this.dateModified = dateModified;
    }
   
    @Id 
    @NotNull
    @Column(name="user_name", length=45)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    @Column(name="last_name", nullable=false, length=45)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="first_name", nullable=false, length=45)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="email", nullable=false, length=45, unique = true)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="password", nullable=false, length=128)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="longitude" , nullable=false)
    public double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }     
    
    @Column(name="latitude" , nullable=false)
    public double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    @Type(type = "true_false")
    @Column(name="enabled", nullable=false)
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    @Column(name="currenttime")
    public Date getCurrentTime() {
        return this.currentTime;
    }
    
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
    

    @Column(name="date_modified")
    public Date getDateModified() {
        return this.dateModified;
    }
    
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Column(name="share_location", nullable=false)
    public boolean isShareLocation() {
        return this.shareLocation;
    }
    
    public void setShareLocation(boolean shareLocation) {
        this.shareLocation = shareLocation;
    }

     @Column(name="datecreated")
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}


