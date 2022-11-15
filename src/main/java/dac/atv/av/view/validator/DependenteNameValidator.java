package dac.atv.av.view.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator
public class DependenteNameValidator implements Validator<String> {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String name)
            throws ValidatorException {

        if(name.isBlank()){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Nome não pode ser vazio", "");
            throw new ValidatorException(message);
        }

    }
}
