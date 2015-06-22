package converters.enums;

import javax.faces.convert.FacesConverter;

import annotations.InjectUtil;
import annotations.Util;
import util.converters.EnumUtil;
import model.enums.Classificacao;

@FacesConverter(value = "classificacao-converter", forClass = Classificacao.class)
public class ClassificacaoConverter extends BaseEnumConverter<Classificacao> {

	@Util(Classificacao.class)
	private EnumUtil<Classificacao> util;

	public ClassificacaoConverter() {
		super();
		InjectUtil.doInjection(this);
	}

	public EnumUtil<Classificacao> getUtil() {
		return util;
	}

	public void setUtil(EnumUtil<Classificacao> util) {
		this.util = util;
	}

}
