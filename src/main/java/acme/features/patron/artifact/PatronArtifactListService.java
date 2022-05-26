package acme.features.patron.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronArtifactListService implements AbstractListService<Patron, Artifact>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronArtifactRepository repository;

	// AbstractListService<Patron, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		
		int masterId;
		masterId = request.getModel().getInteger("chimpumId");
		
		return this.repository.findArtifactsByChimpumId(masterId);
		
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "retailPrice", "artifactType");
		
	}

}
