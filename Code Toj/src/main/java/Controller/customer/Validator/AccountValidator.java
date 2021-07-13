package Controller.customer.Validator;

import Controller.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class AccountValidator {

    public static RequestValidator validateSigin(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("email", "Email non valida.");
        validator.assertMatch("password", Pattern.compile("^(?=.*[0-9a-zA-Z!@#&()–[{}]:;',?/*~$^+=<>]).{8,30}$"),"Password deve avere almeno 8 caratteri.");
        return validator;
    }

    public static RequestValidator updateData(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        if (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0)
            validator.assertMatch("telefono", Pattern.compile("^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$"), "Inserire un numero telefonico valido");
        if (request.getParameter("password") != null && request.getParameter("password").compareTo("") != 0)
            validator.assertMatch("password", Pattern.compile("^(?=.*[0-9a-zA-Z!@#&()–[{}]:;',?/*~$^+=<>]).{8,30}$"),"Password deve avere almeno 8 caratteri e massimo 30.");
        if (request.getParameter("nome") != null && request.getParameter("nome").compareTo("") != 0)
            validator.assertMatch("nome", Pattern.compile("^[a-zA-Z ]*$"), "Inseririsci il tuo nome reale.");
        if (request.getParameter("cognome") != null && request.getParameter("cognome").compareTo("") != 0)
            validator.assertMatch("cognome", Pattern.compile("^[a-zA-Z ]*$"), "Inseririsci il tuo cognome reale.");
        if(request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0)
            validator.assertEmail("email", "Email non valida.");
        return validator;
    }

    public static RequestValidator updateAddress(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        if (request.getParameter("CAP") != null && request.getParameter("CAP").compareTo("") != 0){
            validator.assertMatch("CAP", Pattern.compile("^\\d+$"), "Inserisci un CAP valido");
            validator.assertSize("5", "5", "CAP non completo");
        }
        if (request.getParameter("city") != null && request.getParameter("city").compareTo("") != 0)
            validator.assertMatch("city", Pattern.compile("^[a-zA-Z ]*$"), "Inserisci una città valida.");
        if (request.getParameter("paese") != null && request.getParameter("paese").compareTo("") != 0)
            validator.assertMatch("paese", Pattern.compile("^[a-zA-Z ]*$"), "Inserisci uno Stato valido.");
        return validator;
    }

    public static RequestValidator contactUs(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        if (request.getParameter("name") != null && request.getParameter("name").compareTo("") != 0)
            validator.assertMatch("name", Pattern.compile("^[a-zA-Z ]*$"), "Inseririsci il tuo nome reale.");
        if(request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0)
            validator.assertEmail("email", "Email non valida.");
        return validator;
    }
}
