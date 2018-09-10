package com.qiliu.springcloud.simplesocialmediaapplicationserver.Model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name="username")
	private String username;
    
    @Column(name="password")
	private String password;
	
    @Column(name="passwordconfirm")
	private String passwordConfirm;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), 
          inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@Column(name="enabled")
	private Integer enabled;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
    public User(String username, String password, String passwordConfirm) {
    	this.username = username;
    	this.password = password;
    	this.passwordConfirm = passwordConfirm;
    }

	public String getUserName() {
		return this.username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}
    
	@Transient
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
    
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	

}
