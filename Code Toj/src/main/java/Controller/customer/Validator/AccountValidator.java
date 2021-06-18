package Controller.customer.Validator;

import Controller.http.RequestValidator;

import javax.servlet.http.HttpServletRequest;

public class AccountValidator {

    public static RequestValidator validateSigin(HttpServletRequest request){
        RequestValidator validator = new RequestValidator(request);
        validator.assertEmail("email", "Email non valida");
        validator.assertEmail("password", "Password non valida");
        return validator;
    }

}
