package com.datnguyen.socialnetwork.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "groupsandusers")
@NamedQueries({
	@NamedQuery(name="groupsandusers.isMakeFriendRequest", query="SELECT g.id FROM Groupsandusers g WHERE g.username = :username AND g.status='waiting accept'"),
	@NamedQuery(name="groupsandusers.getAllListWaitingFriendAccept", query="SELECT g FROM Groupsandusers g WHERE g.username = :username AND g.status='waiting accept'"),
	@NamedQuery(name="groupsandusers.getListFriendAccepted", query="SELECT g.username FROM Groupsandusers g WHERE g.groupname = :groupname AND g.status='accepted'")
})
public class Groupsandusers {
	private Integer id;
	private String username;
	private String groupname;
	private String status;
	
	public Groupsandusers(){
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
    @Column(name="user_name", length=45)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
    @Column(name="groupname", length=80)
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	@NotNull
    @Column(name = "status", length=80)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
