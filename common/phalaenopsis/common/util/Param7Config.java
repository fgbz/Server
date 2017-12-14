package phalaenopsis.common.util;



import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.method.Tools.StrUtil;

public class Param7Config {
	private static Param7Config param7;



	public Param7Config GetConfig() {
		AppSettings appSettings = new AppSettings();
		DeltaX = Double.valueOf(appSettings.getDeltaX());
		DeltaY = Double.valueOf(appSettings.getDeltaY());
		DeltaZ = Double.valueOf(appSettings.getDeltaZ());
		Rx = Double.valueOf(appSettings.getRx());
		Ry = Double.valueOf(appSettings.getRy());
		Rz = Double.valueOf(appSettings.getRz());
		K = Double.valueOf(appSettings.getK());

		// LazyInitializer.EnsureInitialized(ref param7, () =>
		// ConfigurationManager.GetSection("param7") as Param7Config);
		return this;
	}

	// private Double d_DeltaX;
	//@JsonProperty("DeltaX")
	public Double DeltaX;

	// private Double d_DeltaY;
	//@JsonProperty("DeltaY")
	public Double DeltaY;

	// private Double d_DeltaZ;
	//@JsonProperty("DeltaZ")
	public Double DeltaZ;

	// private Double d_Rx;
	//@JsonProperty("Rx")
	public Double Rx;

	// private Double d_Ry;
	//@JsonProperty("Ry")
	public Double Ry;

	//@JsonProperty("Rz")
	public Double Rz;

	//@JsonProperty("K")
	public Double K;


}
