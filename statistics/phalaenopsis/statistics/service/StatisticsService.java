package phalaenopsis.statistics.service;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.batik.transcoder.TranscoderException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;

import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.SvgToPngUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.util.QueryUtil;
import phalaenopsis.statistics.entity.ContrastYear;
import phalaenopsis.statistics.entity.CountyLawCaseEnd;
import phalaenopsis.statistics.entity.IllegalCaseInvestigation;
import phalaenopsis.statistics.entity.LandCaseEvaluation;
import phalaenopsis.statistics.entity.LandCasePunish;
import phalaenopsis.statistics.entity.MineralCaseEvaluation;
import phalaenopsis.statistics.entity.MineralCasePunish;
import phalaenopsis.statistics.entity.MinistryPhoneNum;
import phalaenopsis.statistics.entity.PatroAndReport;
import phalaenopsis.statistics.entity.ProviceIllegalLand;
import phalaenopsis.statistics.entity.ProvinceLandLawCase;
import phalaenopsis.statistics.entity.ProvinceLawCaseEnd;
import phalaenopsis.statistics.entity.ProvinceLetter;
import phalaenopsis.statistics.entity.ReportAndillegal;
import phalaenopsis.statistics.entity.SatelliteAndReport;
import phalaenopsis.statistics.enums.ExportEnum;

@Service
public class StatisticsService extends Basis {
	private String docPath;

	public StatisticsService() {
		super();
		String path = this.getClass().getResource("/").getPath();
		path = path.replace("/Server.Java/WEB-INF/classes/", ""); // 去掉后面字符串/Phalaenopsis/WEB-INF/classes/
		File file = new File(path);
		this.docPath=file.getPath();
//		this.docPath =this.getClass().getResource("/phalaenopsis/statistics").getPath();
//		if (-1 != docPath.indexOf("/")) {
//			docPath = docPath.substring(1);
//		}
	}
	/**
	 * 获取土地违法案件及查处情况统计
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LandCaseEvaluation> getLandCaseEvaluationList(Map map) {
		List<LandCaseEvaluation> list = new ArrayList<LandCaseEvaluation>();
		return list;
	}
	/**
	 * 省级信访情况
	 * @param queryUtil 参数
	 * @return
	 */
	public List<ProvinceLetter> getProvinceLetterState(QueryUtil queryUtil){
		List<ProvinceLetter> provinceLetters=new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			ProvinceLetter letter=new ProvinceLetter();
			Calendar a=Calendar.getInstance();
			String sdate=a.get(Calendar.YEAR)+"年"+(i+1)+"月";
			letter.setDate(sdate);
			letter.setLetter(i*2+1);
			letter.setTimer((i+1)*2);
			letter.setVister((i+2)*2);
			provinceLetters.add(letter);
		}
		return provinceLetters;
	}
	/**
	 * 部省12336受理违法举报电话线索量
	 * @return
	 */
	public List<MinistryPhoneNum> getMinistryPhoneNum(QueryUtil queryUtil){
		List<MinistryPhoneNum> phoneNums=new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			MinistryPhoneNum phoneNum=new MinistryPhoneNum();
			Calendar a=Calendar.getInstance();
			String sdate=a.get(Calendar.YEAR)+"年"+(i+1)+"月";
			phoneNum.setDate(sdate);
			phoneNum.setMinistryNum((i+1)*1);
			phoneNum.setProvinceNum((i+1)*2);
			phoneNums.add(phoneNum);
		}
		return phoneNums;
	}
	/**
	 * 全省发现土地违法案件数及面积
	 * @return
	 */
	public List<ProvinceLandLawCase> getProvinceLandLawCase(QueryUtil queryUtil){
		List<ProvinceLandLawCase> list=new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			ProvinceLandLawCase landLawCase=new ProvinceLandLawCase();
			Calendar a=Calendar.getInstance();
			String sdate=a.get(Calendar.YEAR)+"年"+(i+1)+"月";
			landLawCase.setDate(sdate);
			landLawCase.setIllegalArea((i+1)*3);
			landLawCase.setIllegalCount((i+1)*4);
			list.add(landLawCase);
		}
		return list;
	}
	/**
	 * 全省份用途违法用地面积比例
	 * @param queryUtil
	 * @return
	 */
	public List<ProviceIllegalLand> getProvinceReportArea(QueryUtil queryUtil){
		List<ProviceIllegalLand> list=new ArrayList<>();
		for(int i=0;i<12;i++){
			ProviceIllegalLand illegalLand=new ProviceIllegalLand();
			    if(i==0){
			    	illegalLand.setLandType("商业用地");
			    }else if(i==1){
			    	illegalLand.setLandType("工业用地");
			    }else if(i==2){
			    	illegalLand.setLandType("采矿用地");
			    }else if(i==3){
			    	illegalLand.setLandType("城镇住宅用地");
			    }else if(i==4){
			    	illegalLand.setLandType("特殊用地");
			    }else if(i==5){
			    	illegalLand.setLandType("机场用地");
			    }else if(i==6){
			    	illegalLand.setLandType("公路用地");
			    }else if(i==7){
			    	illegalLand.setLandType("科教用地");
			    }else if(i==8){
			    	illegalLand.setLandType("住宅用地");
			    }else if(i==9){
			    	illegalLand.setLandType("其它土地");
			    }else if(i==10){
			    	illegalLand.setLandType("交通运输用地");
			    }else if(i==11){
			    	illegalLand.setLandType("街巷用地");
			    }
				illegalLand.setArea((i+1)*2);
				list.add(illegalLand);
		}
		return list;
	}
	/**
	 * 全省本年立案结案情况
	 * @return
	 */
	public List<ProvinceLawCaseEnd> getProvinceLawCaseEnd(QueryUtil queryUtil){
		User currentUser = getCurrentUser();
		List<ProvinceLawCaseEnd> list = new ArrayList<ProvinceLawCaseEnd>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				ProvinceLawCaseEnd plc = new ProvinceLawCaseEnd();
				plc.setCountyName(currentUser.getRegionList().get(i).getRegionName());
				plc.setBuildArea((double) (i+1)*2);
				plc.setBuildCount((i+2)*3);
				plc.setEndArea((double) (i+2)*1);
				plc.setEndCount((i+4)*1);
				list.add(plc);
			}
		}
		return list;
	}
	/**
	 * 市结案情况以及比例
	 * @param queryUtil
	 * @return
	 */
	public List<CountyLawCaseEnd> getCountyLawCaseEnd(QueryUtil queryUtil) {
		User currentUser = getCurrentUser();
		List<CountyLawCaseEnd> list = new ArrayList<CountyLawCaseEnd>();
		if (currentUser.getRegionList().size() > 0) {
			for (int i = 0; i < currentUser.getRegionList().size(); i++) {
				CountyLawCaseEnd clc = new CountyLawCaseEnd();
				clc.setCountryName(currentUser.getRegionList().get(i).getRegionName());
				clc.setEndCaseSum((i+2)*3);
				list.add(clc);
			}
		}
		return list;
	}
	/**
	 * 获取土地违法案件处分情况统计
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LandCasePunish> getLandCasePunishList(QueryUtil query) {
		List<LandCasePunish> list = new ArrayList<LandCasePunish>();
		for(int i=0;i<21;i++){
			LandCasePunish lcp=new LandCasePunish();
			if(i==0){
				lcp.setTypeName("行政处分建议");
				lcp.setType(1);
			}else if(i==1){
				lcp.setTypeName("行政处分");
				lcp.setType(1);
			}else if(i==2){
				lcp.setTypeName("警告");
				lcp.setType(2);
			}else if(i==3){
				lcp.setTypeName("记过");
				lcp.setType(2);
			}else if(i==4){
				lcp.setTypeName("记大过");
				lcp.setType(2);
			}else if(i==5){
				lcp.setTypeName("降级");
				lcp.setType(2);
			}else if(i==6){
				lcp.setTypeName("撤职");
				lcp.setType(2);
			}else if(i==7){
				lcp.setTypeName("开除");
				lcp.setType(2);
			}else if(i==8){
				lcp.setTypeName("党纪处分建议");
				lcp.setType(1);
			}else if(i==9){
				lcp.setTypeName("党纪处分");
				lcp.setType(1);
			}else if(i==10){
				lcp.setTypeName("警告");
				lcp.setType(2);
			}else if(i==11){
				lcp.setTypeName("严重警告");
				lcp.setType(2);
			}else if(i==12){
				lcp.setTypeName("撤销党内职务");
				lcp.setType(2);
			}else if(i==13){
				lcp.setTypeName("留党察看");
				lcp.setType(2);
			}else if(i==14){
				lcp.setTypeName("开除党籍");
				lcp.setType(2);
			}else if(i==15){
				lcp.setTypeName("刑事案件移送");
				lcp.setType(1);
			}else if(i==16){
				lcp.setTypeName("刑事处罚");
				lcp.setType(1);
			}else if(i==17){
				lcp.setTypeName("非法转让、倒卖土地使用权罪");
				lcp.setType(2);
			}else if(i==18){
				lcp.setTypeName("非法占用耕地罪");
				lcp.setType(2);
			}else if(i==19){
				lcp.setTypeName("非法批准征用、占用土地罪");
				lcp.setType(2);
			}else if(i==20){
				lcp.setTypeName("非法低价出让国有土地使用权罪");
				lcp.setType(2);
			}
			lcp.setCount((i+1)*8);
			lcp.setCity((i+1)*4);
			lcp.setProvince((i+1)*6);
			lcp.setCounty((i+1)*4);
			lcp.setVillage((i+1)*3);
			lcp.setLeadOffice((i+1)*5);
			lcp.setLeadPart((i+1)*4);
			lcp.setLeadDivision((i+1)*2);
			lcp.setOtherOffice((i+1)*3);
			lcp.setOtherPart((i+1)*4);
			lcp.setOtherPerson((i+1)*5);
			lcp.setCompany((i+2)*2);
			lcp.setVillageCadre((i+3)*3);
			lcp.setOtherPerson((i+2)*3);
			list.add(lcp);
		}
		return list;
	}

	/**
	 * 获取矿产违法案件及查处情况统计
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<MineralCaseEvaluation> getMineralCaseEvaluationList(QueryUtil query) {
		List<MineralCaseEvaluation> list = new ArrayList<MineralCaseEvaluation>();
		for(int i=0;i<39;i++){
			MineralCaseEvaluation mce=new MineralCaseEvaluation();
			if(i==0){
				mce.setType(1);
				mce.setTypeName("");
			}else if(i==1){
				mce.setType(1);
				mce.setTypeName("一、上年未结案件");
			}else if(i==2){
				mce.setType(1);
				mce.setTypeName("二、本年立案");
			}else if(i==3){
				mce.setType(2);
				mce.setTypeName("勘查");
			}else if(i==4){
				mce.setType(3);
				mce.setTypeName("无证勘查");
			}else if(i==5){
				mce.setType(3);
				mce.setTypeName("越界勘查");
			}else if(i==6){
				mce.setType(3);
				mce.setTypeName("非法转让探矿权");
			}else if(i==7){
				mce.setType(3);
				mce.setTypeName("其他");
			}else if(i==8){
				mce.setType(2);
				mce.setTypeName("开采");
			}else if(i==9){
				mce.setType(3);
				mce.setTypeName("无证开采");
			}else if(i==10){
				mce.setType(3);
				mce.setTypeName("越界开采");
			}else if(i==11){
				mce.setType(3);
				mce.setTypeName("非法转让采矿权");
			}else if(i==12){
				mce.setType(3);
				mce.setTypeName("破坏性开采");
			}else if(i==13){
				mce.setType(3);
				mce.setTypeName("其他");
			}else if(i==14){
				mce.setType(2);
				mce.setTypeName("不按规定缴纳矿产资源补偿费");
			}else if(i==15){
				mce.setType(2);
				mce.setTypeName("非法批准");
			}else if(i==16){
				mce.setType(3);
				mce.setTypeName("违法发证");
			}else if(i==17){
				mce.setType(4);
				mce.setTypeName("勘查许可证");
			}else if(i==18){
				mce.setType(4);
				mce.setTypeName("采矿许可证");
			}else if(i==19){
				mce.setType(3);
				mce.setTypeName("其他");//.............
			}else if(i==20){
				mce.setType(2);
				mce.setTypeName("处理上年未结案");
			}else if(i==21){
				mce.setType(2);
				mce.setTypeName("勘查");
			}else if(i==22){
				mce.setType(3);
				mce.setTypeName("无证勘查");
			}else if(i==23){
				mce.setType(3);
				mce.setTypeName("越界勘查");
			}else if(i==24){
				mce.setType(3);
				mce.setTypeName("非法转让探矿权");
			}else if(i==25){
				mce.setType(3);
				mce.setTypeName("其他");
			}else if(i==26){
				mce.setType(2);
				mce.setTypeName("开采");
			}else if(i==27){
				mce.setType(3);
				mce.setTypeName("无证开采");
			}else if(i==28){
				mce.setType(3);
				mce.setTypeName("越界开采");
			}else if(i==29){
				mce.setType(3);
				mce.setTypeName("非法转让采矿权");
			}else if(i==30){
				mce.setType(3);
				mce.setTypeName("破坏性开采");
			}else if(i==31){
				mce.setType(3);
				mce.setTypeName("其他");
			}else if(i==32){
				mce.setType(2);
				mce.setTypeName("不按规定缴纳矿产资源补偿费");
			}else if(i==33){
				mce.setType(2);
				mce.setTypeName("非法批准");
			}else if(i==34){
				mce.setType(3);
				mce.setTypeName("违法发证");
			}else if(i==35){
				mce.setType(4);
				mce.setTypeName("勘查许可证");
			}else if(i==36){
				mce.setType(4);
				mce.setTypeName("采矿许可证");
			}else if(i==37){
				mce.setType(3);
				mce.setTypeName("其他");
			}else if(i==38){
				mce.setType(1);
				mce.setTypeName("四、本年未结案件");
			}
			mce.setCount((i+1)*8);
			if((i>=3 && i<=14) ||(i>=21 && i<=32)){
				mce.setCompany((i+1)*3);
				mce.setForeignCompany((i+1)*4);
				mce.setGroup((i+1)*2);
				mce.setVillage((i+1)*5);
				mce.setPersonal((i+2)*3);
			}else if((i>=15 && i<=19) || (i>=33 && i<=37)){
				mce.setStateOrgans((i+1)*6);
				mce.setProvince((i+2)*2);
				mce.setCity((i+3)*2);
				mce.setCounty((i+4)*2);
			}else{
				mce.setStateOrgans((i+1)*6);
				mce.setProvince((i+2)*2);
				mce.setCity((i+3)*2);
				mce.setCounty((i+4)*2);
				mce.setCompany((i+1)*3);
				mce.setForeignCompany((i+1)*4);
				mce.setGroup((i+1)*2);
				mce.setVillage((i+1)*5);
				mce.setPersonal((i+2)*3);
			}
			list.add(mce);
		}
		return list;
	}

	/**
	 * 获取矿产违法案件处分情况统计   
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> getMineralCasePunishList(QueryUtil query) {
		List<MineralCasePunish> administrations = new ArrayList<MineralCasePunish>();
		for(int i=0;i<16;i++){
			MineralCasePunish mcp=new MineralCasePunish();
			if(i==0){
				mcp.setType(1);
				mcp.setTypeName("行政处分建议");
			}else if(i==2){
				mcp.setType(1);
				mcp.setTypeName("行政处分");
			}else if(i==3){
				mcp.setType(2);
				mcp.setTypeName("警告");
			}else if(i==4){
				mcp.setType(2);
				mcp.setTypeName("记过");
			}else if(i==5){
				mcp.setType(2);
				mcp.setTypeName("记大过");
			}else if(i==6){
				mcp.setType(2);
				mcp.setTypeName("降级");
			}else if(i==7){
				mcp.setType(2);
				mcp.setTypeName("撤职");
			}else if(i==8){
				mcp.setType(2);
				mcp.setTypeName("开除");
			}else if(i==9){
				mcp.setType(1);
				mcp.setTypeName("党纪处分建议");
			}else if(i==10){
				mcp.setType(1);
				mcp.setTypeName("党纪处分");
			}else if(i==11){
				mcp.setType(2);
				mcp.setTypeName("警告");
			}else if(i==12){
				mcp.setType(2);
				mcp.setTypeName("严重警告");
			}else if(i==13){
				mcp.setType(2);
				mcp.setTypeName("撤销党内职务");
			}else if(i==14){
				mcp.setType(2);
				mcp.setTypeName("留党察看");
			}else if(i==15){
				mcp.setType(2);
				mcp.setTypeName("开除党籍");
			} 
			mcp.setCount((i+2)*3);
			mcp.setLeadOffice((i+3)*2);
			mcp.setLeadPart((i+4)*3);
			mcp.setLeadDivision((i+1)*5);
			mcp.setOtherOffice((i+2)*3);
			mcp.setOtherPart((i+5)*2);
			mcp.setOtherDivision((i+6)*2);
			administrations.add(mcp);
		}
		List<MineralCasePunish> penals = new ArrayList<MineralCasePunish>();
		for(int j=0;j<8;j++){
			MineralCasePunish mcpPenal=new MineralCasePunish();
			if(j==0){
				mcpPenal.setType(1);
				mcpPenal.setTypeName("刑事案件移送");
			}else if(j==1){
				mcpPenal.setType(1);
				mcpPenal.setTypeName("刑事处罚");
			}else if(j==2){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("非法采矿罪");
			}else if(j==3){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("破坏性采矿罪");
			}else if(j==4){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("滥用职权罪无徇私舞弊");
			}else if(j==5){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("滥用职权罪有徇私舞弊");
			}else if(j==6){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("玩忽职守罪无徇私舞弊");
			}else if(j==7){
				mcpPenal.setType(2);
				mcpPenal.setTypeName("玩忽职守罪有徇私舞弊");
			} 
			mcpPenal.setCount((j+2)*3);
			mcpPenal.setLeadOffice((j+3)*2);
			mcpPenal.setLeadPart((j+4)*3);
			mcpPenal.setLeadDivision((j+1)*5);
			mcpPenal.setOtherOffice((j+2)*3);
			mcpPenal.setOtherPart((j+5)*2);
			mcpPenal.setOtherDivision((j+6)*2);
			mcpPenal.setMiningPerson((j+3)*2);
			mcpPenal.setIndividualMining((j+1)*4);
			mcpPenal.setOtherPerson((j+4)*7);
			penals.add(mcpPenal);
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("administration", administrations);
		map.put("penal", penals);
		return map;
	}

	/**
	 * 获取土地违法发现、制止情况统计
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<LandCaseEvaluation> getLandCasePreventList(QueryUtil query) {
		List<LandCaseEvaluation> list = new ArrayList<LandCaseEvaluation>();
		for(int i=0;i<3;i++){
			LandCaseEvaluation lcel=new LandCaseEvaluation();
			if(i==0){
				lcel.setTypeName("");
			}else if(i==1){
				lcel.setTypeName("本年巡查发现违法");
			}else if(i==2){
				lcel.setTypeName("本年巡查制止违法");
			}
			lcel.setAllCount((i+8)*2);
			lcel.setAllLandArea((i+8)*3);
			lcel.setAllArableArea((i+8)*4);
			lcel.setProvinceCount((i+7)*2);
			lcel.setProvinceLandArea((i+7)*3);
			lcel.setProvinceArableArea((i+7)*4);
			lcel.setCityCount((i+6)*3);
			lcel.setCityLandArea((i+6)*2);
			lcel.setCityArableArea((i+6)*4);
			lcel.setCountyCount((i+5)*2);
			lcel.setCountyLandArea((i+5)*3);
			lcel.setCountyArableArea((i+5)*4);
			lcel.setVillageCount((i+4)*3);
			lcel.setVillageLandArea((i+4)*2);
			lcel.setVillageArableArea((i+4)*4);
			lcel.setGroupCount((i+3)*2);
			lcel.setGroupLandArea((i+3)*3);
			lcel.setGroupArableArea((i+3)*4);
			lcel.setCompanyCount((i+2)*4);
			lcel.setCompanyLandArea((i+2)*3);
			lcel.setCompanyArableArea((i+2)*2);
			lcel.setPersonalArableArea((i+1)*4);
			lcel.setPersonalCount((i+1)*5);
			lcel.setPersonalLandArea((i+1)*3);
			list.add(lcel);
		}
		return list;
	}
	/**
	 * 导出word

	 * @throws IOException 
	 * @throws DocumentException
	 */
	public void exportWord(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws DocumentException, IOException{
		createDocContext(request,response,map);
	}
	/**
	 * itext导出word

	 */
	public void createDocContext(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map) throws DocumentException,IOException {
		String file="";//文件存的位置
		String titleStr = "";//表头
		Integer titlelen=0;
		List<String> cells=new ArrayList<>();
		Table aTable = null;
	    //图片放入word
	      String svgs=map.get("svg").toString();
	      String svgCode = svgs.replaceAll("<", "<").replaceAll(">", ">");
	      String svg[] = svgCode.split("_");  
	      String path[] = new String[svg.length];  
	      List<String> imageList = new ArrayList<String>();//存图片的 
	      if (svg != null) {  
	            for (int k = 0; k < svg.length; k++) {  
	                String picName = UUID64.newUUID64().getValue() + ".png";
	                String imgPath=docPath+"/img/";
	                File imgfile=new File(imgPath);
	                if(!imgfile.exists()){
	                	imgfile.mkdir();
	                }
	                path[k] =imgPath+picName;  
	                imageList.add(path[k]);  
	                SvgToPngUtil.convertToPng(svg[k], path[k]);  
	            }  
	    } 
		Cell cc=new Cell();
		  // 设置纸张大小  
		if(map.get("type").equals(""+ExportEnum.ContrastYear.getValue()+"")){
			
			/*********17市分年度数据比对***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ContrastYear.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ContrastYear.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			ContrastYear[] cy=gson.fromJson(obj, ContrastYear[].class);
			List<ContrastYear> list=Arrays.asList(cy);
			ContrastYear contrastYear=new ContrastYear();
			titlelen=contrastYear.getClass().getDeclaredFields().length;
			cells.add("地市");
			cells.add("2015年度违法案件数");
			cells.add("2016年度违法案件数");
			cells.add("2015年度违法案件面积");
			cells.add("2016年度违法案件面积");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getRegionName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getMinYearCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getMaxYearCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getMinYearArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getMaxYearArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(100);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
				}
		}else if(map.get("type").equals(""+ExportEnum.SatelliteAndReport.getValue()+"")){
			
			/*********卫片数据与统计上报数据比对***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.SatelliteAndReport.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.SatelliteAndReport.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			SatelliteAndReport[] sar=gson.fromJson(obj, SatelliteAndReport[].class);
			List<SatelliteAndReport> list=Arrays.asList(sar);
			SatelliteAndReport satelliteAndReport=new SatelliteAndReport();
			titlelen=satelliteAndReport.getClass().getDeclaredFields().length;
			cells.add("地市");
			cells.add("卫片发现案件面积");
			cells.add("上报违法案件面积");
			cells.add("卫片发现案件件数");
			cells.add("上报违法案件件数");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getRegionName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getSatelliteArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getReportArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getSatelliteCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getReportCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(100);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
				}
		}else if(map.get("type").equals(""+ExportEnum.CountyLawCaseEnd.getValue()+"")){
			
			/*********市结案情况以及比例***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.CountyLawCaseEnd.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.CountyLawCaseEnd.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			CountyLawCaseEnd[] cce=gson.fromJson(obj, CountyLawCaseEnd[].class);
			List<CountyLawCaseEnd> list=Arrays.asList(cce);
			CountyLawCaseEnd caseEnd=new CountyLawCaseEnd();
			titlelen=caseEnd.getClass().getDeclaredFields().length;
			cells.add("市级");
			cells.add("结案数）");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getCountryName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getEndCaseSum())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(100);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
			  }
		}else if(map.get("type").equals(""+ExportEnum.MinistryPhoneNum.getValue()+"")){
			
			/*********部省12336受理违法举报电话线索量***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.MinistryPhoneNum.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.MinistryPhoneNum.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			MinistryPhoneNum[] mpn=gson.fromJson(obj, MinistryPhoneNum[].class);
			List<MinistryPhoneNum> list=Arrays.asList(mpn);
			MinistryPhoneNum phoneNum=new MinistryPhoneNum();
			titlelen=phoneNum.getClass().getDeclaredFields().length;
			cells.add("年月");
			cells.add("部转违法举报（件）");
			cells.add("省接违法举报（件）");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getDate()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getProvinceNum())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getMinistryNum())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(50);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
			  }
		}else if(map.get("type").equals(""+ExportEnum.PatroAndReport.getValue()+"")){
			
			/*********省级巡查与统计上报数据比对***************/
			String filePath=""+docPath+"wordExport/";
			String fileName=ExportEnum.PatroAndReport.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.PatroAndReport.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			PatroAndReport[] par=gson.fromJson(obj, PatroAndReport[].class);
			List<PatroAndReport> list=Arrays.asList(par);
			PatroAndReport patroAndReport=new PatroAndReport();
			titlelen=patroAndReport.getClass().getDeclaredFields().length;
			cells.add("地市");
			cells.add("巡查发现违法案件面积");
			cells.add("上报违法案件面积");
			cells.add("巡查发现违法案件件数");
			cells.add("上报违法案件件数");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getRegionName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getPatroCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getReportArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getPatroCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getReportCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(100);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
			  }
		}else if(map.get("type").equals(""+ExportEnum.ProviceIllegalLand.getValue()+"")){
			
			/*********全省份用途违法用地面积比例***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ProviceIllegalLand.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ProviceIllegalLand.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			ProviceIllegalLand[] pl=gson.fromJson(obj, ProviceIllegalLand[].class);
			List<ProviceIllegalLand> list=Arrays.asList(pl);
			ProviceIllegalLand proviceIllegalLand=new ProviceIllegalLand();
			titlelen=proviceIllegalLand.getClass().getDeclaredFields().length;
			cells.add("土地分类");
			cells.add("面积");
			aTable= new Table(titlelen);// 设置表格为几列
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
		    for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getLandType()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(50);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
			  }
		}else if(map.get("type").equals(""+ExportEnum.ProvinceLetter.getValue()+"")){
			
			/*********省信访导出***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ProvinceLetter.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ProvinceLetter.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			ProvinceLetter[] pl=gson.fromJson(obj, ProvinceLetter[].class);
			List<ProvinceLetter> list=Arrays.asList(pl);
			ProvinceLetter provinceLetter=new ProvinceLetter();
			titlelen=provinceLetter.getClass().getDeclaredFields().length;
			cells.add("年月");
			cells.add("来信（批次）");
			cells.add("来访（件）");
			cells.add("人次（人）");
			aTable= new Table(titlelen);// 设置表格为几列
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
		    for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getDate()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getLetter())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getVister())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getTimer())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(55);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
			  }
		}else if(map.get("type").equals(""+ExportEnum.ReportAndillegal.getValue()+"")){
			
			/*********17市统计上报潍坊案件数及违法用地面积数比对***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ReportAndillegal.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ReportAndillegal.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			ReportAndillegal[] ral=gson.fromJson(obj, ReportAndillegal[].class);
			List<ReportAndillegal> list=Arrays.asList(ral);
			ReportAndillegal reportAndillegal = new ReportAndillegal();
			titlelen=reportAndillegal.getClass().getDeclaredFields().length;
			cells.add("地市");
			cells.add("本年上报违法案件数");
			cells.add("违法用地面积");
			aTable= new Table(titlelen);// 设置表格为几列
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
		    for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getRegionName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getReportCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getIllegalArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(55);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
				}
		}else if(map.get("type").equals(""+ExportEnum.ProvinceLandLawCase.getValue()+"")){
			
			/*********全省发现土地违法案件数及面积***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ProvinceLandLawCase.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ProvinceLandLawCase.getName();
			String obj=map.get("list").toString();
			Gson gson=new Gson();
			ProvinceLandLawCase[] pcl=gson.fromJson(obj, ProvinceLandLawCase[].class);
			List<ProvinceLandLawCase> list=Arrays.asList(pcl);
			ProvinceLandLawCase provinceLandLawCase=new ProvinceLandLawCase();
			titlelen=provinceLandLawCase.getClass().getDeclaredFields().length;
			cells.add("年月");
			cells.add("本年发现违法件数");
			cells.add("本年发现违法面积（亩）");
			aTable= new Table(titlelen);// 设置表格为几列
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
		    for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getDate()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getIllegalCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getIllegalArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(55);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
				}
		}else{
			/*********全省本年立案结案情况***************/
			String filePath=""+docPath+"/wordExport/";
			String fileName=ExportEnum.ProvinceLawCaseEnd.getName()+".doc";
			file=filePath+fileName;
			File fila=new File(filePath);
			if(!fila.exists()){
				fila.mkdir();
			}
			fila = new File(filePath + fileName);
			if(fila.exists()){
				fila.delete();
			}
			fila.createNewFile();
			titleStr=ExportEnum.ProvinceLawCaseEnd.getName();
			String obj=map.get("list").toString();
			Gson gson = new Gson();
			ProvinceLawCaseEnd[] pro = gson.fromJson(obj, ProvinceLawCaseEnd[].class);
			List<ProvinceLawCaseEnd> list = Arrays.asList(pro);
			ProvinceLawCaseEnd provinceLawCaseEnd =new ProvinceLawCaseEnd();
			titlelen=provinceLawCaseEnd.getClass().getDeclaredFields().length;
			cells.add("地市");
			cells.add("立案案数");
			cells.add("结案数");
			cells.add("立案面积");
			cells.add("结案面积");
			aTable= new Table(titlelen);// 设置表格为几列  
			  //设置第一行表头
		    for(String cell:cells){
		    	cc=new Cell(new Phrase(cell));
		    	cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    }
		    //加列表数据
			for(int i=0;i<list.size();i++){
				cc=new Cell(new Phrase(list.get(i).getCountyName()));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	 aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getBuildCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getEndCount())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getBuildArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
		    	cc=new Cell(new Phrase(String.valueOf(list.get(i).getEndArea())));
				cc.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中   
		    	cc.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
		    	aTable.addCell(cc);
			  }
			  for(String  imgstr: imageList){
					cc = new Cell();
				    Image img = null; 
				  	img = Image.getInstance(imgstr);  
				  	img.setAbsolutePosition(0, 0);  
				    img.scaleAbsolute(180, 150);// 直接设定显示尺寸  
				    img.scalePercent(100);// 表示显示的大小为原尺寸的
	                img.setAlignment(Image.ALIGN_CENTER);//图片居右显示 
	                cc.setColspan(titlelen); 
	                cc.add(img);
	                aTable.addCell(cc);
				}
		   }
			Document document = new Document(PageSize.A4); 
		    // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中  
		    RtfWriter2.getInstance(document, new FileOutputStream(file));
		    document.open();  
		    // 设置中文字体  
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",  
		            "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
		    // 标题字体风格  
		    RtfFont titleFont = new RtfFont("宋体", 21, Font.BOLD, Color.BLACK);  
		    /* Font titleFont = new Font(bfChinese, 21, Font.BOLD); */  
		    // 正文字体风格  
		    Font contextFont = new Font(bfChinese, 12, Font.NORMAL);
		    Paragraph title = new Paragraph(titleStr);  
		    // 设置标题格式对齐方式  
		    title.setAlignment(Element.ALIGN_CENTER);  
		    title.setFont(titleFont);  
		    document.add(title);  
		      
		    // 设置 Table 表格  
		    int width[] =new int[titlelen];// 每列的占比例  
		    for(int i=0;i<titlelen;i++){
		    	width[i]=(100/titlelen);
		    }
		    aTable.setWidths(width);// 设置每列所占比例  
		    aTable.setWidth(100); // 占页面宽度 100%  
		    aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示  
		    aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示  
		    aTable.setAutoFillEmptyCells(true); // 自动填满  
		    aTable.setBorderWidth(1); // 边框宽度  
		    aTable.setBorderColor(Color.BLACK); // 边框颜色  
		    aTable.setPadding(18);// 衬距，看效果就知道什么意思了  
		    aTable.setSpacing(0);// 即单元格之间的间距  
		    aTable.setBorder(1);// 边框 
		    
		    document.add(aTable);
		    document.close();  
		    SvgToPngUtil.downloadFile(response, file,""+titleStr+".doc");
	}  
	/**
	 * 土地违法案件及查处情况统计表
	 * @return
	 */
	public List<IllegalCaseInvestigation> getIllegalCaseInvestigationList(QueryUtil query){
		List<IllegalCaseInvestigation> list=new ArrayList<>();
		for(int i=0;i<25;i++){
			IllegalCaseInvestigation investigation=new IllegalCaseInvestigation();
			if(i==0){
				investigation.setName("");
				investigation.setType(1);
			}else if(i==1){
				investigation.setName("一、上年未结案件");
				investigation.setType(1);
			}else if(i==2){
				investigation.setName("二、本年发现违法");
				investigation.setType(1);
			}else if(i==3){
				investigation.setName("本年发生");
				investigation.setType(1);
			}else if(i==4){
				investigation.setName("历年隐漏");
				investigation.setType(2);
			}else if(i==5){
				investigation.setName("三、本年立案");
				investigation.setType(2);
			}else if(i==6){
				investigation.setName("本年发生本年立案");
				investigation.setType(1);
			}else if(i==7){
				investigation.setName("买卖或非法转让");
				investigation.setType(3);
			}else if(i==8){
				investigation.setName("破坏耕地");
				investigation.setType(3);
			}else if(i==9){
				investigation.setName("非法占地");
				investigation.setType(3);
			}else if(i==10){
					investigation.setName("非法占地");
					investigation.setType(3);
			}else if(i==11){
				investigation.setName("低价出让土地");
				investigation.setType(3);
			}else if(i==12){
				investigation.setName("其他");
				investigation.setType(3);
			}else if(i==13){
				investigation.setName("历年隐漏案件立案");
				investigation.setType(2);
			}else if(i==14){
				investigation.setName("四、本年结案");
				investigation.setType(1);
			}else if(i==15){
				investigation.setName("处理本年发生案件");
				investigation.setType(2);
			}else if(i==16){
				investigation.setName("买卖或非法转让");
				investigation.setType(3);
			}else if(i==17){
				investigation.setName("破坏耕地");
				investigation.setType(3);
			}else if(i==18){
				investigation.setName("非法占地");
				investigation.setType(3);
			}else if(i==19){
				investigation.setName("非法批地");
				investigation.setType(3);
			}else if(i==20){
				investigation.setName("低价出让土地");
				investigation.setType(3);
			}else if(i==21){
				investigation.setName("其他");
				investigation.setType(3);
			}else if(i==22){
				investigation.setName("处理上年未结案");
				investigation.setType(2);
			}else if(i==23){
				investigation.setName("处理历年隐漏案");
				investigation.setType(2);
			}else if(i==24){
				investigation.setName("五、本年未结案件");
				investigation.setType(1);
			}
			investigation.setIllegalCaseTotalCount((i+8)*3);
			investigation.setIllegalCaseTotalLandArea((i+8)*4);
			investigation.setIllegalCaseTotalTillArea((i+8)*5);
			investigation.setIllegalCaseProvinceCount((i+7)*2);
			investigation.setIllegalCaseProvinceLandArea((i+7)*3);
			investigation.setIllegalCaseProvinceTillArea((i+7)*3);
			investigation.setIllegalCaseCityCount((i+6)*3);
			investigation.setIllegalCaseCityLandArea((i+6)*2);
			investigation.setIllegalCaseCityTillArea((i+6)*4);
			investigation.setIllegalCaseCountyCount((i+5)*2);
			investigation.setIllegalCaseCountyLandArea((i+5)*3);
			investigation.setIllegalCaseCountyTillArea((i+5)*4);
			investigation.setIllegalCaseVillageCount((i+4)*2);
			investigation.setIllegalCaseVillageLandArea((i+4)*3);
			investigation.setIllegalCaseVillageTillArea((i+4)*4);
			investigation.setIllegalCaseCountryCount((i+3)*2);
			investigation.setIllegalCaseCountryLandArea((i+3)*3);
			investigation.setIllegalCaseCountryTillArea((i+3)*4);
			investigation.setIllegalCaseCompanyCount((i+2)*2);
			investigation.setIllegalCaseCompanyLandArea((i+2)*3);
			investigation.setIllegalCaseCompanyTillArea((i+2)*4);
			investigation.setIllegalCasePersonalCount((i+1)*2);
			investigation.setIllegalCasePersonalLandArea((i+1)*3);
			investigation.setIllegalCasePersonalTillArea((i+1)*4);
			list.add(investigation);
		}
		return list;
	}
}
