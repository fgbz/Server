package phalaenopsis.lawcase.entity.document;

import java.util.HashMap;

import phalaenopsis.common.method.Enum.IntEnum;

/** 
 文档类型
 
*/
public enum DocumentType implements IntEnum<DocumentType>
{
	/** 
	 责令停止违法行为通知书（简易停建书）
	 
	*/
	StopIrregularitiesNotice(1001),
	/** 
	 抄告单
	*/
	CopyOrder(1002),
	/** 
	 违法线索登记表 仅标记类型
	*/
	IllegalClues(1998),

	/** 
	 违法案件受理表 仅标记类型
	 
	*/
	Accept(1999),
	/** 
	 接受调查通知书
	 
	*/
	ReceiveSurveyNotice(2001),

	/** 
	 询问笔录（当事人笔录）
	 
	*/
	InterrogationRecord(2002),

	/** 
	 违法案件调查报告
	 
	*/
	SurveyReport(2003),

	/** 
	 证据先行登记保存通知书
	 
	*/
	EvidenceSaveNotice(2004),

	/** 
	 现场勘测 仅标记类型
	 
	*/
	SiteSurveyRecord(2998),

	/** 
	 延迟申请 仅标记类型
	 
	*/
	ApplyDelay(2999),

	/** 
	 违法案件审理记录
	 
	*/
	TrialRecord(3001),
	/** 
	 涉嫌犯罪案件移送书
	 
	*/
	SuspectedCriminal(4001),

	/** 
	 行政处分建议书
	 
	*/
	PunishAdvice(4002),

	/** 
	 行政处罚告知书
	 
	*/
	AdministrativePenaltyNotice(5001),

	/** 
	 听证告知书
	 
	*/
	HearingNotice(5002),

	/** 
	 行政处罚决定书
	 
	*/
	AdministrativePenaltyDecisionBook(6001),

	/** 
	 非法财物移交书
	 
	*/
	IllegalityPropertyTransferBook(7001),

	/** 
	 履行行政处罚决定催告函
	 
	*/
	ExigentNotice(7002),

	/** 
	 强制执行申请书
	 
	*/
	EnforceApplicationForm(7003),

	/** 
	 土地违法违规处理移送书
	 
	*/
	HandleTransferBook(7004),

	/** 
	 行政处罚决定执行记录
	 
	*/
	PunishExecutionRecord(7005),

	/** 
	 立案
	 
	*/
	Build(8001),

	/** 
	 中止
	 
	*/
	Suspend(8002),

	/** 
	 终止
	 
	*/
	Stop(8003),

	/** 
	 处理决定
	 
	*/
	Deal(8004),

	/** 
	 撤案
	 
	*/
	Drop(8005),

	/** 
	 结案
	 
	*/
	EndCase(8006),

	/** 
	 回证
	 
	*/
	Receipt(8007);
	private int intValue;
	private static HashMap<Integer, DocumentType> mappings;
	private synchronized static HashMap<Integer, DocumentType> getMappings()
	{
		if (mappings == null)
		{
			mappings = new HashMap<Integer, DocumentType>();
		}
		return mappings;
	}
	private DocumentType(int value)
	{
		intValue = value;
		DocumentType.getMappings().put(value, this);
	}
	public int getValue()
	{
		return intValue;
	}
	public static DocumentType forValue(int value)
	{
		return getMappings().get(value);
	}
	@Override
	public int getIntValue() {
		return intValue;
	}
}