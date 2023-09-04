package com.zorem.ct.webbase.exceptions.factories;

import com.zorem.ct.webbase.driver.manager.DriverManager;
import com.zorem.ct.webbase.enums.BrowserSize;
import com.zorem.ct.webbase.exceptions.BaseActionException;
import com.zorem.ct.webbase.utils.report.Logs;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseElements {

    Logger LOGGER = Logger.getLogger(BaseElements.class.getName());
    protected static WebDriver webDriver;

    public static void setWebDriver(WebDriver webDriver) {
        BaseElements.webDriver = webDriver;
    }

    protected WebDriverWait wait;

    protected int timeoutWebInSeconds = 120;

    public BaseElements() {

        if (!Objects.isNull(DriverManager.getWebDriver())) {
            this.webDriver = (WebDriver) DriverManager.getInstance().getWebDriver();
            PageFactory.initElements(webDriver, this);
        }
    }

    /**
     * Método que cambia las dimensiones de la ventana.
     * @param size constante que representa las dimensiones esperadas. Ejemplo:  max, big, half;
     */
    public void setBrowserSizeTo(BrowserSize size) {
        switch (size) {
            case big:
                webDriver.manage().window().setPosition(new Point(0, 0));
                webDriver.manage().window().setSize(new Dimension(1350, 730));
                break;
            case half:
                webDriver.manage().window().setPosition(new Point(0, 0));
                webDriver.manage().window().setSize(new Dimension(700, 730));
                break;
            default:
                webDriver.manage().window().maximize();
                break;
        }
    }

    /**
     * Método que ejecuta el comando retroceder a la página anterior de Selenium.
     *
     */
    public void backNavigation() {
        webDriver.navigate().back();
    }

    public static void newTabJS(String url) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            String sentencia = "window.open('" + url + "','_blank');";
            executor.executeScript(sentencia);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR("No se pudo abrir una nueva pestaña");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método implementa una espera implícita de N segundos
     *
     * @param sec cantidad de segundos.
     *
     */
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que espera que el elemento esté visible luego de esperar 120s ..
     *
     * @param webElement       elemento donde se realizara el comando.
     * @return retorna el elemento encontrado
     */
    public void waitForVisibility(WebElement webElement) {
        try {
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutWebInSeconds));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            LOGGER.log(Level.INFO, "waitForVisibility" + webElement.toString());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que devuelve si el elemento está visualmente en la ventana o no luego de una espera de 30s.
     *
     * @param webElement elemento donde se realizara el comando.
     * @return retorna verdadero o falso, dependiendo del estado del elemento.
     */
    public boolean waitForVisibilityBoolean(WebElement webElement) {
        try {
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            LOGGER.log(Level.INFO, "waitForVisibilityBoolean" + webElement.toString());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Método que espera que el elemento sea clickable en un plazo máximo de 150s.
     *
     * @param webElement       elemento donde se realizara el comando.
     * @return retorna el elemento encontrado
     */
    public static WebElement waitForElementToBeClickable(WebElement webElement) {
        try {
            FluentWait<WebDriver> fluentWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(150)).pollingEvery(Duration.ofSeconds(1)).ignoring((NoSuchElementException.class));
            webElement = fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));
            return webElement;
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    public static WebElement waitForElementToBePresent(WebElement webElement) {
        try {
            FluentWait<WebDriver> fluentWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(150)).pollingEvery(Duration.ofSeconds(1)).ignoring((NoSuchElementException.class));
            fluentWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement;
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
//            Thread.currentThread().interrupt();
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que espera que el elemento sea clickable luego de esperar 120s.
     *
     * @param webElement       elemento donde se realizara el comando.
     * @return retorna el elemento encontrado
     */
    public void waitForClickablility(WebElement webElement) {

        try {
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutWebInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            LOGGER.log(Level.INFO, "waitForClickablility" + webElement.toString());
        } catch (Exception e) {
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Encuentra un elemento a partir de la clase locator de selenium By.
     *
     * @param byLocator Localizador del elemento en base a la clase de Selenium By.
     *                Por ejemplo: By.id("id-del-elemento")
     * @return retorna el elemento encontrado.
     */
    public static WebElement findElement(By byLocator) {
        WebDriverWait wait;
        try {
            wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
            return webDriver.findElement(byLocator);
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }


    /**
     * Método que ejecuta el comando click de Selenium.
     *
     * @param webElement elemento donde se realizara el comando.
     */
    public void click(WebElement webElement) {
        try {
            waitForElementToBeClickable(webElement);
            webElement.click();
            LOGGER.log(Level.INFO, "click" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement.getLocation() + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que espera que el elemento sea clickable por un tiempo de espera en segundos.
     *
     * @param webElement       elemento donde se realizara el comando.
     * @param timeOutOnSeconds Tiempo de espera de localización.
     * @return retorna el elemento encontrado
     */
    public void clickWithTimeOut(WebElement webElement, int timeOutOnSeconds) {

        for (int i = 0; i < timeOutOnSeconds; i++) {
            try {
                webElement.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    /**
     * Método que ejecuta el comando click sobre un elemento a través de la clase JavascriptExecutor
     *
     * @param webElement elemento donde se realizara el comando.
     */
    public void clickJS(WebElement webElement) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", webElement);
            LOGGER.log(Level.INFO, "ClickJS" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el evento mouse-up sobre un elemento a través de la clase JavascriptExecutor
     *
     * @param webElement elemento donde se realizara el evento.
     */
    public void mouseUp(WebElement webElement) {
        try {
            String rightClickScript = "var evt = document.createEvent('MouseEvents');" +
                    "evt.initEvent('mouseup', true, true);" +
                    "arguments[0].dispatchEvent(evt);";
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript(rightClickScript, webElement);
            LOGGER.log(Level.INFO, "MouseUp" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el evento click derecho sobre un elemento a través de la clase JavascriptExecutor
     *
     * @param webElement elemento donde se realizara el evento.
     */
    public void rightClickJS(WebElement webElement) {
        try {
            String rightClickScript = "var evt = document.createEvent('MouseEvents');" +
                    "evt.initEvent('contextmenu', true, true);" +
                    "arguments[0].dispatchEvent(evt);";
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript(rightClickScript, webElement);
            LOGGER.log(Level.INFO, "rightClickJS" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta la acción drag and drop sobre dos elementos a través de la clase JavascriptExecutor
     *
     * @param webElementDrag elemento de origen de la acción.
     * @param webElementDrop elemento destino de la acción.
     */
    public void DragDropJS(WebElement webElementDrag, WebElement webElementDrop) {
        try {
            Actions actions = new Actions(webDriver);
            actions.dragAndDrop(webElementDrag, webElementDrop).build().perform();
            LOGGER.log(Level.INFO, "DragDropJS" + webElementDrag.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElementDrag + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el comando doble click sobre un elemento a través de la clase JavascriptExecutor
     *
     * @param webElement elemento donde se realizara el comando.
     */
    public void DblclickJS(WebElement webElement) {
        try {
            String doubleClickScript = "var evt = document.createEvent('MouseEvents');" +
                    "evt.initEvent('dblclick', true, true);" +
                    "arguments[0].dispatchEvent(evt);";
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript(doubleClickScript, webElement);
            LOGGER.log(Level.INFO, "DblclickJS" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el comando click sobre un elemento a través de la clase JavascriptExecutor
     *
     * @param byLocator Localizador del elemento en base a la clase de Selenium By.
     *                Por ejemplo: By.id("id-del-elemento")
     */
    public void clickJS(By byLocator) {
        try {
            WebElement element = webDriver.findElement(byLocator);
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("arguments[0].click();", element);
            LOGGER.log(Level.INFO, "clickJS" + byLocator.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(byLocator + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que limpia un elemento de entrada
     * @param webElement Elemento que se desea limpiar, entrada de caracteres
     */
    public void clear(WebElement webElement) {
        try {
            waitForVisibility(webElement);
            webElement.clear();
            LOGGER.log(Level.INFO, "clear" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());

        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que limpia el valor de un elemento de entrada y luego ejecuta el comando sendkeys de Selenium para escribir un nuevo valor
     * @param webElement    elemento donde se realizara el comando.
     * @param textToSend conjunto de caracteres que se tipearan en el elemento
     */
    public void enter(WebElement webElement, String textToSend) {
        try {
            clear(webElement);
            waitForVisibility(webElement);
            webElement.sendKeys(textToSend);
            LOGGER.log(Level.INFO, "enter" + webElement.toString());
        } catch (StaleElementReferenceException staleElementReferenceException) {
            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el comando sendkeys de Selenium para escribir en un elemento de entrada sin limpiar el campo.
     *
     * @param webElement    elemento donde se realizara el comando.
     * @param key conjunto de caracteres que se tipearan en el elemento
     */
    public void press(WebElement webElement, Keys key) {
        try {

            waitForVisibility(webElement);
            webElement.sendKeys(key);
            LOGGER.log(Level.INFO, "press" + webElement.toString());

        } catch (StaleElementReferenceException staleElementReferenceException) {

            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());

        } catch (Exception e) {

            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Metodo que devuelve si el elemento está visualmente en la ventana o no.
     *
     * @param webElement elemento donde se realizara el comando.
     * @return retorna verdadero o falso, dependiendo del estado del elemento.
     */

    public boolean isDisplayed(WebElement webElement) {
        try {
            LOGGER.log(Level.INFO, "isDisplayed" + webElement.toString());
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            Assert.fail(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que devuelve si un valor es un número o no.
     *
     * @param str valor.
     * @return retorna verdadero o falso, dependiendo del tipo de dato.
     */
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * Método que devuelve si el elemento está seleccionado o no. Utilizado mayormente en checks, radiobuttons.
     *
     * @param webElement elemento donde se realizara el comando.
     * @return retorna verdadero o falso, dependiendo del estado del elemento.
     */
    public boolean isChecked(WebElement webElement) {

        try {
            LOGGER.log(Level.INFO, "isChecked" + webElement.toString());
            return Boolean.parseBoolean(webElement.getAttribute("checked"));
        } catch (NoSuchElementException e) {
            throw new BaseActionException("Element is not Checked." + e.getMessage());
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException("Element is not." + e.getMessage());
        }
    }

    /**
     * Realiza el scroll hasta la ubicación de un elemento.
     *
     * @param webElement elemento donde se realizara el comando.
     */
    public void scrollTo(WebElement webElement) {
        ((JavascriptExecutor) webDriver)
                .executeScript("arguments[0].scrollIntoView(true);", webElement);
        LOGGER.log(Level.INFO, "scrollTo" + webElement.toString());
    }

    /**
     * Realiza el scroll hasta el top de la ventana.
     *
     */
    public void scrollUp() {
        waitFor(1);
        ((JavascriptExecutor) webDriver)
                .executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        waitFor(1);
    }

    /**
     * Realiza el scroll vertical de X pixeles.
     *
     * @param value cantidad de pixeles que se debe scrollear.
     */
    public void scrollDown(int value) {
        ((JavascriptExecutor) webDriver).executeScript("scroll(0," + value + ");");
    }

    /**
     * Método que obtiene el texto del elemento.
     *
     * @param webElement elemento del cual se obtendrá el texto.
     */
    public String getText(WebElement webElement) {
        try {
            waitForVisibility(webElement);
            return webElement.getText();

        } catch (StaleElementReferenceException staleElementReferenceException) {

            Logs.ERROR(webElement + " es un elemento obsoleto");
            throw new BaseActionException(staleElementReferenceException.getMessage());

        } catch (Exception e) {

            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que ejecuta el evento move on sobre un elemento a partir de la clase locator de selenium By.
     *
     * @param byLocator Localizador del elemento en base a la clase de Selenium By.
     *                Por ejemplo: By.id("id-del-elemento")
     * @return retorna el elemento encontrado.
     */
    public static WebElement MoveOnElement(By byLocator) {
        try {
            long time = 800;
            WebElement element = webDriver.findElement(byLocator);
            Actions action = new Actions(webDriver);
            action.moveToElement(element).build().perform();
            return element;
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método que resalta un elemento a través de la clase JavascriptExecutor
     *
     * @param byLocator Localizador del elemento en base a la clase de Selenium By.
     *                Por ejemplo: By.id("id-del-elemento")
     */
    public static void ResaltarMensaje(By byLocator) {
        try {
            Actions actons = new Actions(webDriver);
            actons.sendKeys(Keys.ESCAPE);
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("arguments[0].setAttribute('style', 'background: #b9d803;');", byLocator);
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Metodo que devuelve un String aleatorio de longitud N.
     *
     * @param n longitud del String a generar.
     * @return retorna un String de longitud N.
     */
    public static String getAlphaString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    /**
     * Método implementa una espera implícita de 20 segundos hasta que cargue el contenido de la página.
     *
     */
    public static void waitDOMLoaded(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        } catch (Exception e) {
            Logs.ERROR(e.getMessage());
            throw new BaseActionException(e.getMessage());
        }
    }

    /**
     * Método implementa una espera implícita de 10 segundos hasta que cargue el contenido de la página.
     *
     */
    public static void waitLoadPage(){
        waitFor(10);
    }
}
