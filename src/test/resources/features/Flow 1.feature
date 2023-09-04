
Feature: Realizar una compra en saucedemo.com

  @Caso-1
  Scenario: Compra exitosa de dos productos
    Given que estoy en la página "https://www.saucedemo.com/"
    When me autentico con el usuario "standard_user" y contraseña "secret_sauce"
    And agrego 2 productos al carrito
    And visualizo el contenido del carrito
    And hago clic en el botón Checkout
    And completo el formulario con:
      | Campo        | Valor  |
      | First Name   | Kevin  |
      | Last Name    | Arnold |
      | Zip          | 11002  |
    And hago clic en el botón continue
    And hago clic en el botón Finish
    Then veo la confirmación "Thank you for your order!"
