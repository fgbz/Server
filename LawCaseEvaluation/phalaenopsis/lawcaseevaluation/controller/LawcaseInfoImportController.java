/**
 * Description 案件台账、评查人员导入服务类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.controller;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import phalaenopsis.lawcaseevaluation.common.ExcelHelper;
import phalaenopsis.lawcaseevaluation.entity.*;
import phalaenopsis.lawcaseevaluation.service.LawcaseInfoImportService;

@Controller
@RequestMapping("/LawcaseEvaluation")
public class LawcaseInfoImportController {

    private static Logger Log = LoggerFactory.getLogger(LawcaseInfoImportController.class);

    @Autowired
    private LawcaseInfoImportService lawcaseInfoImportService;
    /**
     * 导入案件台账
     * 
     * @return 0 失败，1 成功。
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/Import/LawcaseAccount", method = RequestMethod.POST)
    @ResponseBody
    public ImportResult importLawcaseAccount(HttpServletRequest request) {
    	//获取前端传过来的file
        MultipartFile file = getFile(request);
        FileInputStream inputStream = null;
        int result = 0;
        int year = Integer.parseInt(request.getParameter("year")) ;
        System.out.print(year);
        try {
            if (file != null) {
            	//转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                 inputStream = (FileInputStream) file.getInputStream();
                 //将导入的excel转化为实体
                List<LawcaseAccountExcel> list = ExcelHelper.convertToList(LawcaseAccountExcel.class, fileName, inputStream, 4, 10);
                //插入案件台账
                result= lawcaseInfoImportService.importLawcaseAccount(list, year);
                 inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //封装结果集，触发前端upload回调
        ImportResult importResult = new ImportResult();
        importResult.setCode(result);
        return importResult;
    }

    /**
     * 导入评查人员
     * 
     * @return 0 失败，1 成功。
     */
    @RequestMapping(value = "/Import/EvaluationPerson", method = RequestMethod.POST)
    @ResponseBody
    public ImportResult importEvaluationPerson(HttpServletRequest request) {
        int rowsNum = 2, columnsNum = 8;
      //获取前端传过来的file
        MultipartFile file = getFile(request);
        FileInputStream inputStream = null;
        int result = 0;
        try {
            if (file != null) {
            	//转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                //将导入的excel转化为实体
                List<ImportingEvaluationPerson> list = ExcelHelper
                        .convertToList(ImportingEvaluationPerson.class, fileName, inputStream, rowsNum, columnsNum);
                //插入评查人员
                result = lawcaseInfoImportService.importEvaluationPerson(list,-1,true);
                
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      //封装结果集，触发前端upload回调
        ImportResult importResult = new ImportResult();
        importResult.setCode(result);
        return importResult;
    }

    /**
     * 获取客户端上传的文件
     * 
     * @param request
     * @return
     */
    public MultipartFile getFile(HttpServletRequest request) {

        MultipartFile file = null;

        // 解析器解析request的上下文
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        // 先判断request中是否包涵multipart类型的数据
        if (multipartResolver.isMultipart(request)) {
            // 再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String name = (String) iter.next();
                // 根据name值拿取文件
                file = multiRequest.getFile(name);
            }

        }
        return file;

    }
}
