package acme.features.inventor.chimpum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lustar.Lustar;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorLustarShowService implements AbstractShowService<Inventor, Lustar>{

	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected InventorLustarRepository repository;
	
	// AbstractListService<Patron, Patronage> interface ---------------------------------
	
	@Override
	public boolean authorise(final Request<Lustar> request) {
		assert request != null;
	
		return true;
	}

	@Override
	public Lustar findOne(final Request<Lustar> request) {
		assert request != null;
		
		Lustar lustar;
		int id;
		
		id = request.getModel().getInteger("id");
		lustar = this.repository.findLustarById(id);
		
		return lustar;
	}

	@Override
	public void unbind(final Request<Lustar> request, final Lustar entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		
		request.unbind(entity, model, "subject", "summary", "creationMoment", "startDate", "finishDate", "income", "moreInfo");
		model.setAttribute("id", entity.getId());
		model.setAttribute("code", entity.getCode());
	}

}
