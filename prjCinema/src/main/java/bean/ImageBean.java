package bean;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import util.ByteConverterUtil;

@ManagedBean(name = "imageBean")
@ViewScoped
public class ImageBean {

	private static final Logger logger = Logger.getLogger(ImageBean.class);

	private UploadedFile file;
	private StreamedContent image;

	public ImageBean() {
		super();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public void upload() {
		if (file != null && file.getContents() != null) {
			this.setImage(new DefaultStreamedContent(new ByteArrayInputStream(
					file.getContents())));
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		logger.info("Uploading image!");
		this.setFile(event.getFile());
		this.upload();
	}

	public Byte[] getByteImage() {
		Byte[] byteImage = null;
		if (file != null && file.getContents() != null) {
			byteImage = ByteConverterUtil.parseByteToObject(file.getContents());
		}

		if (byteImage == null)
			logger.warn("ByteImage is null");

		return byteImage;
	}

}
