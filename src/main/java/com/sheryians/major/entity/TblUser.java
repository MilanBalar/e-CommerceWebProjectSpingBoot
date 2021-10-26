package com.sheryians.major.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_user",schema = "appuser")
public class TblUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(nullable = false)
	private String fisrtName;

	private String lastName;

	@NotEmpty
	@Column(nullable = false,unique = true)
	@Email(message = "errors.invalid_email")
	private String email;

	private String password;

	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
			inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
		)
	private List<TblRoles> tblRoles;



	public TblUser(TblUser user) {
		super();
		this.id = user.getUserId();
		this.fisrtName = user.getFisrtName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.tblRoles = user.getTblRoles();
	}
	public TblUser() {
		super();
	}

	public long getUserId() {
		return id;
	}

	public void setUserId(long id) {
		this.id = id;
	}

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TblRoles> getTblRoles() {
		return tblRoles;
	}

	public void setTblRoles(List<TblRoles> tblRoles) {
		this.tblRoles = tblRoles;
	}


}
