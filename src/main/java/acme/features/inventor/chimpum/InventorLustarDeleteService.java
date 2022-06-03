package acme.features.inventor.chimpum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artifacts.Artifact;
import acme.entities.lustar.Lustar;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorLustarDeleteService implements AbstractDeleteService<Inventor, Lustar> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorLustarRepository repository;
		
	// AbstractDeleteService<Inventor, Lustar> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Lustar> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Lustar> request, final Lustar entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "subject", "summary", "startDate", "finishDate", "income", "moreInfo", "code", "creationMoment");
	}

	@Override
	public void unbind(final Request<Lustar> request, final Lustar entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "subject", "summary", "startDate", "finishDate", "income", "moreInfo", "code", "creationMoment");
	}

	@Override
	public Lustar findOne(final Request<Lustar> request) {
		assert request != null;
		
		Lustar result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findLustarById(id);
		
		return result;
	}

	@Override
	public void validate(final Request<Lustar> request, final Lustar entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Lustar> request, final Lustar entity) {
		assert request != null;
		assert entity != null;
		
		int id;
		id = entity.getId();
		List<Artifact> lustarArtifacts = this.repository.findArtifactsByLustarId(id);
		for (Artifact lustarArtifact : lustarArtifacts) {
			lustarArtifact.setLustar(null);
		}
		
		this.repository.delete(entity);
	}

}
