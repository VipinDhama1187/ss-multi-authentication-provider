package com.vipin.multi.authentication.provider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class Authority  {

	/**
	 * The id.
	 */
	@Id
	@Column(name = "AUTHORITY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORITIES_SEQ_GEN")
	@SequenceGenerator(sequenceName = "AUTHORITIES_SEQ", name = "AUTHORITIES_SEQ_GEN")
	private Integer id;

	@Column(name = "AUTHORITY", nullable = false)
	private String AUTHORITY;

	
	public Authority() {
		super();
	}

	public Authority(String aUTHORITY) {
		super();
		AUTHORITY = aUTHORITY;
	}

	public Authority(Integer id, String aUTHORITY) {
		super();
		this.id = id;
		AUTHORITY = aUTHORITY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAUTHORITY() {
		return AUTHORITY;
	}

	public void setAUTHORITY(String aUTHORITY) {
		AUTHORITY = aUTHORITY;
	}

	@Override
	public String toString() {
		return String.format("Authority [id=%s, AUTHORITY=%s]", id, AUTHORITY);
	}

}
