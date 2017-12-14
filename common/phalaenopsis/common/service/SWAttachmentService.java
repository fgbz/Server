package phalaenopsis.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.IAttachmentDao;
import phalaenopsis.common.dao.ISWAttachmentDao;

@Service("swattachmentService")
public class SWAttachmentService extends AttachmentService {

	// @Resource(name ="swattachmentDao")
	// private ISWAttachmentDao swDao;

	@Override
	@Resource(name = "swattachmentDao")
	public void setDao(IAttachmentDao dao) {
		super.dao = dao;
	}
}
