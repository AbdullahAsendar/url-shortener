/**
 * 
 */
package org.asendar.url.shortener.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author asendar
 *
 */
@Getter
@Setter
@Entity
@Table(name = "url_access")
public class UrlAccessEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "ip_address")
	private String ipAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shortened_url_id", referencedColumnName = "id")
	private ShortenedUrlEntity shortenedUrl;
}
