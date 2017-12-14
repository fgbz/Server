package phalaenopsis.satellitegraph.entity;

public class SpatialReference {

	public static SpatialReference WebMercator;

	@com.alibaba.fastjson.annotation.JSONField(serialize=false)
	public double dateline;

	public double getDateline() {
		return dateline;
	}

	@com.alibaba.fastjson.annotation.JSONField(serialize=false)
	public int WKID;


	@com.alibaba.fastjson.annotation.JSONField(serialize=true)
	public String WKT;


	static {
		SpatialReference.WebMercator = new SpatialReference(0x18ed4);
	}

	public SpatialReference(){
		this.WKID = 0;
	}

	public SpatialReference(int WKID) {
		this.WKID = WKID;	
	}

	public SpatialReference(String wkt) {
		this.WKT = wkt;
		this.WKID = 0;
	}

	public SpatialReference Clone() {
		try {
			SpatialReference wKID = (SpatialReference) this.clone();

			wKID.WKID = this.WKID;
			wKID.WKT = this.WKT;
			return wKID;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String ToJson() {
		if (this.WKID >= 1 || (this.WKT != null && this.WKT.length() > 0)) {
			return String.format("{{\"wkid\":%s}}", String.valueOf(this.WKID));
		} else {
			return String.format("{{\"wkt\":\"%s\"}}", this.WKT.replace("\"", "\\\""));
		}
	}

	public static double WorldWidth(SpatialReference sref) {
		if (sref != null) {
			double dateline = sref.dateline;
			if (!Double.isNaN(dateline)) {
				return dateline * 2;
			} else {
				return Double.NaN;
			}
		} else {
			return Double.NaN;
		}
	}
}
