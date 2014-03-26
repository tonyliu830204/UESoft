package com.syuesoft.model;

/**
 * FbkCarDcnameId entity. @author MyEclipse Persistence Tools
 */

public class FbkCarDcnameId extends BaseBean {

	// Fields

	private FbkDcserveyName fbkDcserveyName;
	private FbkCollect fbkCollect;
	// Property accessors

	public FbkDcserveyName getFbkDcserveyName() {
		return this.fbkDcserveyName;
	}

	public void setFbkDcserveyName(FbkDcserveyName fbkDcserveyName) {
		this.fbkDcserveyName = fbkDcserveyName;
	}


	public FbkCollect getFbkCollect() {
		return fbkCollect;
	}

	public void setFbkCollect(FbkCollect fbkCollect) {
		this.fbkCollect = fbkCollect;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FbkCarDcnameId))
			return false;
		FbkCarDcnameId castOther = (FbkCarDcnameId) other;

		return ((this.getFbkDcserveyName() == castOther.getFbkDcserveyName()) || (this.getFbkDcserveyName() != null && castOther.getFbkDcserveyName() != null && this.getFbkDcserveyName().equals(
				castOther.getFbkDcserveyName())))
				&& ((this.getFbkCollect() == castOther.getFbkCollect()) || (this.getFbkCollect() != null && castOther.getFbkCollect() != null && this.getFbkCollect().equals(castOther.getFbkCollect())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFbkDcserveyName() == null ? 0 : this.getFbkDcserveyName().hashCode());
		result = 37 * result + (getFbkCollect() == null ? 0 : this.getFbkCollect().hashCode());
		return result;
	}

}