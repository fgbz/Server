package phalaenopsis.allWeather.enums;

public enum MobileSpotType {

	Legal(2), NotNew(3);

	private MobileSpotType(int v) {
		this.value = v;
	}

	public static MobileSpotType getSpotType(int v) {
		for (MobileSpotType item : MobileSpotType.values()) {
			if (item.value == v)
				return item;
		}
		return null;
	}

	private int value;

}
