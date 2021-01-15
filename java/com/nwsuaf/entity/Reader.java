package com.nwsuaf.entity;

public class Reader {
	String cert_id;
	String name;
	String sex;
	String birthday;
	String dept;
	String occupation;
	String password;
	String usr_state;
	String kind;
	String kind_lw;

	@Override
	public String toString() {
		return "Reader{" +
				"cert_id='" + cert_id + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", birthday='" + birthday + '\'' +
				", dept='" + dept + '\'' +
				", occupation='" + occupation + '\'' +
				", password='" + password + '\'' +
				", usr_state='" + usr_state + '\'' +
				", kind='" + kind + '\'' +
				", kind_lw='" + kind_lw + '\'' +
				'}';
	}

	public String getKind_lw() {
		return kind_lw;
	}

	public void setKind_lw(String kind_lw) {
		this.kind_lw = kind_lw;
	}

	public String getCert_id() {
		return cert_id;
	}
	public void setCert_id(String cert_id) {
		this.cert_id = cert_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsr_state() {
		return usr_state;
	}
	public void setUsr_state(String usr_state) {
		this.usr_state = usr_state;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

}
