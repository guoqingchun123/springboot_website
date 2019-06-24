package com.bestvike.website.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "DocFile")
public class DocFile implements Serializable {

	private static final long serialVersionUID = 1207962979849921253L;

	@Id
	private String id;
	private String viewUrl;
	private String thumbUrl;
	private String imageDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}
}
