#!/bin/bash
# Establecer ruta base (directorio donde está este script)
BASE_DIR="$(dirname "$(readlink -f "$0")")"

# Definir ruta al SDK de JavaFX
FX_PATH="$BASE_DIR/../lib/javafx-sdk-22.0.1/lib"

# Ejecutar el JAR con los módulos necesarios
java --module-path "$FX_PATH" --add-modules javafx.controls,javafx.fxml,javafx.media -jar calculator.jar
