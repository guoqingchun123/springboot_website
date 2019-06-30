package com.bestvike.website.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "App_Version")
public class AppVersion implements Serializable {

	private static final long serialVersionUID = 7886686963325199754L;

	// 1.1.1 --> 1*1000+1*100+1
	private String versionId;
	private String isForce;

	@Transient
	private String hasNewer;
	@Transient
	private String updateUrl;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getIsForce() {
		return isForce;
	}

	public void setIsForce(String isForce) {
		this.isForce = isForce;
	}

	public String getHasNewer() {
		return hasNewer;
	}

	public void setHasNewer(String hasNewer) {
		this.hasNewer = hasNewer;
	}

	public String getUpdateUrl() {
		return updateUrl;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
}
