package com.textTI.storeManagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_clientType")
public class ClientType extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	@Column(name="name", nullable=false, unique=true)	
	private String name;

	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="clientType", fetch=FetchType.EAGER)
	private List<Client> clients;
	
	public ClientType() {
		super();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getNameWithDescription()
	{
		return this.name.concat(" - ").concat(this.description);
	}
	
	public int getClientsSize()
	{
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

        if (! (obj instanceof ClientType)) 
            return false;
        
        return this.getId() == ((ClientType)obj).getId();
    }
}
