package dac.atv.av.view.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

import java.time.LocalDate;

@FacesValidator
public class DependenteDtNascValidator implements Validator<LocalDate> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, LocalDate date) throws ValidatorException {
        if(!date.isBefore(LocalDate.now())){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Data de Nascimento Inválida", "");
            throw new ValidatorException(message);
        }
    }
}
