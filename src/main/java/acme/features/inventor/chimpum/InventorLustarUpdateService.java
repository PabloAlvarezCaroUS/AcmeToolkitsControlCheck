package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.lustar.Lustar;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import features.SpamDetector;

@Service
public class InventorLustarUpdateService implements AbstractUpdateService<Inventor, Lustar> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorLustarRepository repository;
		
	// AbstractUpdateService<Inventor, Chimpum> interface ---------------------
	
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
		
		request.bind(entity, errors, "subject", "summary", "startDate", "finishDate", "income", "moreInfo");
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
		
		SpamDetector spamDetector;
		String strongSpamTerms;
		String weakSpamTerms;
		int strongSpamThreshold;
		int weakSpamThreshold;
		
		spamDetector = new SpamDetector();
		strongSpamTerms = this.repository.findStrongSpamTerms();
		weakSpamTerms = this.repository.findWeakSpamTerms();
		strongSpamThreshold = this.repository.findStrongSpamTreshold();
		weakSpamThreshold = this.repository.findWeakSpamTreshold();
		
		if(!errors.hasErrors("subject")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getSubject())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getSubject()),
				"subject", "inventor.chimpum.form.error.spam");
		}
		
		if(!errors.hasErrors("summary")) {
			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getSummary())
				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getSummary()),
				"summary", "inventor.chimpum.form.error.spam");
		}
		
		if(!errors.hasErrors("startDate")) {
			Calendar calendar;
			
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			
			errors.state(request, entity.getStartDate().after(calendar.getTime()), "startDate", "inventor.chimpum.form.error.startDate");
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
			
			errors.state(request, errorState, "finishDate", "inventor.chimpum.form.error.finishDate");
		}
		
		if (!errors.hasErrors("income")) {
			final String currency = entity.getIncome().getCurrency();
			final String currencyAvaliable = this.repository.acceptedCurrencies();
			boolean acceptedCurrency = false;
			
			for(final String cur: currencyAvaliable.split(",")) {
				acceptedCurrency = cur.trim().equalsIgnoreCase(currency);
				if(acceptedCurrency)
					break;
			}
			
			errors.state(request, entity.getIncome().getAmount() > 0 , "income", "inventor.chimpum.form.error.negative-budget");
			errors.state(request,acceptedCurrency, "income", "inventor.chimpum.form.error.nonexistent-currency");
		}
	}

	@Override
	public void update(final Request<Lustar> request, final Lustar entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
