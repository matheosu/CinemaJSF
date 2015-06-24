package validators;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import exception.ModelException;
import model.Pessoa;

@FacesValidator("nascimento-validator")
public class NascimentoValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object object)
			throws ValidatorException {
		if(object instanceof Calendar){
			Calendar c = (Calendar) object;
			
			try {
				Pessoa.validarNascimento(c);
			} catch (ModelException e) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}
		}
	}

}
