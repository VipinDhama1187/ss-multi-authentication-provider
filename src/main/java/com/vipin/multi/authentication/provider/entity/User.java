package com.vipin.multi.authentication.provider.entity;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
public class User {


	/** The id. */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GEN")
	@SequenceGenerator(sequenceName = "USERS_SEQ", name = "USERS_SEQ_GEN")
	private Integer id;

	/** The name. */
	@Size(min = 2, message = "name should be min of 2 characters")
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"))
	private Set<Authority> authority = new HashSet<>();

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	public User(Integer id, @Size(min = 2, message = "name should be min of 2 characters") String username,
			String password, boolean enabled, Set<Authority> authority) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}

	
	
	
	
	/**
	 *
	 */
	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, enabled=%s, authority=%s]", id, username, password,
				enabled, authority);
	}

}
