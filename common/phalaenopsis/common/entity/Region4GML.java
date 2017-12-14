package phalaenopsis.common.entity;

public class Region4GML {
	public byte[] id;
	
	public String code;
	
	public String name;
	
	public byte[] gML;
	
	public short rank;
	
	public byte[] getId() {
		return id;
	}

	public void setId(byte[] id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getgML() {
		return gML;
	}

	public void setgML(byte[] gML) {
		this.gML = gML;
	}

	public short getRank() {
		return rank;
	}

	public void setRank(short rank) {
		this.rank = rank;
	}

	public int getRegionType() {
		return regionType;
	}

	public void setRegionType(int regionType) {
		this.regionType = regionType;
	}

	public int getpARENT_ID() {
		return pARENT_ID;
	}

	public void setpARENT_ID(int pARENT_ID) {
		this.pARENT_ID = pARENT_ID;
	}

	public int regionType;
	
	public int pARENT_ID;
}
