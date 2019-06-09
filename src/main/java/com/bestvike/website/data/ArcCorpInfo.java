package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Arc_CorpInfo")
public class ArcCorpInfo implements Serializable {

	@Id
	private String corpno;
	private String corpname;
	private String registeraddress;
	private String contactname;
	private String phoneno;
	private String licenseno;
	private String legalname;
	private String legalcertno;

	public String getCorpno() {
		return corpno;
	}

	public void setCorpno(String corpno) {
		this.corpno = corpno;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getRegisteraddress() {
		return registeraddress;
	}

	public void setRegisteraddress(String registeraddress) {
		this.registeraddress = registeraddress;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getLegalname() {
		return legalname;
	}

	public void setLegalname(String legalname) {
		this.legalname = legalname;
	}

	public String getLegalcertno() {
		return legalcertno;
	}

	public void setLegalcertno(String legalcertno) {
		this.legalcertno = legalcertno;
	}
}
