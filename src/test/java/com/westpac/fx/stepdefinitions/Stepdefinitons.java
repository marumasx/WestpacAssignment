package com.westpac.fx.stepdefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.westpac.fx.pages.Fx_Travel_MigrantPage;
import com.westpac.fx.utils.Utils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefinitons extends Utils {
	public Stepdefinitons() throws IOException {
		super();
	}

	public static WebDriver driver;
	public static Fx_Travel_MigrantPage fxtravelmigrantpage;

	// *************************************************************************************************************
	// @Author: sravan.marumaula@infosys.com
	// @Param: Passing user Name and Browser to the method
	// @Description: This method is created to capture screen shot with Img
	// extension
	// in target folder in case of failure scenarios and
	// kill the browser instance after each method
	// *************************************************************************************************************
	@After
	public void teardown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}

		kill();
	}

	// *************************************************************************************************************
	// @Author: sravan.marumaula@infosys.com
	// @Param: Passing user Name and Browser to the method
	// @Description: This method is created to open the browser and hits the URL
	// of the
	// application
	// *************************************************************************************************************
	@Given("^user opens the (.+) in (.+)$")
	public void user_opens_the_in(String Url, String Browser) throws Throwable {
		driver = Utils.initializeBrowser(Url, Browser);
		fxtravelmigrantpage = PageFactory.initElements(driver, Fx_Travel_MigrantPage.class);
	}

	@When("^the user Navigates to Currency converter$")
	public void the_user_navigates_to_currency_converter() throws Throwable {
		Utils.mouseover(fxtravelmigrantpage.fxTravelMigrantLink);
		try {
			if (Utils.iselementdisplayed(fxtravelmigrantpage.currencyConverterLink) == true) {
				fxtravelmigrantpage.currencyConverterLink.click();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Then("^the user should see an error message$")
	public void the_user_should_see_an_error_message() throws Throwable {
		String expectedErrorMessage = "Please enter the amount you want to convert.";
		try {
			Utils.impicitwait(5);
			Utils.iselementdisplayed(Fx_Travel_MigrantPage.errordivPane);
			String actualErrorMessage = Fx_Travel_MigrantPage.errordivPane.getText();
			Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@And("^the user clicks on Convert button with out entering any amount$")
	public void the_user_clicks_on_convert_button_with_out_entering_any_amount() throws Throwable {
		Utils.impicitwait(5);
		driver.switchTo().frame("westpac-iframe");
		try {
			if (Utils.iselementdisplayed(fxtravelmigrantpage.convertButton) == true) {
				fxtravelmigrantpage.convertButton.click();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException(e.getMessage());
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@And("^the user selects required curreny from first (.+) dropdown$")
	public void the_user_selects_required_curreny_from_first_dropdown(String convertfrom) throws Throwable {
		Utils.impicitwait(5);
		driver.switchTo().frame("westpac-iframe");
		Utils.selectFromDropdown(fxtravelmigrantpage.convertFromDPD, null, null, convertfrom);
	}

	@And("^the user enters required amount in (.+) field$")
	public void the_user_enters_required_amount_in_field(String enteramount) throws Throwable {
		try {
			Fx_Travel_MigrantPage.enterAmountTextBox.sendKeys(enteramount);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Then("^the user should see the converted value in reslut pane (.+) Convert from (.+) Convert to (.+)$")
	public void the_user_should_see_the_converted_value_in_reslut_pane_convert_from_convert_to(String enteramount,
			String convertfrom, String convertto) throws Throwable {
		Utils.impicitwait(10);
		String actualtext = Fx_Travel_MigrantPage.resultPane.getText();
		String requiredText = actualtext.substring(0, actualtext.indexOf("\n"));
		String startsWith = enteramount + " " + convertfrom;
		String endsWith = convertto;
		if (convertfrom.equalsIgnoreCase("United States Dollar") || convertfrom.equalsIgnoreCase("Pound Sterling")) {
			String[] lines;
			lines = actualtext.split("\n");
			for (int i = 0; i < lines.length - 1; i++) {
				for (int j = i + 1; j < lines.length - 2;) {
					requiredText = lines[0] + lines[1].substring(0, lines[1].indexOf("@"));
					requiredText = requiredText.trim();
					if (requiredText.startsWith(startsWith) && requiredText.endsWith(endsWith)) {
						Assert.assertTrue(true);
					} else {
						Assert.assertTrue(false);
					}
					break;
				}
			}
		} else {
			if (requiredText.startsWith(startsWith) && requiredText.endsWith(endsWith)) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@And("^user selects required curreny from second (.+) dropdown$")
	public void user_selects_required_curreny_from_second_dropdown(String convertto) throws Throwable {
		Utils.selectFromDropdown(fxtravelmigrantpage.convertToDPD, null, null, convertto);
	}

	@And("^the user clicks on convert button$")
	public void the_user_clicks_on_convert_button() throws Throwable {
		Utils.impicitwait(5);
		fxtravelmigrantpage.convertButton.click();
	}
}
