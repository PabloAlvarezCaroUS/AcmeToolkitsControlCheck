package acme.features.patron.artifact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronArtifactShowService implements AbstractShowService<Patron, Artifact>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronArtifactRepository repository;

	// AbstractListService<Patron, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		Artifact result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findArtifactById(id);
		
		return result.isPublished();
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;

		Artifact result;
		int id;
		
		id=request.getModel().getInteger("id");
		result = this.repository.findArtifactById(id);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "artifactType", "link");
	}
	
}
