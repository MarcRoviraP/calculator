package utilidades;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
 
Original:
https://riptutorial.com/es/javafx/example/23068/cambio-dinamico-de-idioma-cuando-la-aplicacion-se-esta-ejecutando
*/

public final class I18N {

    private static final ObjectProperty<Locale> locale;

    static {
        locale = new SimpleObjectProperty<>(getDefaultLocale());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    private static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale local) {
        locale.set(local);
        Locale.setDefault(local);
    }

    private static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(Locale.ENGLISH, new Locale("es"), new Locale("ca")));
    }


    private static Locale getDefaultLocale() {
        Locale sysDefault = Locale.getDefault();
        return getSupportedLocales().contains(sysDefault) ? sysDefault : new Locale("es");
    }


    private static String get(final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle("strings", getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    public static StringBinding createStringBinding(final String key, Object... args) {
        return Bindings.createStringBinding(() -> get(key, args), locale);
    }

}