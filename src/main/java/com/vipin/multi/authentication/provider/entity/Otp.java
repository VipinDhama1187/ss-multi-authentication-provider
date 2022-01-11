package com.vipin.multi.authentication.provider.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OTP")
public class Otp {

	@Id
	@Column(name = "OTP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OTP_SEQ_GEN")
	@SequenceGenerator(sequenceName = "OTP_SEQ", name = "OTP_SEQ_GEN")
	private Integer id;

	@Column(name = "otp", nullable = false)
	private Integer otp;

	@OneToOne
	@JoinColumn(name ="user_id")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Otp [id=%s, otp=%s]", id, otp);
	}

}
