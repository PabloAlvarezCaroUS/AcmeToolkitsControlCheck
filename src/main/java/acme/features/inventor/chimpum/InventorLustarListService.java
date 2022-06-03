package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lustar.Lustar;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorLustarListService implements AbstractListService<Inventor, Lustar>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected InventorLustarRepository repository;
	
	// AbstractListService<Inventor, Patronage> interface ---------------------------------
	
	@Override
	public boolean authorise(final Request<Lustar> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Lustar> findMany(final Request<Lustar> request) {
		assert request != null;
		
		Collection<Lustar> result;
		result = this.repository.findAllLustars();
		
		return result;
	}

	@Override
	public void unbind(final Request<Lustar> request, final Lustar entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "subject", "creationMoment", "income");
	}

}
