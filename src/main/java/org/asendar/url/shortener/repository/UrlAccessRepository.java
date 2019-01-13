/**
 * 
 */
package org.asendar.url.shortener.repository;

import org.asendar.url.shortener.entity.UrlAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asendar
 *
 */
@Repository
public interface UrlAccessRepository extends JpaRepository<UrlAccessEntity, Long>{

}
