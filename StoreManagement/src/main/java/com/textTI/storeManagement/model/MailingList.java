package com.textTI.storeManagement.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_mailingList")
public class MailingList extends BaseModel {

	private static final long serialVersionUID = 530674444960715214L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "createdOn")
	private Date createdOn;
	
	@Column(name = "defaultFromName")
	private String defaultFromName;
	
	@Column(name = "defaultFromEmail")
	private String defaultFromEmail;
	
	@Column(name = "defaultSubject")
	private String defaultSubject;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_mailingList_client", 
				joinColumns = { @JoinColumn(name = "mailingList_id", updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "client_id", updatable = false) })
	private List<Client> clients;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public String getFormatedCreatedOn() {
		if(this.createdOn != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			return sdf.format(createdOn);
		}
		return "";
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getDefaultFromName() {
		return defaultFromName;
	}

	public void setDefaultFromName(String defaultFromName) {
		this.defaultFromName = defaultFromName;
	}

	public String getDefaultFromEmail() {
		return defaultFromEmail;
	}

	public void setDefaultFromEmail(String defaultFromEmail) {
		this.defaultFromEmail = defaultFromEmail;
	}

	public String getDefaultSubject() {
		return defaultSubject;
	}

	public void setDefaultSubject(String defaultSubject) {
		this.defaultSubject = defaultSubject;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public int getClientsListSize(){
		return this.clients.size();
	}
	
	@Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;

        if (! (obj instanceof MailingList)) 
            return false;
        
        return this.getId() == ((MailingList)obj).getId();
    }
}
