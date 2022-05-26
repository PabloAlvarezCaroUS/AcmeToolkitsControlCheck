package acme.features.patron.artifact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifacts.Artifact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronArtifactRepository extends AbstractRepository{

	@Query("SELECT a FROM Artifact a WHERE a.chimpum.id = :id")
	Collection<Artifact> findArtifactsByChimpumId(int id);
	
	@Query("select a from Artifact a where a.id=:id")
	Artifact findArtifactById(int id);
	

}
