/**
 * 
 */
package org.asendar.url.shortener.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author asendar
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("org.asendar.url.shortener")
public class Launcher {

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

}
