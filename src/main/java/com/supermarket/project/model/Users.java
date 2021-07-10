package com.supermarket.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "userName"),
		@UniqueConstraint(columnNames = "email")
})
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank
	@Size(max=20)
	private String userName;
	
	@NotBlank
	@Size(max=20)
	@Email
	private String email;
	
	@NotBlank
	@Size(max=120)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
				joinColumns =@JoinColumn(name = "user_id"),
				inverseJoinColumns =@JoinColumn(name =  "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "superMarketId", referencedColumnName = "superMarketId")
	private SuperMarket theSuperMarket;
	
	public Users() {
		
	}

	public Users(String userName,String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public SuperMarket getTheSuperMarket() {
		return theSuperMarket;
	}

	public void setTheSuperMarket(SuperMarket theSuperMarket) {
		this.theSuperMarket = theSuperMarket;
	}
	
	
}
