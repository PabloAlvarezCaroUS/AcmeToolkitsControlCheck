package acme.features.patron.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronChimpumListService implements AbstractListService<Patron, Chimpum>{
	
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
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		assert request != null;
		
		Collection<Chimpum> result;
		result = this.repository.findAllChimpums();
		
		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "creationMoment", "budget");
	}

}
