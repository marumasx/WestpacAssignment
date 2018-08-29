package com.westpac.fx.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Fx_Travel_MigrantPage {
	@FindBy(id = "ubermenu-section-link-fx-travel-and-migrant-ps")
	public WebElement fxTravelMigrantLink;

	@FindBy(id = "ubermenu-item-cta-currency-converter-ps")
	public WebElement currencyConverterLink;

	@FindBy(id = "convert")
	public WebElement convertButton;

	@FindBy(id = "ConvertFrom")
	public WebElement convertFromDPD;

	@FindBy(id = "ConvertTo")
	public WebElement convertToDPD;

	@FindBy(id = "errordiv")
	public static WebElement errordivPane;

	@FindBy(id = "Amount")
	public static WebElement enterAmountTextBox;

	@FindBy(xpath = "//*[@id='resultsdiv']/em")
	public static WebElement resultPane;

}
