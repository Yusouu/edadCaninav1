package es.davante.edadcanina;

import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter({"errorValidacion", "edadMinima", "edadMaxima"})
    public static void setErrorValidacion(EditText editText, Validacion validacion, int edadMinima, int edadMaxima) {
        if (editText == null || validacion == null) {
            return;
        }

        switch (validacion) {
            case OK:
                editText.setError(null);
                break;
            case VACIO:
                editText.setError(editText.getContext().getString(R.string.error_vacio));
                editText.requestFocus();
                break;
            case NO_NUMERO:
                editText.setError(editText.getContext().getString(R.string.error_no_numero));
                editText.requestFocus();
                break;
            case FUERA_DE_RANGO:
                editText.setError(editText.getContext().getString(R.string.error_rango, edadMinima, edadMaxima));
                editText.requestFocus();
                break;
        }
    }
}