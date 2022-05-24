package acme.features.patron.chimpum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronChimpumShowService implements AbstractShowService<Patron, Chimpum>{

	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected PatronChimpumRepository repository;
	
	// AbstractListService<Patron, Patronage> interface ---------------------------------
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
	
		return true;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum chimpum;
		int id;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(id);
		
		return chimpum;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		
		request.unbind(entity, model, "title", "creationMoment", "startDate", "finishDate", "budget", "link");
		model.setAttribute("id", entity.getId());
		model.setAttribute("code", entity.getCode());
	}

}
