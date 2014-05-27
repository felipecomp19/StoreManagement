package com.textTI.storeManagement.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.text.MaskFormatter;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = "tb_client")
public class Client extends BaseModel {

	private static final long serialVersionUID = 4340932225688335396L;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "cpf", length = 15, unique = false, nullable = false)
	private String cpf;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "cellPhone")
	private String cellPhone;
		
	@Column(name = "createdOn")
	private Date createdOn;
	
	@Column(name="month_birthday")
	private int month_birthday;
	
	@Column(name="day_birthday")
	private int day_birthday;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientType", nullable = false)
	@JsonManagedReference
	private ClientType clientType;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_client_store", 
				joinColumns = { @JoinColumn(name = "client_id", nullable = false, updatable = false) }, 
				inverseJoinColumns = { @JoinColumn(name = "store_id", nullable = false, updatable = false) })
	@JsonManagedReference
	private Set<Store> stores;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "address")
	private Address address;
	
//	@Column(name = "especialActions")
//	private String especialActions;
	
	@Transient
	private int count;
	
	@Transient
	private boolean checked;

	public Client() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
	
	public int getMonth_birthday() {
		return month_birthday;
	}

	public void setMonth_birthday(int month_birthday) {
		this.month_birthday = month_birthday;
	}

	public int getDay_birthday() {
		return day_birthday;
	}

	public void setDay_birthday(int day_birthday) {
		this.day_birthday = day_birthday;
	}
	
	public String getFormatedBirthday()
	{
		return this.day_birthday + "/" + this.getMonth_birthday();
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
//	public String getEspecialActions() {
//		return especialActions;
//	}
//
//	public void setEspecialActions(String especialActions) {
//		this.especialActions = especialActions;
//	}

	public String getFormatedCPF()
	{
		try {
			return formatarString(this.cpf, "###.###.###-##");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.cpf;
	} 
	
	public String formatarString(String texto, String mascara) throws ParseException {  
	    MaskFormatter mf = new MaskFormatter(mascara);  
	    mf.setValueContainsLiteralCharacters(false);  
	    return mf.valueToString(texto);  
	} 
	
	public String getStoresNames()
	{
		String result = "";
		int count = 1;
		for (Store store : this.stores) {
			if(count == this.stores.size())
				result+= store.getNameWithDesc();
			else
				result+= store.getNameWithDesc() + ", ";
			count++;
		}
		return result;
	}
}
