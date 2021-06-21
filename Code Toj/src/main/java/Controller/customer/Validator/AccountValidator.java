package Controller.customer.Validator;

import Controller.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class AccountValidator {

    public static RequestValidator validateSigin(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("email", "Email non valida");
        validator.assertMatch("password", Pattern.compile("^\\w{8}"),"Password deve avere almeno 8 caratteri");
        return validator;
    }

}
