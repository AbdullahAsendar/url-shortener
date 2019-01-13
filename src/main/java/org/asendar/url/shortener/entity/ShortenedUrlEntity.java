/**
 * 
 */
package org.asendar.url.shortener.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;

/**
 * @author asendar
 *
 */
@Getter
@Setter
@Entity
@Table(name = "shortened_url")
public class ShortenedUrlEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "original")
	private String original;

	@Column(name = "shortened")
	private String shortened;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<UrlAccessEntity> access;

	public void addAccess(UrlAccessEntity urlAccess) {
		if (access == null)
			access = Lists.newArrayList();

		access.add(urlAccess);
	}

}
