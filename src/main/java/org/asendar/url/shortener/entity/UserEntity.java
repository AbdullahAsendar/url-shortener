/**
 * 
 */
package org.asendar.url.shortener.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author asendar
 *
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@ToString(of = { "id", "username" })
public class UserEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1228027060807775684L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "email_address", unique = true, nullable = false)
	private String email;

	@Column(name = "user_name", unique = true, nullable = false)
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ShortenedUrlEntity> ShortenedUrls;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserAuthority> authorities;

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	@JsonIgnore
	public Set<UserAuthority> getAuthorities() {
		if(authorities==null)
			return Sets.newHashSet();
		return authorities;
	}

	// Use Roles as external API
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void grantRole(UserRole role) {
		if (authorities == null) {
			authorities = Sets.newHashSet();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

}
