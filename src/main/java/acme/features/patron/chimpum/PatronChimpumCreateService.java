package acme.features.patron.chimpum;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chimpum.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronChimpumCreateService implements AbstractCreateService<Patron, Chimpum> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronChimpumRepository repository;
		
	// AbstractCreateService<Patron, Patronage> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "pattern", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "pattern", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;
		
		Chimpum result;
		Date creationMoment;
		
		result = new Chimpum();
		creationMoment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(creationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getStartDate().after(calendar.getTime()), "startDate", "patron.chimpum.form.error.startDate");
		}
		
		if(!errors.hasErrors("finishDate")) {
			Calendar calendar;
			
			boolean errorState = true;
			
			if (entity.getStartDate() != null) {		
				calendar = new GregorianCalendar();
				calendar.setTime(entity.getStartDate());
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				errorState = entity.getFinishDate().after(calendar.getTime());
			}
			
			errors.state(request, errorState, "finishDate", "patron.chimpum.form.error.finishDate");
		}
		
		if (!errors.hasErrors("budget")) {
			final String currency = entity.getBudget().getCurrency();
			final String currencyAvaliable = this.repository.acceptedCurrencies();
			boolean acceptedCurrency = false;
			
			for(final String cur: currencyAvaliable.split(",")) {
				acceptedCurrency = cur.trim().equalsIgnoreCase(currency);
				if(acceptedCurrency)
					break;
			}
			errors.state(request, entity.getBudget().getAmount() > 0 , "budget", "patron.chimpum.form.error.negative-budget");
			errors.state(request,acceptedCurrency, "budget", "patron.chimpum.form.error.nonexistent-currency");
		}
	}

	@Override
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
