package com.zorem.ct.webapp.pages;

import com.zorem.ct.webbase.enums.BrowserSize;
import com.zorem.ct.webbase.exceptions.BaseActionException;
import com.zorem.ct.webbase.exceptions.factories.BaseElements;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class HomePage extends BaseElements {

    @FindAll({
            @FindBy(xpath = "//input[@placeholder='Username']"),
            @FindBy(xpath = "//input[@name='username']")
    })
    @Getter
    WebElement txtUsername;

    @FindAll({
            @FindBy(xpath = "//input[@placeholder='Password']"),
            @FindBy(xpath = "//input[@name='password']")
    })
    @Getter
    WebElement txtPassword;

    @FindAll({
            @FindBy(xpath = "//input[@value='Login']"),
            @FindBy(xpath = "//input[@name='login-button']")
    })
    @Getter
    WebElement btnLogin;

    @FindAll({
            @FindBy(xpath = "//button[text()='Add to cart']"),
            @FindBy(xpath = "//button[contains(@name,'add-to-cart')]")
    })
    @Getter
    WebElement btnAddProducts;

    @FindAll({
            @FindBy(xpath = "//a[@class='shopping_cart_link']"),
            @FindBy(xpath = "//div[@id='shopping_cart_container']//a")
    })
    @Getter
    WebElement aShopCart;

    @FindAll({
            @FindBy(xpath = "//button[@name='checkout']"),
            @FindBy(xpath = "//button[text()='Checkout']")
    })
    @Getter
    WebElement btnCheckout;

    @FindAll({
            @FindBy(xpath = "//input[@name='continue']"),
            @FindBy(xpath = "//button[@value='Continue']")
    })
    @Getter
    WebElement btnContinue;

    @FindAll({
            @FindBy(xpath = "//button[@name='finish']"),
            @FindBy(xpath = "//button[text()='Finish']")
    })
    @Getter
    WebElement btnFinish;

    @FindAll({
            @FindBy(xpath = "//input[@name='firstName']"),
            @FindBy(xpath = "//input[@placeholder='First Name']")
    })
    @Getter
    WebElement txtFirstname;

    @FindAll({
            @FindBy(xpath = "//input[@name='lastName']"),
            @FindBy(xpath = "//input[@placeholder='Last Name']")
    })
    @Getter
    WebElement txtLastname;

    @FindAll({
            @FindBy(xpath = "//input[@name='postalCode']"),
            @FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
    })
    @Getter
    WebElement txtPostalcode;

    @FindAll({
            @FindBy(xpath = "//h2[@class='complete-header']"),
            @FindBy(xpath = "//div[@id='checkout_complete_container']//h2")
    })
    @Getter
    WebElement Labelconfirmacion;

    public void setSite(String site){
        setBrowserSizeTo(BrowserSize.max);
        try {
            if (!webDriver.getCurrentUrl().contains(site)) {
                webDriver.get(site);
            }
        } catch (WebDriverException | BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void ingresarCredencialesPara(String user, String pass){
        try {
            waitForElementToBePresent(getTxtUsername());
            waitForElementToBePresent(getTxtPassword());
            enter(getTxtUsername(), user);
            enter(getTxtPassword(), pass);
            click(getBtnLogin());

        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void agregar_productos(int cant){
        try {
            Random random = new Random();
            waitForElementToBePresent(getBtnAddProducts());
            List<WebElement> productos = webDriver.findElements(By.xpath("//button[text()='Add to cart']"));
            if (productos.size() >= cant) {
                for (int i = 1; i <= cant; i++) {
                    int numeroAleatorio = random.nextInt(productos.size()) + 1;
                    WebElement elementToClick = productos.get(numeroAleatorio);
                    clickJS(elementToClick);
                }
            } else {
                Assert.fail("No se obtuvieron los productos");
            }

        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void contenido_carrito(){
        try {
        waitForElementToBePresent(getAShopCart());
        clickJS(getAShopCart());
        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void click_continue(){
        try {
            waitForElementToBePresent(getBtnContinue());
            clickJS(getBtnContinue());
        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void click_finish(){
        try {
            waitForElementToBePresent(getBtnFinish());
            clickJS(getBtnFinish());
        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void click_checkout(){
        try {
            waitForElementToBePresent(getBtnCheckout());
            clickJS(getBtnCheckout());
        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void confirmacion(String title){
        try {
            waitForElementToBePresent(getLabelconfirmacion());
            Assert.assertEquals(getLabelconfirmacion().getAttribute("innerHTML"),title);
        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void checkout_form(String firstname, String lastname, String zipcode){
        try {
            waitForElementToBePresent(getTxtFirstname());
            enter(getTxtFirstname(),firstname);
            enter(getTxtLastname(),lastname);
            enter(getTxtPostalcode(),zipcode);

        } catch (BaseActionException e) {
            Assert.fail(e.getMessage());
        }
    }



}
