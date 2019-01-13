/**
 * 
 */
package org.asendar.url.shortener.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author asendar
 *
 */
@Configuration
@EntityScan("org.asendar.url.shortener.entity")
@EnableJpaRepositories("org.asendar.url.shortener.repository")
public class Config {

}
