package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import exception.ModelException;
import model.Pessoa;

@FacesValidator("cpf-validator")
public class CPFValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object object)
			throws ValidatorException {
		if(object instanceof String){
			String cpf = (String) object;

			try {
				Pessoa.validarCPF(cpf);
			} catch (ModelException e) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			}
		}
	}
}
