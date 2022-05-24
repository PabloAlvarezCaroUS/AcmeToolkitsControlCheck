package acme.features.patron.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronChimpumRepository extends AbstractRepository {
	
	@Query("select chimpum from Chimpum chimpum")
	Collection<Chimpum> findAllChimpums();
	
	
}
