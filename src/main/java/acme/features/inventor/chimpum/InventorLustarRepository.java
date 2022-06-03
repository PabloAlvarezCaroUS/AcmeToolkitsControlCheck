package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artifacts.Artifact;
import acme.entities.lustar.Lustar;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorLustarRepository extends AbstractRepository {
	
	@Query("SELECT l FROM Lustar l")
	Collection<Lustar> findAllLustars();
	
	@Query("SELECT l FROM Lustar l WHERE l.id = :id")
	Lustar findLustarById(int id);
	
	@Query("SELECT l FROM Lustar l WHERE l.pattern = :pattern")
	Lustar findLustarByPattern(String pattern);
	
	@Query("select cd.acceptedCurrencies from ConfigData cd")
	String acceptedCurrencies();
	
	@Query("SELECT artifact FROM Artifact artifact WHERE artifact.lustar.id = :id")
	List<Artifact> findArtifactsByLustarId(int id);
	
	@Query("select config.strongSpamTerms from ConfigData config")
	String findStrongSpamTerms();
	
	@Query("select config.weakSpamTerms from ConfigData config")
	String findWeakSpamTerms();
	
	@Query("select config.strongSpamTreshold from ConfigData config")
	int findStrongSpamTreshold();
	
	@Query("select config.weakSpamTreshold from ConfigData config")
	int findWeakSpamTreshold();
	
}
