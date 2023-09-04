package com.zorem.ct.webapp.stepDefinitions;

import com.zorem.ct.webapp.pages.HomePage;
import com.zorem.ct.webbase.utils.report.ManageScreenShoot;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class SauceStepDefinition {

    HomePage homePage = new HomePage();

    @Given("que estoy en la página {string}")
    public void queEstoyEnLaPágina(String url) {
        ManageScreenShoot.takeScreenShoot();
        homePage.setSite(url);
        ManageScreenShoot.takeScreenShoot();
    }

    @When("me autentico con el usuario {string} y contraseña {string}")
    public void meAutenticoConElUsuarioYContraseña(String user, String pass) {
        ManageScreenShoot.takeScreenShoot();
        homePage.ingresarCredencialesPara(user,pass);
        ManageScreenShoot.takeScreenShoot();
    }

    @And("visualizo el contenido del carrito")
    public void visualizoElContenidoDelCarrito() {
        ManageScreenShoot.takeScreenShoot();
        homePage.contenido_carrito();
        ManageScreenShoot.takeScreenShoot();
    }

    @And("completo el formulario con:")
    public void completoElFormularioCon(DataTable dataTable) {
        ManageScreenShoot.takeScreenShoot();

        List<List<String>> Form = dataTable.asLists(String.class);
        String Firstname = Form.get(1).get(1);
        String Lastname = Form.get(2).get(1);
        String Code = Form.get(3).get(1);
        homePage.checkout_form(Firstname,Lastname,Code);
        ManageScreenShoot.takeScreenShoot();
    }

    @And("hago clic en el botón Checkout")
    public void hagoClicEnElBotónCheckout() {
        ManageScreenShoot.takeScreenShoot();
        homePage.click_checkout();
        ManageScreenShoot.takeScreenShoot();
    }

    @And("hago clic en el botón continue")
    public void hagoClicEnElBotónContinue() {
        ManageScreenShoot.takeScreenShoot();
        homePage.click_continue();
        ManageScreenShoot.takeScreenShoot();
    }

    @And("hago clic en el botón Finish")
    public void hagoClicEnElBotónFinish() {
        ManageScreenShoot.takeScreenShoot();
        homePage.click_finish();
        ManageScreenShoot.takeScreenShoot();
    }

    @Then("veo la confirmación {string}")
    public void veoLaConfirmación(String title) {
        ManageScreenShoot.takeScreenShoot();
        homePage.confirmacion(title);
        ManageScreenShoot.takeScreenShoot();
    }

    @And("agrego {int} productos al carrito")
    public void agregoProductosAlCarrito(int productos) {
        ManageScreenShoot.takeScreenShoot();
        homePage.agregar_productos(productos);
        ManageScreenShoot.takeScreenShoot();
    }
}
