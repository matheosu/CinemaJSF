package bean;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;

import model.Filme;
import model.enums.Classificacao;
import model.enums.StatusFilme;
import util.ByteConverterUtil;
import util.ClassificacaoUtil;
import util.JSFUtil;
import util.PathUtil;
import util.StatusFilmeUtil;

@ManagedBean(name = "filmeBean")
@RequestScoped
public class FilmeBean extends BaseBean<Filme> {

//	private static final Logger logger = Logger.getLogger(FilmeBean.class);

	@ManagedProperty(value = "#{generoBean}")
	private GeneroBean generoBean;

	@ManagedProperty(value = "#{imageBean}")
	private ImageBean imageBean;

	public FilmeBean() {
		super();
	}

	public GeneroBean getGeneroBean() {
		return generoBean;
	}

	public void setGeneroBean(GeneroBean generoBean) {
		this.generoBean = generoBean;
	}

	public ImageBean getImageBean() {
		return imageBean;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean = imageBean;
	}

	public List<Classificacao> getClassificacoes() {
		return ClassificacaoUtil.getClassificacoes();
	}

	@Override
	protected Filme newInstance() {
		return new Filme();
	}

	public List<StatusFilme> getStatus() {
		return StatusFilmeUtil.getStatusFilme();
	}

	public static String show() {
		return PathUtil.getActionList(Filme.class, true);
	}

	@Override
	public String save() {
		this.getInstance().setImagem(imageBean.getByteImage());
		return super.save();
	}

	@Override
	public String edit() {
		Long id = JSFUtil.getParametroLong("id");
		Filme filme = dao.findById(id);
		if(filme !=null ){
			this.setInstance(filme);
			
			if (filme.getImagem()!=null) {
				byte[] byteImage = ByteConverterUtil.parsetByteToPrimite(filme.getImagem());
				imageBean.setImage(new DefaultStreamedContent(new ByteArrayInputStream(byteImage),"image/jpg",filme.getTitulo()+"_IMAGEM"));
			}
			return PathUtil.getActionEdit(Filme.class, false);
		}
		
		return PathUtil.getActionList(Filme.class, true);
	}
	
	
}
