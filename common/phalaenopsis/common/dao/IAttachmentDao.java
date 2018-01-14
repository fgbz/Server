package phalaenopsis.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.Attachment.Attachment;

@Repository("attachmentDao")
public interface IAttachmentDao {

	/**
	 * 获取附件列表
	 * @param bizID
	 * @return
	 */
	List<Attachment> getAttachments(String bizID);

	List<Attachment> getAttachmentsHistroy(String bizID);

	
	/**
	 * 获取附件列表 倒叙
	 * @param bizID
	 * @return
	 */
	List<Attachment> getDescAttachments(String bizID);
	
	/**
	 * 更新附件关联的业务数据
	 * @param fileID
	 * @param bizID
	 * @return
	 */
	int updateBizID(@Param("fileID") String fileID, @Param("bizID") String bizID, @Param("module") String module, @Param("exreaInfo") String exreaInfo);

	/**
	 * 根据Id查询单个附件
	 * @param fileID
	 * @return
	 */
	Attachment getAttachmentById(String fileID);

	/**
	 * 根据传的Id删除附件
	 * @param fileID
	 */
	int delete(String fileID);

	/**
	 * 删除法规标准附件
	 * @param id
	 * @return
	 */
	int deleteFgbzfile(String id);
	/**
	 * 批量删除附件(逻辑删除)
	 * @param ids
	 * @return
	 */
	int batchUpdate(@Param("ids") List<String> ids);
	/**
	 * 保存附件
	 * @param attachment 附件
	 * @return
	 */
	int save(Attachment attachment);

	/**
	 * 保存法规附件
	 * @param attachment
	 * @return
	 */
	int saveFgbz(Attachment attachment);

	List<Attachment> getMoblieAttachments(@Param("bizID") String bizID, @Param("source") int i);

	Attachment getAttachmentByBizIdAndFileName(@Param("spotid") String spotid, @Param("fileName") String fileName);
	
	/**
	 * 通过业务id，删除业务下的所有附件
	 * @param bizID
	 * @return
	 */
	int deleteByBizID(@Param("bizId") String bizID);

	
}
