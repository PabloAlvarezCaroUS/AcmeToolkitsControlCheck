package acme.testing.inventor.lustar;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorLustarDeleteTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/lustar/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positive(final int recordIndex, final String subject, final String pattern, final String startDate, final String finishDate, final String income, final String moreInfo) {
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor", "Lustars");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("subject", subject);
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("income", income);
		super.fillInputBoxIn("link", moreInfo);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "Lustars");
		super.checkListingExists();
		super.sortListing(1, "desc");
		super.checkColumnHasValue(recordIndex, 0, subject);
		super.checkColumnHasValue(recordIndex, 2, income);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		
		super.checkNotErrorsExist();


		super.signOut();
	}
	

	// Ancillary methods ------------------------------------------------------

}
