/**
 * 
 */
package org.asendar.url.shortener.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class UserEntity {

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

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ShortenedUrlEntity> ShortenedUrls;

}
