package utilidades;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Utils {
    public static int comprobarFuerzaPassword(String password) {
        int fuerza = 0;
        if (password.length() >= 8) {
            fuerza++;
        }
        if (password.matches(".*[a-z].*")) {
            fuerza++;
        }
        if (password.matches(".*[A-Z].*")) {
            fuerza++;
        }
        if (password.matches(".*[0-9].*")) {
            fuerza++;
        }
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            fuerza++;
        }
        if (fuerza < 3) {
            fuerza = 1;
        } else if (fuerza < 5) {
            fuerza = 2;
        } else {
            fuerza = 3;
        }
        return fuerza;
    }

    public static DocumentFilter limitaCaracteres(int numCaracteres) {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= numCaracteres) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        return filter;
    }

    public static boolean isValidLogin(String login) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return login.matches(regex);
    }

    public static boolean isValidPassword(String password) {
        String regex = "^[A-Za-z\\d@$!%*#?&]{8,}$";
        return password.matches(regex);
    }

    public static boolean validarDNI(String dni) {
        boolean valido = false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        if (dni.length() == 9) {
            char letra = dni.charAt(8);
            int num = Integer.parseInt(dni.substring(0, 8));
            int resto = num % 23;
            if (letra == letras.charAt(resto)) {
                valido = true;
            }
        }
        return valido;
    }
}
