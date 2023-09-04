# Framework Web Automation

## 1. Tecnologías utilizadas

Java + Maven + Selenium Webdriver + Cucumber + TestNG

### Dependencias Utilizadas

| Dependencias                           | Versión |
|----------------------------------------|---------|
| org.apache.commons: commons-lang3      | 3.12.0  |
| org.selenium.selenium:selenium-java    | 4.11.0  | 
| io.cucumber: cucumber-core             | 7.8.0   |
| io.cucumber: cucumber-java             | 7.8.0   |
| io.cucumber: cucumber-picocontainer    | 7.8.0   |
| io.cucumber: cucumber-testng           | 7.8.0   |
| org.apache.logging.log4j:log-core      | 2.17.1  | 
| log4j                                  | 1.2.17  | 
| org.projectlombok:lombok               | 1.18.24 |
| io.qameta.allure:allure-testng         | 2.23.0  |
| io.github.bonigarcia: webdrivermanager | 5.4.1   |
| com.github.bastiaanjansen: otp-java    | 2.0.1   |
| maven-compiler-plugin                  | 3.10.1  |
| maven-resources-plugin                 | 3.3.1   |
| maven-surefire-plugin                  | 3.1.2   |
| maven-cucumber-reporting               | 5.7.6   |
| force-partner-api                      | 51.0.0  |

## 2. Arquitectura del proyecto

Este proyecto se compone de 3 paquetes principalmente:

- **webbase** : Contiene el código genérico para utilizar en proyectos de automatización web.
- **resources** : Contiene el archivo con las credenciales de acceso.
- **webapp** : Contiene el código específico que implementa los casos de prueba.


### resources

- **features:** Contiene los archivos con la descripción de los casos de prueba escritos en lenguaje gherkin.

### webapp

- **runner:** En este paquete están el archivo necesario para la ejecución de los casos descritos en los features.
- **stepDefinitions:** En este paquete está la implementación de los métodos asociados a los pasos descritos en el
  feature.
- **pages:** En este paquete están todas las clases Page del proyecto que representan cada interfaz de la aplicación.

## 3. Configuración para la ejecución


### src/test/java/com/zorem/ct/webapp/runner/TestRunnerWeb.java

Donde se indica los features a ejecutar.

### testngWeb.xml

Donde se indica el navegador web que realizará la ejecución y el Runner asociado.

## 4. Ejecución

Para ejecutar los casos de prueba indicados en el Runner deberá ejecutar el siguiente comando desde el directorio raíz
del proyecto o en todo caso ejecutar desde el archivo testngWeb.xml con IntelliJ IDEA:

``` shell
mvn clean test
```

## 5. Allure Report

El proyecto está configurado con un plugin que descarga e inicia el servidor local de Allure para visualizar el reporte.

Los archivos de allure se pueden encontrar en el directorio: `/allure-results`.

Para visualizar el reporte hay que iniciar el servidor de allure indicando la ruta de los archivos allure generados:

``` shell
allure serve allure-results
```
