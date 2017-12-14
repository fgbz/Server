package phalaenopsis.pjmapspot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phalaenopsis.pjmapspot.bean.PjMapSpotBean;
import phalaenopsis.pjmapspot.dao.PjRegionFlowDao;
import phalaenopsis.pjmapspot.dao.PjRegionFlowLogDao;
import phalaenopsis.pjmapspot.entity.PjRegionflow;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author chunl
 */
@Service
public class RegionFlowService {

	@Autowired
	private PjRegionFlowDao pjRegionFlowDao;
	@Autowired
	private PjRegionFlowLogDao pjRegionFlowLogDao;
    /**
     * saveOrUpdate 上报与回退
     * @param pjMapSpotBean
     * @param listPjMapSpotBean
     * @return
     */
    public Integer saveOrUpdate(PjMapSpotBean pjMapSpotBean){
    	List<PjRegionflow> listPjMapSpotBean = new ArrayList<PjRegionflow>();
    	
    	List<Long> regionList = pjMapSpotBean.getRegionId();
    	if(regionList!=null&&regionList.size()>0){
    		for(Long regionId:regionList){
    			PjRegionflow pjRegionflow = new PjRegionflow();
        		pjRegionflow.setApproveInfo(pjMapSpotBean.getApproveInfo());
        		pjRegionflow.setApproveName(pjMapSpotBean.getApproveName());
        		pjRegionflow.setRegionId(regionId);
        		pjRegionflow.setState(getState(pjMapSpotBean.getType(),pjMapSpotBean.getGrade()));
        		pjRegionflow.setYear(pjMapSpotBean.getYear());
        		listPjMapSpotBean.add(pjRegionflow);
    		}
    	}
    	pjMapSpotBean.setState(getState(pjMapSpotBean.getType(),pjMapSpotBean.getGrade()));
    	if(pjMapSpotBean.getType().equals("2") && pjMapSpotBean.getGrade() ==2 && regionList!=null&&regionList.size()>0){
    		int i = pjRegionFlowDao.updatePJMapSpotInfoState(regionList);
    	}
    	int result = pjRegionFlowDao.saveOrUpdate(pjMapSpotBean, listPjMapSpotBean);
    	//增加日志
    	pjRegionFlowLogDao.saveOrUpdate(listPjMapSpotBean);
    	return result;
    }
    
    
    public Short getState(String type,int grade){
    	Short state = 0;
    	 //1上报2退回
    	if("1".equals(type)){
    		//1区县 2市级 3省级
    		if(grade==1){
    			//区级已上报市级
    			state = 1;
    		}else if(grade==2){
    			//市级已上报省级
    			state = 2;
    		}
    	}else if("2".equals(type)){
    		//1区县 2市级 3省级
    		if(grade==2){
    			//市级退回区级
    			state = 3;
    		}else if(grade==3){
    			//省级退回市级
    			state = 4;
    		}
    	}
    	return state;
    }
    
    
    public List<PjRegionflow>  getList(PjRegionflow pjRegionflow){
    	return pjRegionFlowDao.getList(pjRegionflow);
    }

}
