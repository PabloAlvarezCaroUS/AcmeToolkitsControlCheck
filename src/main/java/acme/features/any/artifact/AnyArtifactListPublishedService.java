package acme.features.any.artifact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtifactListPublishedService implements AbstractListService<Any, Artifact> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyArtifactRepository repository;

	// AbstractListService<Any, Artifact> interface --------------

	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Artifact> findMany(final Request<Artifact> request) {
		assert request != null;
		
		Collection<Artifact> result;
		
		if (request.getModel().hasAttribute("lustarId")) {
			Integer masterId;
			masterId = request.getModel().getInteger("lustarId");
			result = this.repository.findArtifactsByLustarId(masterId);
			return result;
		} else {
			final String type = request.getModel().getString("type");
			if(type.equals("component")) {
				result = this.repository.findAllComponentsPublished();
				return result;
			}else {
				result = this.repository.findAllToolsPublished();
				return result;
			}
		}
		
	}
	
	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "retailPrice", "artifactType");

	}
}
