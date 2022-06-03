package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.lustar.Lustar;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorLustarController extends AbstractController<Inventor, Lustar>{
	
	// Internal state -------------------------------------------------------------------
	
	@Autowired
	protected InventorLustarListService listService;
	
	@Autowired
	protected InventorLustarShowService showService;
	
	@Autowired
	protected InventorLustarCreateService createService;
	
	@Autowired
	protected InventorLustarUpdateService updateService;
	
	@Autowired
	protected InventorLustarDeleteService deleteService;
	
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
