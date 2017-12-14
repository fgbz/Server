/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangw786
 *
 * 2017年5月4日下午4:28:29
 * 根据业务与终端 构建分析的图层组
 */
public class AnalysisLayerGroup implements Serializable {
	

	private static final long serialVersionUID = -5156577940494004538L;

	public String Id;

    /**
     * 业务类型
     */
    public short BusinessType;

    /**
     * 终端类型 
     */
    public short TerminalType;

    /**
     * 展示类型 
     */
    public short DisplayType;

    /**
     * 分析的图层集合 
     */
    public List<AnalysisLayer> Layers;

    /**
     * ArcGIS GeometryServer
     */
    public String GeometryServer;


    public AnalysisLayerGroup() {
        Layers = new ArrayList<AnalysisLayer>();
    }

    public void AddLayers(AnalysisLayer[] layers) {
    	for (AnalysisLayer analysisLayer : layers) {
    		 analysisLayer.AnalysisLayerGroup = this;
             this.Layers.add(analysisLayer);
		} 
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the businessType
	 */
	public short getBusinessType() {
		return BusinessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(short businessType) {
		BusinessType = businessType;
	}

	/**
	 * @return the terminalType
	 */
	public short getTerminalType() {
		return TerminalType;
	}

	/**
	 * @param terminalType the terminalType to set
	 */
	public void setTerminalType(short terminalType) {
		TerminalType = terminalType;
	}

	/**
	 * @return the displayType
	 */
	public short getDisplayType() {
		return DisplayType;
	}

	/**
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(short displayType) {
		DisplayType = displayType;
	}

	/**
	 * @return the layers
	 */
	public List<AnalysisLayer> getLayers() {
		return Layers;
	}

	/**
	 * @param layers the layers to set
	 */
	public void setLayers(List<AnalysisLayer> layers) {
		Layers = layers;
	}

	/**
	 * @return the geometryServer
	 */
	public String getGeometryServer() {
		return GeometryServer;
	}

	/**
	 * @param geometryServer the geometryServer to set
	 */
	public void setGeometryServer(String geometryServer) {
		GeometryServer = geometryServer;
	}
}
