package phalaenopsis.lawcase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.lawcase.service.LawCaseService;

@Controller		 
public class LawCaseSignController {
	
	@Autowired
	private LawCaseService lawCaseService;

	 /**
	  * 
	  * @param currIDs
	  * @param lastIDs
	  * @param reason
	  * @return
	  * @desc 退回签字流程
	  */
	 @RequestMapping(value = "/SendBackSignature",method = RequestMethod.POST) 
    @ResponseBody
	 public boolean sendBackSignature(List<String> currIDs, List<String> lastIDs, String reason){
		return lawCaseService.sendBackSignature(currIDs,lastIDs,reason);
    }
	 
	 /**
	  * 
	  * @param sign
	  * @return
	  * @desc 保存签字信息
	  */
	 @RequestMapping(value = "/SaveSignature",method = RequestMethod.POST) 
     @ResponseBody
	 public String saveSignature(@RequestBody phalaenopsis.lawcase.entity.Signature sign) {
		return lawCaseService.saveSignatureByEndCase(sign);
     }
	 
	 /**
	  * 提交签字流程
	  * @param sign
	  * @param signLinkID
	  * @param nextNode
	  * @return
	  */
	 @RequestMapping(value = "/SubmitSignature",method = RequestMethod.POST) 
     @ResponseBody
	 public List<phalaenopsis.lawcase.entity.Signature> submitSignature(phalaenopsis.lawcase.entity.Signature sign, String signLinkID, String nextNode){
		return lawCaseService.submitSignature(sign,signLinkID,nextNode);

     }
	 
	 
	 
}
