package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import exception.ModelException;
import model.Pessoa;

public class RGValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {
		if (object instanceof String) {
			String rg = (String) object;

			try {
				Pessoa.validarRG(rg);
			} catch (ModelException e) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, e.getMessage(), null));

			}

		}
	}

}
