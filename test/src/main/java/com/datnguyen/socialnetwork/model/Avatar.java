package com.datnguyen.socialnetwork.model;
// Generated Jan 27, 2015 3:14:30 PM by Hibernate Tools 4.3.1


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;



@Entity
@Table(name="avatar") 
public class Avatar  {


	private String userName;
	private byte[] avatar;

    public Avatar() {
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
    
    /*
     * 	TINYBLOB: maximum length of 255 bytes
		BLOB: maximum length of 65,535 bytes
		MEDIUMBLOB: maximum length of 16,777,215 bytes
		LONGBLOB: maximum length of 4,294,967,295 bytes
     */
    
    @Column(columnDefinition = "LONGBLOB")
	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
}


