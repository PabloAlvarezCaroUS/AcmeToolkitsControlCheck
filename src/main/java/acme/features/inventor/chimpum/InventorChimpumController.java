package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chimpum.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected InventorChimpumListService listService;
	
	@Autowired
	protected InventorChimpumShowService showService;
	
	@Autowired
	protected InventorChimpumCreateService createService;
	
	@Autowired
	protected InventorChimpumUpdateService updateService;
	
	@Autowired
	protected InventorChimpumDeleteService deleteService;
	
	// Constructors ---------------------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("show", this.showService);
	}
}
