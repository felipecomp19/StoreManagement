package com.textTI.storeManagement.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="tb_store")
public class Store extends BaseModel {

	private static final long serialVersionUID = 2240699832588471227L;
	
	@Column(name="name", nullable=false)	
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "telephone", length=30)
	private String telephone;

	@ManyToMany(mappedBy = "stores", fetch=FetchType.EAGER)
	@JsonBackReference
	private Set<Client> clients;
	
	@OneToMany(mappedBy="store")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
	private List<Employee> employees;
	
	public Store() {
		super();
	}
	
	public String getIdAsString()
	{
		return new Long(super.getId()).toString();
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
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	public int getClientsSize()
	{
		return this.clients.size();
	}
	
	public String getNameWithDesc()
	{
		return this.name + " - " + this.description;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
    public int hashCode() {
		if(this.getId() != null)
			return this.getId().hashCode();
		return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) 
            return false;

        if (! (obj instanceof Store)) 
            return false;
        
        return this.getId() == ((Store)obj).getId();
    }
}
