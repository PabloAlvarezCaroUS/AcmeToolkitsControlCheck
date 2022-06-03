package acme.features.inventor.artifact;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.entities.artifacts.ArtifactType;
import acme.entities.lustar.Lustar;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtifactShowService implements AbstractShowService<Inventor, Artifact>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtifactRepository repository;

	// AbstractListService<Inventor, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Artifact> request) {
		assert request != null;
		
		Integer id;
		Artifact artifact;
		boolean result;
		id = request.getModel().getInteger("id");
		artifact = this.repository.findArtifactById(id);
		
		result = request.getPrincipal().getActiveRoleId() == artifact.getInventor().getId();
		
		
		return result;
	}

	@Override
	public Artifact findOne(final Request<Artifact> request) {
		assert request != null;
	
		Integer id;
		Artifact artifact;
		id = request.getModel().getInteger("id");
		artifact = this.repository.findArtifactById(id);
		
		return artifact;
	}

	@Override
	public void unbind(final Request<Artifact> request, final Artifact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		List<String> types;
		String lustarCode;
		String lustarSubject;
		boolean hasLustar;
		List<Lustar> lustars;
		
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "artifactType", "link");
		
		types = new ArrayList<String>();
		for(final ArtifactType type:ArtifactType.values()) {
			types.add(type.toString());
		}
		
		hasLustar = (entity.getLustar() != null);
		lustars = this.repository.findAllLustars();
		
		model.setAttribute("lustars", lustars);
		model.setAttribute("hasLustar", hasLustar);
		
		if (hasLustar) {
			lustarCode = entity.getLustar().getCode();
			lustarSubject = entity.getLustar().getSubject();
			model.setAttribute("lustar.code", lustarCode);
			model.setAttribute("lustar.subject", lustarSubject);
			model.setAttribute("lustar", entity.getLustar());
		}
		
		model.setAttribute("types", types);
		model.setAttribute("published", entity.isPublished());
		model.setAttribute("isComponent", entity.getArtifactType().equals(ArtifactType.COMPONENT));


	
	}
	
}
