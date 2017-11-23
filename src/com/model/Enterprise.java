package com.model;

public class Enterprise {
	private String enterprise_id;
	private String enterprise_name;
	private String address;
	private String enterprise_type;
	private String enterprise_property;
	private String enterprise_abbr;
	private String web_site;
	private String customs_code;
	private String org_code;
	private String zip_code;
	private String contact;
	private String telephone;
	private String fax_tel;
	private String tax_code;
	private String busi_license;
	private String legal_repr;
	private String legal_tele;
	private String country;
	private String city;
	private String operate_scope;
	private String email;
	private String remark;
	public Enterprise() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBusi_license() {
		return busi_license;
	}

	public void setBusi_license(String busi_license) {
		this.busi_license = busi_license;
	}

	public String getLegal_repr() {
		return legal_repr;
	}

	public void setLegal_repr(String legal_repr) {
		this.legal_repr = legal_repr;
	}

	public String getLegal_tele() {
		return legal_tele;
	}

	public void setLegal_tele(String legal_tele) {
		this.legal_tele = legal_tele;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOperate_scope() {
		return operate_scope;
	}

	public void setOperate_scope(String operate_scope) {
		this.operate_scope = operate_scope;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	public String getEnterprise_name() {
		return enterprise_name;
	}
	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEnterprise_type() {
		return enterprise_type;
	}
	public void setEnterprise_type(String enterprise_type) {
		this.enterprise_type = enterprise_type;
	}
	public String getEnterprise_property() {
		return enterprise_property;
	}
	public void setEnterprise_property(String enterprise_property) {
		this.enterprise_property = enterprise_property;
	}
	public String getEnterprise_abbr() {
		return enterprise_abbr;
	}
	public void setEnterprise_abbr(String enterprise_abbr) {
		this.enterprise_abbr = enterprise_abbr;
	}
	public String getWeb_site() {
		return web_site;
	}
	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
	public String getCustoms_code() {
		return customs_code;
	}
	public void setCustoms_code(String customs_code) {
		this.customs_code = customs_code;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax_tel() {
		return fax_tel;
	}
	public void setFax_tel(String fax_tel) {
		this.fax_tel = fax_tel;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	@Override
	public String toString() {
		return "Enterprise [enterprise_id=" + enterprise_id + ", enterprise_name=" + enterprise_name + ", address="
				+ address + ", enterprise_type=" + enterprise_type + ", enterprise_property=" + enterprise_property
				+ ", enterprise_abbr=" + enterprise_abbr + ", web_site=" + web_site + ", customs_code=" + customs_code
				+ ", org_code=" + org_code + ", zip_code=" + zip_code + ", contact=" + contact + ", telephone="
				+ telephone + ", fax_tel=" + fax_tel + ", tax_code=" + tax_code + ", busi_license=" + busi_license
				+ ", legal_repr=" + legal_repr + ", legal_tele=" + legal_tele + ", country=" + country + ", city="
				+ city + ", operate_scope=" + operate_scope + ", email=" + email + ", remark=" + remark + "]";
	}

}
