package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumListTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String pattern, final String creationMoment, final String startDate, final String finishDate, final String budget, final String link) {
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron", "Chimpums");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, budget);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void hacking() {
		super.signIn("inventor1", "inventor1");
		
		super.navigate("/patron/chimpum/list");
		super.checkErrorsExist();
		
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
