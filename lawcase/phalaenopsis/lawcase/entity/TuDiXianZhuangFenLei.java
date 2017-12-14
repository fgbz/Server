//package phalaenopsis.lawcase.entity;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import phalaenopsis.lawcase.utility.*;;
//
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
////[DataContract]
//public class TuDiXianZhuangFenLei
//{
//
//	public TuDiXianZhuangFenLei()
//	{
//
//	}
//
//	public TuDiXianZhuangFenLei(String datas)
//	{
//		if (!DotNetToJavaStringHelper.isNullOrEmpty(datas))
//		{
//			String[] arr = datas.split(",");
//			List<String> uList = new ArrayList<String>();
//			Collections.addAll(uList, arr);
//			java.util.HashMap<String, String> dic = new java.util.HashMap<String, String>();
//			for (int i = 0; i < uList.size(); i++)
//			{
//				dic.put(uList.get(i).toString(), uList.get(i).toString());
//			}
//			if (dic.containsKey("水田"))
//			{
//				this.setShuiTian(dic.get("水田"));
//			}
//			if (dic.containsKey("水浇地"))
//			{
//				this.setShuiJiaoDi(dic.get("水浇地"));
//			}
//			if (dic.containsKey("旱地"))
//			{
//				this.setHanDi(dic.get("旱地"));
//			}
//			if (dic.containsKey("果园"))
//			{
//				this.setGuoYuan(dic.get("果园"));
//			}
//			if (dic.containsKey("茶园"))
//			{
//				this.setChaYuan(dic.get("茶园"));
//			}
//			if (dic.containsKey("其他园地"))
//			{
//				this.setQiTaYuanDi(dic.get("其他园地"));
//			}
//			if (dic.containsKey("有林地"))
//			{
//				this.setYouLinDi(dic.get("有林地"));
//			}
//			if (dic.containsKey("灌木林地"))
//			{
//				this.setGuanMuLinDI(dic.get("灌木林地"));
//			}
//			if (dic.containsKey("其他林地"))
//			{
//				this.setQiTaLinDi(dic.get("其他林地"));
//			}
//			if (dic.containsKey("天然牧草地"))
//			{
//				this.setTianRanMuCaoDi(dic.get("天然牧草地"));
//			}
//			if (dic.containsKey("人工牧草地"))
//			{
//				this.setRenGongMuCaoDi(dic.get("人工牧草地"));
//			}
//			if (dic.containsKey("其他草地"))
//			{
//				this.setQiTaCaoDi(dic.get("其他草地"));
//			}
//			if (dic.containsKey("城市"))
//			{
//				this.setChengShi(dic.get("城市"));
//			}
//			if (dic.containsKey("建制镇"))
//			{
//				this.setJianZhiZhen(dic.get("建制镇"));
//			}
//			if (dic.containsKey("村庄"))
//			{
//				this.setCunZhuang(dic.get("村庄"));
//			}
//			if (dic.containsKey("采矿用地"))
//			{
//				this.setCaiKuangYongDi(dic.get("采矿用地"));
//			}
//			if (dic.containsKey("风景名胜及特殊用地"))
//			{
//				this.setFengJinTeShu(dic.get("风景名胜及特殊用地"));
//			}
//			if (dic.containsKey("铁路用地"))
//			{
//				this.setTieLuYongDi(dic.get("铁路用地"));
//			}
//			if (dic.containsKey("公路用地"))
//			{
//				this.setGongLuYongDi(dic.get("公路用地"));
//			}
//			if (dic.containsKey("农村道路"))
//			{
//				this.setNongCunDaoLu(dic.get("农村道路"));
//			}
//			if (dic.containsKey("机场用地"))
//			{
//				this.setJiChangYongDi(dic.get("机场用地"));
//			}
//			if (dic.containsKey("港口码头用地"))
//			{
//				this.setGangKouMaTouYongDi(dic.get("港口码头用地"));
//			}
//			if (dic.containsKey("管道运输用地"))
//			{
//				this.setGuanDaoYunShuYongDi(dic.get("管道运输用地"));
//			}
//			if (dic.containsKey("河流水面"))
//			{
//				this.setHeLiuShuiMian(dic.get("河流水面"));
//			}
//			if (dic.containsKey("湖泊水面"))
//			{
//				this.setHuPoShuiMian(dic.get("湖泊水面"));
//			}
//			if (dic.containsKey("水库水面"))
//			{
//				this.setShuiKuShuiMian(dic.get("水库水面"));
//			}
//			if (dic.containsKey("坑塘水面"))
//			{
//				this.setKengTangShuiMian(dic.get("坑塘水面"));
//			}
//			if (dic.containsKey("沿海滩涂"))
//			{
//				this.setYanHaiTanTu(dic.get("沿海滩涂"));
//			}
//			if (dic.containsKey("内陆滩涂"))
//			{
//				this.setNeiLuTanTu(dic.get("内陆滩涂"));
//			}
//			if (dic.containsKey("沟渠"))
//			{
//				this.setGouQu(dic.get("沟渠"));
//			}
//			if (dic.containsKey("水工建筑用地"))
//			{
//				this.setShuiGongJianZhuYongDi(dic.get("水工建筑用地"));
//			}
//			if (dic.containsKey("冰川及永久积雪"))
//			{
//				this.setBingChuanJiYongJiuJiXue(dic.get("冰川及永久积雪"));
//			}
//			if (dic.containsKey("设施农用地"))
//			{
//				this.setSheShiNongYongDi(dic.get("设施农用地"));
//			}
//			if (dic.containsKey("田坎"))
//			{
//				this.setTianKan(dic.get("田坎"));
//			}
//			if (dic.containsKey("盐碱地"))
//			{
//				this.setYanJianDi(dic.get("盐碱地"));
//			}
//			if (dic.containsKey("沼泽地"))
//			{
//				this.setZhaoZeDi(dic.get("沼泽地"));
//			}
//			if (dic.containsKey("沙地"))
//			{
//				this.setShaDi(dic.get("沙地"));
//			}
//			if (dic.containsKey("裸地"))
//			{
//				this.setLuoDi(dic.get("裸地"));
//			}
//
//			this.setArableLandArea((Double.parseDouble(this.getShuiTian()) + Double.parseDouble(this.getShuiJiaoDi()) + Double.parseDouble(this.getHanDi())).toString());
//			this.setYuanDi((Double.parseDouble(this.getGuoYuan()) + Double.parseDouble(this.getChaYuan()) + Double.parseDouble(this.getQiTaYuanDi())).toString());
//			this.setLinDi((Double.parseDouble(this.getYouLinDi()) + Double.parseDouble(this.getGuanMuLinDI()) + Double.parseDouble(this.getQiTaLinDi())).toString());
//			this.setCaoDi((Double.parseDouble(this.getTianRanMuCaoDi()) + Double.parseDouble(this.getRenGongMuCaoDi()) + Double.parseDouble(this.getQiTaCaoDi())).toString());
//			this.setChengZhenCun((Double.parseDouble(this.getChengShi()) + Double.parseDouble(this.getJianZhiZhen()) + Double.parseDouble(this.getCunZhuang()) + Double.parseDouble(this.getCaiKuangYongDi()) + Double.parseDouble(this.getFengJinTeShu())).toString());
//			this.setJiaoTongYunShu((Double.parseDouble(this.getTieLuYongDi()) + Double.parseDouble(this.getGongLuYongDi()) + Double.parseDouble(this.getNongCunDaoLu()) + Double.parseDouble(this.getJiChangYongDi()) + Double.parseDouble(this.getGangKouMaTouYongDi()) + Double.parseDouble(this.getGuanDaoYunShuYongDi())).toString());
//			this.setShuiYuShuiLi((Double.parseDouble(this.getHeLiuShuiMian()) + Double.parseDouble(this.getHuPoShuiMian()) + Double.parseDouble(this.getShuiKuShuiMian()) + Double.parseDouble(this.getKengTangShuiMian()) + Double.parseDouble(this.getYanHaiTanTu()) + Double.parseDouble(this.getNeiLuTanTu()) + Double.parseDouble(this.getGouQu()) + Double.parseDouble(this.getShuiGongJianZhuYongDi()) + Double.parseDouble(this.getBingChuanJiYongJiuJiXue())).toString());
//			this.setQiTaTuDi((Double.parseDouble(this.getSheShiNongYongDi()) + Double.parseDouble(this.getTianKan()) + Double.parseDouble(this.getYanJianDi()) + Double.parseDouble(this.getZhaoZeDi()) + Double.parseDouble(this.getShaDi()) + Double.parseDouble(this.getLuoDi())).toString());
//
//		}
//	}
//
//	/** 
//	 空格
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 0), DataMember]
//	private String privateFirstBlank;
//	public final String getFirstBlank()
//	{
//		return privateFirstBlank;
//	}
//	public final void setFirstBlank(String value)
//	{
//		privateFirstBlank = value;
//	}
//
//	/** 
//	 空格
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 1), DataMember]
//	private String privateSecondBlank;
//	public final String getSecondBlank()
//	{
//		return privateSecondBlank;
//	}
//	public final void setSecondBlank(String value)
//	{
//		privateSecondBlank = value;
//	}
//
//
//	/** 
//	 耕地
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 2), DataMember]
//	private String privateArableLandArea;
//	public final String getArableLandArea()
//	{
//		return privateArableLandArea;
//	}
//	public final void setArableLandArea(String value)
//	{
//		privateArableLandArea = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 3), DataMember]
//	private String privateShuiTian;
//	public final String getShuiTian()
//	{
//		return privateShuiTian;
//	}
//	public final void setShuiTian(String value)
//	{
//		privateShuiTian = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 4), DataMember]
//	private String privateShuiJiaoDi;
//	public final String getShuiJiaoDi()
//	{
//		return privateShuiJiaoDi;
//	}
//	public final void setShuiJiaoDi(String value)
//	{
//		privateShuiJiaoDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 5), DataMember]
//	private String privateHanDi;
//	public final String getHanDi()
//	{
//		return privateHanDi;
//	}
//	public final void setHanDi(String value)
//	{
//		privateHanDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 6), DataMember]
//	private String privateYuanDi;
//	public final String getYuanDi()
//	{
//		return privateYuanDi;
//	}
//	public final void setYuanDi(String value)
//	{
//		privateYuanDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 7), DataMember]
//	private String privateGuoYuan;
//	public final String getGuoYuan()
//	{
//		return privateGuoYuan;
//	}
//	public final void setGuoYuan(String value)
//	{
//		privateGuoYuan = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 8), DataMember]
//	private String privateChaYuan;
//	public final String getChaYuan()
//	{
//		return privateChaYuan;
//	}
//	public final void setChaYuan(String value)
//	{
//		privateChaYuan = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 9), DataMember]
//	private String privateQiTaYuanDi;
//	public final String getQiTaYuanDi()
//	{
//		return privateQiTaYuanDi;
//	}
//	public final void setQiTaYuanDi(String value)
//	{
//		privateQiTaYuanDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 10), DataMember]
//	private String privateLinDi;
//	public final String getLinDi()
//	{
//		return privateLinDi;
//	}
//	public final void setLinDi(String value)
//	{
//		privateLinDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 11), DataMember]
//	private String privateYouLinDi;
//	public final String getYouLinDi()
//	{
//		return privateYouLinDi;
//	}
//	public final void setYouLinDi(String value)
//	{
//		privateYouLinDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 12), DataMember]
//	private String privateGuanMuLinDI;
//	public final String getGuanMuLinDI()
//	{
//		return privateGuanMuLinDI;
//	}
//	public final void setGuanMuLinDI(String value)
//	{
//		privateGuanMuLinDI = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 13), DataMember]
//	private String privateQiTaLinDi;
//	public final String getQiTaLinDi()
//	{
//		return privateQiTaLinDi;
//	}
//	public final void setQiTaLinDi(String value)
//	{
//		privateQiTaLinDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 14), DataMember]
//	private String privateCaoDi;
//	public final String getCaoDi()
//	{
//		return privateCaoDi;
//	}
//	public final void setCaoDi(String value)
//	{
//		privateCaoDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 15), DataMember]
//	private String privateTianRanMuCaoDi;
//	public final String getTianRanMuCaoDi()
//	{
//		return privateTianRanMuCaoDi;
//	}
//	public final void setTianRanMuCaoDi(String value)
//	{
//		privateTianRanMuCaoDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 16), DataMember]
//	private String privateRenGongMuCaoDi;
//	public final String getRenGongMuCaoDi()
//	{
//		return privateRenGongMuCaoDi;
//	}
//	public final void setRenGongMuCaoDi(String value)
//	{
//		privateRenGongMuCaoDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 17), DataMember]
//	private String privateQiTaCaoDi;
//	public final String getQiTaCaoDi()
//	{
//		return privateQiTaCaoDi;
//	}
//	public final void setQiTaCaoDi(String value)
//	{
//		privateQiTaCaoDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 18), DataMember]
//	private String privateChengZhenCun;
//	public final String getChengZhenCun()
//	{
//		return privateChengZhenCun;
//	}
//	public final void setChengZhenCun(String value)
//	{
//		privateChengZhenCun = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 19), DataMember]
//	private String privateChengShi;
//	public final String getChengShi()
//	{
//		return privateChengShi;
//	}
//	public final void setChengShi(String value)
//	{
//		privateChengShi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 20), DataMember]
//	private String privateJianZhiZhen;
//	public final String getJianZhiZhen()
//	{
//		return privateJianZhiZhen;
//	}
//	public final void setJianZhiZhen(String value)
//	{
//		privateJianZhiZhen = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 21), DataMember]
//	private String privateCunZhuang;
//	public final String getCunZhuang()
//	{
//		return privateCunZhuang;
//	}
//	public final void setCunZhuang(String value)
//	{
//		privateCunZhuang = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 22), DataMember]
//	private String privateCaiKuangYongDi;
//	public final String getCaiKuangYongDi()
//	{
//		return privateCaiKuangYongDi;
//	}
//	public final void setCaiKuangYongDi(String value)
//	{
//		privateCaiKuangYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 23), DataMember]
//	private String privateFengJinTeShu;
//	public final String getFengJinTeShu()
//	{
//		return privateFengJinTeShu;
//	}
//	public final void setFengJinTeShu(String value)
//	{
//		privateFengJinTeShu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 24), DataMember]
//	private String privateJiaoTongYunShu;
//	public final String getJiaoTongYunShu()
//	{
//		return privateJiaoTongYunShu;
//	}
//	public final void setJiaoTongYunShu(String value)
//	{
//		privateJiaoTongYunShu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 25), DataMember]
//	private String privateTieLuYongDi;
//	public final String getTieLuYongDi()
//	{
//		return privateTieLuYongDi;
//	}
//	public final void setTieLuYongDi(String value)
//	{
//		privateTieLuYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 26), DataMember]
//	private String privateGongLuYongDi;
//	public final String getGongLuYongDi()
//	{
//		return privateGongLuYongDi;
//	}
//	public final void setGongLuYongDi(String value)
//	{
//		privateGongLuYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 27), DataMember]
//	private String privateNongCunDaoLu;
//	public final String getNongCunDaoLu()
//	{
//		return privateNongCunDaoLu;
//	}
//	public final void setNongCunDaoLu(String value)
//	{
//		privateNongCunDaoLu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 28), DataMember]
//	private String privateJiChangYongDi;
//	public final String getJiChangYongDi()
//	{
//		return privateJiChangYongDi;
//	}
//	public final void setJiChangYongDi(String value)
//	{
//		privateJiChangYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 29), DataMember]
//	private String privateGangKouMaTouYongDi;
//	public final String getGangKouMaTouYongDi()
//	{
//		return privateGangKouMaTouYongDi;
//	}
//	public final void setGangKouMaTouYongDi(String value)
//	{
//		privateGangKouMaTouYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 30), DataMember]
//	private String privateGuanDaoYunShuYongDi;
//	public final String getGuanDaoYunShuYongDi()
//	{
//		return privateGuanDaoYunShuYongDi;
//	}
//	public final void setGuanDaoYunShuYongDi(String value)
//	{
//		privateGuanDaoYunShuYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 31), DataMember]
//	private String privateShuiYuShuiLi;
//	public final String getShuiYuShuiLi()
//	{
//		return privateShuiYuShuiLi;
//	}
//	public final void setShuiYuShuiLi(String value)
//	{
//		privateShuiYuShuiLi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 32), DataMember]
//	private String privateHeLiuShuiMian;
//	public final String getHeLiuShuiMian()
//	{
//		return privateHeLiuShuiMian;
//	}
//	public final void setHeLiuShuiMian(String value)
//	{
//		privateHeLiuShuiMian = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 33), DataMember]
//	private String privateHuPoShuiMian;
//	public final String getHuPoShuiMian()
//	{
//		return privateHuPoShuiMian;
//	}
//	public final void setHuPoShuiMian(String value)
//	{
//		privateHuPoShuiMian = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 34), DataMember]
//	private String privateShuiKuShuiMian;
//	public final String getShuiKuShuiMian()
//	{
//		return privateShuiKuShuiMian;
//	}
//	public final void setShuiKuShuiMian(String value)
//	{
//		privateShuiKuShuiMian = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 35), DataMember]
//	private String privateKengTangShuiMian;
//	public final String getKengTangShuiMian()
//	{
//		return privateKengTangShuiMian;
//	}
//	public final void setKengTangShuiMian(String value)
//	{
//		privateKengTangShuiMian = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 36), DataMember]
//	private String privateYanHaiTanTu;
//	public final String getYanHaiTanTu()
//	{
//		return privateYanHaiTanTu;
//	}
//	public final void setYanHaiTanTu(String value)
//	{
//		privateYanHaiTanTu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 37), DataMember]
//	private String privateNeiLuTanTu;
//	public final String getNeiLuTanTu()
//	{
//		return privateNeiLuTanTu;
//	}
//	public final void setNeiLuTanTu(String value)
//	{
//		privateNeiLuTanTu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 38), DataMember]
//	private String privateGouQu;
//	public final String getGouQu()
//	{
//		return privateGouQu;
//	}
//	public final void setGouQu(String value)
//	{
//		privateGouQu = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 39), DataMember]
//	private String privateShuiGongJianZhuYongDi;
//	public final String getShuiGongJianZhuYongDi()
//	{
//		return privateShuiGongJianZhuYongDi;
//	}
//	public final void setShuiGongJianZhuYongDi(String value)
//	{
//		privateShuiGongJianZhuYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 40), DataMember]
//	private String privateBingChuanJiYongJiuJiXue;
//	public final String getBingChuanJiYongJiuJiXue()
//	{
//		return privateBingChuanJiYongJiuJiXue;
//	}
//	public final void setBingChuanJiYongJiuJiXue(String value)
//	{
//		privateBingChuanJiYongJiuJiXue = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 41), DataMember]
//	private String privateQiTaTuDi;
//	public final String getQiTaTuDi()
//	{
//		return privateQiTaTuDi;
//	}
//	public final void setQiTaTuDi(String value)
//	{
//		privateQiTaTuDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 42), DataMember]
//	private String privateSheShiNongYongDi;
//	public final String getSheShiNongYongDi()
//	{
//		return privateSheShiNongYongDi;
//	}
//	public final void setSheShiNongYongDi(String value)
//	{
//		privateSheShiNongYongDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 43), DataMember]
//	private String privateTianKan;
//	public final String getTianKan()
//	{
//		return privateTianKan;
//	}
//	public final void setTianKan(String value)
//	{
//		privateTianKan = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 44), DataMember]
//	private String privateYanJianDi;
//	public final String getYanJianDi()
//	{
//		return privateYanJianDi;
//	}
//	public final void setYanJianDi(String value)
//	{
//		privateYanJianDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 45), DataMember]
//	private String privateZhaoZeDi;
//	public final String getZhaoZeDi()
//	{
//		return privateZhaoZeDi;
//	}
//	public final void setZhaoZeDi(String value)
//	{
//		privateZhaoZeDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 46), DataMember]
//	private String privateShaDi;
//	public final String getShaDi()
//	{
//		return privateShaDi;
//	}
//	public final void setShaDi(String value)
//	{
//		privateShaDi = value;
//	}
//
//	/** 
//	 
//	 
//	*/
////C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//	//[ExcelColumn(Order = 47), DataMember]
//	private String privateLuoDi;
//	public final String getLuoDi()
//	{
//		return privateLuoDi;
//	}
//	public final void setLuoDi(String value)
//	{
//		privateLuoDi = value;
//	}
//
//}