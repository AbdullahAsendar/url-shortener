/**
 * 
 */
package org.asendar.url.shortener.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author asendar
 *
 */
@Configuration
@EntityScan("org.asendar.url.shortener.entity")
@EnableJpaRepositories("org.asendar.url.shortener.repository")
public class Config implements WebMvcConfigurer {

	@Controller
	static class Routes {
		@ApiIgnore
		@RequestMapping(value = "/")
		public String swagger() {
			return "redirect:/swagger-ui.html";
		}
	}
}
