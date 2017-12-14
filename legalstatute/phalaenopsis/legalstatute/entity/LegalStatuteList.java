package phalaenopsis.legalstatute.entity;

import java.util.List;

public class LegalStatuteList {
	private List<LegalStatute> legalStatuteList;
	
	private int[] idInt;

	public List<LegalStatute> getLegalStatuteList() {
		return legalStatuteList;
	}

	public void setLegalStatuteList(List<LegalStatute> legalStatuteList) {
		this.legalStatuteList = legalStatuteList;
	}

	public int[] getIdInt() {
		return idInt;
	}

	public void setIdInt(int[] idInt) {
		this.idInt = idInt;
	}

}
