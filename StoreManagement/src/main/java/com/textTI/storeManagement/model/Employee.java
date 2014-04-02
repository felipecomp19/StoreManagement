package com.textTI.storeManagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="tb_employees")
public class Employee extends BaseModel {
	
	private static final long serialVersionUID = -6933096177712981167L;

	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store", nullable = false)
	@JsonManagedReference
	private Store store;
	
	@OneToMany(mappedBy="employee")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
	private List<Indicator> indicators;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
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
        
        return this.getId() == ((Employee)obj).getId();
    }
}
