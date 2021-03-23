package phalaenopsis.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.apache.poi.hdgf.streams.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.entity.Attachment.FileState;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.service.AttachmentService;

/**
 * 附件服务
 *
 * @author chunl
 */
@Controller
@RequestMapping("/Foundation/Attachment")
public class AttachmentController {

    @Autowired
    private HttpServletRequest request;

    //@Resource(name="attachmentService")
    public AttachmentService service;

    @Resource(name = "attachmentService")
    public void setService(AttachmentService service) {
        this.service = service;
    }

    /**
     * 获取附件列表
     *
     * @param bizID 附件关联的业务数据ID
     * @return
     */
    @RequestMapping(value = "/GetAttachments", method = RequestMethod.GET)
    @ResponseBody
    public List<Attachment> getAttachments(String bizID) {
        if (StrUtil.isNullOrEmpty(bizID) || "undefined".equals(bizID))
            return null;
        return service.getAttachments(bizID);
    }

    /**
     * 获取附件列表
     *
     * @param bizID 附件关联的业务数据ID
     * @return
     */
    @RequestMapping(value = "/getMoblieAttachments", method = RequestMethod.GET)
    @ResponseBody
    public List<Attachment> getMoblieAttachments(@RequestParam("bizID") String bizID) {
        if (StrUtil.isNullOrEmpty(bizID) || "undefined".equals(bizID))
            return null;
        return service.getMoblieAttachments(bizID, 0);
    }

    /**
     * 更新附件关联的业务数据ID
     *
     * @param fileID 附件ID
     * @param bizID  附件关联的业务数据
     * @return
     */
    @RequestMapping(value = "/UpdateBizID", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateBizID(@RequestBody Map<String, String> map) {
        String fileID = map.get("fileID");
        String bizID = map.get("bizID");
        return service.updateBizID(fileID, bizID);
    }

    /**
     * 更新附件关联的业务数据ID
     *
     * @param fileID 附件ID
     * @param bizID  附件关联的业务数据
     * @return
     */
    @RequestMapping(value = "/UpdateBizIDAndExtraInfo", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateBizIDAndExtraInfo(@RequestBody Map<String, String> map) {
        String fileID = map.get("fileID");
        String bizID = map.get("bizID");
        String module = map.get("module");
        String exreaInfo = map.get("exreaInfo");
        return service.updateBizIDAndExtraInfo(fileID, bizID, module, exreaInfo);
    }

    /**
     * 上传附件 客户端 上传附件使用的接口服务
     *
     * @param fileName 文件名称
     * @param explain  说明
     * @return
     */
    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    @ResponseBody
    public FileState upload(HttpServletRequest request) {
        return service.upload("", "", request);
    }


    /**
     * 上传附件 不生成缩略图接口
     *
     * @param fileName 文件名称
     * @param explain  说明
     * @return
     */
    @RequestMapping(value = "/uploadWithNoThum", method = RequestMethod.POST)
    @ResponseBody
    public FileState uploadWithNoThum(HttpServletRequest request) {
        String userid = request.getParameter("userid");
        String module = request.getParameter("module");
        return service.uploadWithNoThum(userid, module, request);
    }


    /**
     * 上传附件 移动端 上传附件使用的接口服务
     *
     * @param fileName 文件名称
     * @param spotid
     * @param x
     * @param y
     * @param angle
     * @return
     */
    @RequestMapping(value = "/MobileUploadToServer", method = RequestMethod.POST)
    @ResponseBody
    public FileState mobileUploadToServer(@PathParam("fileName") String fileName,
                                          @PathParam("spotid") String spotid, @PathParam("x") Double x, @PathParam("y") Double y,
                                          @PathParam("angle") Double angle, HttpServletRequest request) {
        return service.mobileUploadToServer(fileName, spotid, x, y, angle, request);
    }

    /**
     * 上传附件 移动端 上传附件使用的接口服务WithModule
     *
     * @param fileName 文件名称
     * @param spotid
     * @param x
     * @param y
     * @param angle
     * @return
     */
    @RequestMapping(value = "/MobileUploadToServerWithModule", method = RequestMethod.POST)
    @ResponseBody
    public FileState MobileUploadToServerWithModule(@PathParam("fileName") String fileName,
                                                    @PathParam("spotid") String spotid, @PathParam("module") String module, @PathParam("x") String x, @PathParam("y") String y,
                                                    @PathParam("angle") String angle, HttpServletRequest request) {
        return service.mobileUploadToServer(fileName, spotid, module, x, y, angle, request);
    }

    /**
     * 下载附件
     *
     * @param fileID 文件ID
     * @return
     */
    @RequestMapping(value = "/Download", method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileID = request.getParameter("file");
        String module = request.getParameter("module");
        service.download(fileID, response, module);
    }

    /**
     * 预览
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getPreView", method = RequestMethod.GET)
    @ResponseBody
    public void getPreView(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileID = request.getParameter("file");
        service.getPreView(fileID, response);
    }


    /**
     * 下载图片缩略图
     *
     * @param fileID 文件ID
     * @return
     */
    @RequestMapping(value = "/DownloadThumb", method = RequestMethod.GET)
    @ResponseBody
    public void downloadThumb(HttpServletRequest request, HttpServletResponse response) {
        String fileID = request.getParameter("file");
        service.downloadThumb(fileID, response);
    }

    /**
     * 删除附件
     *
     * @param fileIDs 文件ID集合
     * @return
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(@RequestBody List<String> ids) {
        return service.delete(ids);
    }
}
