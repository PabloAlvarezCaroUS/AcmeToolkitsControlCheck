package acme.testing.patron.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronChimpumDeleteTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/chimpum/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positive(final int recordIndex, final String title, final String pattern, final String startDate, final String finishDate, final String budget, final String link) {
		super.signIn("patron2", "patron2");
		
		super.clickOnMenu("Patron", "Chimpums");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Patron", "Chimpums");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 2, budget);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		
		super.checkNotErrorsExist();


		super.signOut();
	}
	

	// Ancillary methods ------------------------------------------------------

}
