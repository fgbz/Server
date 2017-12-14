package phalaenopsis.pjmapspot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.pjmapspot.bean.PjMapSpotBean;
import phalaenopsis.pjmapspot.bean.PjMapSpotFlowAuditBean;
import phalaenopsis.pjmapspot.entity.PjRegionflow;
import phalaenopsis.pjmapspot.service.PjMapSpotService;
import phalaenopsis.pjmapspot.service.RegionFlowService;
import java.util.List;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/19
 * 修改历史：
 * 1. [2017/10/19]创建文件 by chunl
 *
 * @author chunl
 */
@RestController
@RequestMapping(value = "/PJGraph/PJRegionFlowService")
public class RegionFlowController {
	private static Logger logger = LoggerFactory.getLogger(RegionFlowController.class);
    @Autowired
    private RegionFlowService service;
    @Autowired
    private PjMapSpotService pjMapSpotService;

    
    /**
     * 市级审核流程处理
     * @return 返回标识
     */
    @RequestMapping(value = "/flowAudit", method = RequestMethod.POST)
    public boolean flowAudit(@RequestBody PjMapSpotFlowAuditBean pjMapSpotFlowAuditBean){
    	try {
    		pjMapSpotService.flowAudit(pjMapSpotFlowAuditBean);
		} catch (Exception e) {
			logger.error("市级审核流程处理失败！",e);
			return false;
		}
        return true;
    }
    
    /**
     * 上报与退回
     * @return 返回标识
     */
    @RequestMapping(value = "/flowReportAndBack", method = RequestMethod.POST)
    public boolean flowReportAndBack(@RequestBody PjMapSpotBean pjMapSpotBean){
    	try {
    		 AppSettings appSettings = new AppSettings();
    		 Short year = Short.valueOf(appSettings.getReportYear());
             pjMapSpotBean.setYear(year);
    		 service.saveOrUpdate(pjMapSpotBean);
		} catch (Exception e) {
			logger.error("上报与退回失败！",e);
			return false;
		}
    	return true;
    }

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public List<PjRegionflow>  getList(@RequestBody PjRegionflow pjRegionflow){
    	AppSettings appSettings = new AppSettings();
		Short year = Short.valueOf(appSettings.getReportYear());
		pjRegionflow.setYear(year);
    	return service.getList(pjRegionflow);
    }
}
