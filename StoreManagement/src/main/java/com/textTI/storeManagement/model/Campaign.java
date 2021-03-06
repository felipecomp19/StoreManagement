package com.textTI.storeManagement.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="tb_campaign")
public class Campaign extends BaseModel {

	private static final long serialVersionUID = 5569652223591261494L;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="createdOns")
	private Date createdOn;
	
	@Transient
	private String emailContent;
	
	@Column(name="emailFileName")
	private String emailFileName;
	
	@ManyToOne(optional=false, fetch= FetchType.EAGER)
	@JsonManagedReference
	private MailingList mailingList;
	
	@Column(name = "submitted")
	private boolean submitted;
	
	@Column(name = "submittedDate")
	private Date submittedDate;
	
	@ManyToOne(optional=false, fetch= FetchType.EAGER)
	@JsonManagedReference
	private Status status;
	
	private String subject;
	
	@Transient
	private Date dateScheduled;
	
	public Campaign() {
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

	public MailingList getMailingList() {
		return mailingList;
	}

	public void setMailingList(MailingList mailingList) {
		this.mailingList = mailingList;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getEmailFileName() {
		return emailFileName;
	}

	public void setEmailFileName(String emailFileName) {
		this.emailFileName = emailFileName;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getFormatedSubmittedDate() {
		if(this.submittedDate != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			return sdf.format(submittedDate);
		}
		return "";
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}

