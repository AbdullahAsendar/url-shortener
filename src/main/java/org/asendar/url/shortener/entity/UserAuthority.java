package org.asendar.url.shortener.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author asendar
 *
 */
@Entity
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7570302863304960143L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Id
	private UserEntity user;

	@NotNull
	@Id
	private String authority;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserAuthority))
			return false;

		UserAuthority ua = (UserAuthority) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}

	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}