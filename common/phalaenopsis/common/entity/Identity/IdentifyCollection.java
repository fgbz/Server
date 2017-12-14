package phalaenopsis.common.entity.Identity;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.satellitegraph.entity.Polygon;

public class IdentifyCollection {
	@JsonProperty("IdentifyID")
	public String identifyID;

	@JsonProperty("Coordinates")
	public Polygon coordinates;

	@JsonProperty("Items")
	public List<IdentifyItem> items;

	public IdentifyCollection() {
		this.items = new ArrayList<IdentifyItem>();
	}

	public String getIdentifyID() {
		return identifyID;
	}

	public void setIdentifyID(String identifyID) {
		this.identifyID = identifyID;
	}

	public Polygon getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Polygon coordinates) {
		this.coordinates = coordinates;
	}

	public List<IdentifyItem> getItems() {
		return items;
	}

	public void setItems(List<IdentifyItem> items) {
		this.items = items;
	}
	
}
