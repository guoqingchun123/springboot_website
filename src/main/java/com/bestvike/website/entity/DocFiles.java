package com.bestvike.website.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "DocFiles")
public class DocFiles implements Serializable {

	@Id
	private String id;
	private String keyId;
	private String fileType;
	private String docType;
	private String docName;
	@DBRef
	private List<DocFile> imageList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public List<DocFile> getImageList() {
		return imageList;
	}

	public void setImageList(List<DocFile> imageList) {
		this.imageList = imageList;
	}
}
