package phalaenopsis.common.controller;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import phalaenopsis.common.service.AttachmentService;

@Controller
@RequestMapping("/allweather/Attachment")
public class SWAttachmentController extends AttachmentController {

	@Resource(name = "swattachmentService")
	public void setService(AttachmentService service) {
		super.service = service;
	}
}
