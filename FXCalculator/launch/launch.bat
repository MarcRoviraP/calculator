@echo off
:: Establecer ruta base
set BASE_DIR=%~dp0

:: Definir ruta al SDK de JavaFX
set FX_PATH=%BASE_DIR%..\lib\javafx-sdk-22.0.1\lib

:: Ejecutar el JAR con los módulos necesarios
java --module-path "%FX_PATH%" --add-modules javafx.controls,javafx.fxml,javafx.media -jar calculator.jar

:: Pausar para ver cualquier mensaje de error o salida
pause
