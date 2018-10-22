package org.tyaa.spring.rest.hibernate.payment.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = -793443469880404168L;
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String password;
	@ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String description) {
		this.password = description;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
