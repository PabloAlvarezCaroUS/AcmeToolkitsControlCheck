package acme.features.patron.artifact;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.artifacts.Artifact;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronArtifactController extends AbstractController<Patron, Artifact>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronArtifactListService listService;
	
	@Autowired
	protected PatronArtifactShowService showService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
	
}
