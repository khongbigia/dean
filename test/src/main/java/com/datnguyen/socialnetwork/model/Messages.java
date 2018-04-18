package com.datnguyen.socialnetwork.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "Messages.findAll", query = "SELECT m FROM Messages m"),
    @NamedQuery(name = "Messages.findByMessageid", query = "SELECT m FROM Messages m WHERE m.messageid = :messageid"),
    @NamedQuery(name = "Messages.findBySender", query = "SELECT m FROM Messages m WHERE m.sender = :sender"),
    @NamedQuery(name = "Messages.findByReceiver", query = "SELECT m FROM Messages m WHERE m.receiver = :receiver"),
    @NamedQuery(name = "Messages.hasUnseenMessages", query = "SELECT m FROM Messages m WHERE m.receiver = :receiver AND m.status='Unseen'"),
    @NamedQuery(name = "Messages.findByContent", query = "SELECT m FROM Messages m WHERE m.content = :content"),
    @NamedQuery(name = "Messages.findByStatus", query = "SELECT m FROM Messages m WHERE m.status = :status"),
    @NamedQuery(name = "Messages.getListFriendsSentMessage", query = "SELECT distinct m.sender FROM Messages m WHERE m.receiver = :receiver AND m.status='Unseen'"),
    @NamedQuery(name = "Messages.findUnseenMessages", query = "SELECT m FROM Messages m WHERE m.status = 'Unseen' AND m.sender =:sender AND m.receiver = :receiver")})

public class Messages {

    private Integer messageid;
    
    private String sender;

    private String receiver;

    private String content;

    private String status;
    
    private Date dateCreated;

    public Messages() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "messageid")
    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

	@NotNull
    @Column(name = "sender", length=45)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @NotNull
    @Column(name = "receiver", length=45)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @NotNull
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
  
    @NotNull
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="datecreated")
    public Date getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
