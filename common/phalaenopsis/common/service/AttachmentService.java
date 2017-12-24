package phalaenopsis.common.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hdgf.streams.Stream;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.format.DataFormatDetector;
import org.springframework.beans.ExtendedBeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

import phalaenopsis.common.dao.IAttachmentDao;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.entity.Attachment.AttachmentSource;
import phalaenopsis.common.entity.Attachment.FileState;
import phalaenopsis.common.enums.IsDelete;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Attachment.Multipart;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.util.FileHelper;
import phalaenopsis.fgbz.common.FileConverter;
import phalaenopsis.fgbz.common.ItextpdfUtil;

@Service("attachmentService")
public class AttachmentService extends Basis {

	public IAttachmentDao dao;


	@Resource(name = "attachmentDao")
	public void setDao(IAttachmentDao dao) {
		this.dao = dao;
	}

	public AttachmentService() {

		AppSettings settings = new AppSettings();

		String webInfPath = getServerPath();
		String uploadFolder = webInfPath + settings.getUploadFolder();
		File file = new File(uploadFolder);
		if (!file.exists())
			file.mkdirs();

		for (int i = 0; i < 16; i++) {
			String path1 = String.format("%s%x", uploadFolder, i);
			file = new File(path1);
			if (!file.exists())
				file.mkdir();

			for (int j = 0; j < 16; j++) {
				String path2 = String.format("%s%x/%x/", uploadFolder, i, j);
				file = new File(path2);
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
	}

	public List<Attachment> getAttachments(String bizID) {
		List<Attachment> result = dao.getAttachments(bizID);

//        for (Attachment attachment : result) {
//            attachment.WGS84ToXian80();
//        }
//
//        calShift(result);
		return result;
	}


	public List<Attachment> getMoblieAttachments(String bizID, int i) {
		// TODO Auto-generated method stub
		return dao.getMoblieAttachments(bizID, i);
	}

	public boolean updateBizID(String fileID, String bizID) {
		return dao.updateBizID(fileID, bizID, null, null) == 1;
	}

	public boolean updateBizIDAndExtraInfo(String fileID, String bizID, String module, String exreaInfo) {
		return dao.updateBizID(fileID, bizID, module, exreaInfo) == 1;
	}

	public FileState upload(String fileName, String explain, HttpServletRequest request) {
		return save(fileName, explain, null, 0, 0, 0, request, false);
	}

	public FileState uploadWithNoThum(String userid, String module, HttpServletRequest request) {
		return saveWithNoThum(userid, module, request);
	}

	public FileState mobileUploadToServer(String fileName, String spotid, Double x, Double y, Double angle,
										  HttpServletRequest request) {
		if (null != fileName && StringUtils.hasLength(fileName)) {
			Attachment attachment = dao.getAttachmentByBizIdAndFileName(spotid, fileName.toLowerCase());
			if (null != attachment) {
				return new FileState(true, attachment.getId(), attachment.getUploadTime(), attachment.getFileName());
			} else {
				return save(fileName, null, spotid, x, y, angle, request, true);
			}
		}
		return save(fileName, null, spotid, x, y, angle, request, true);
	}

	public FileState mobileUploadToServer(String fileName, String spotid,String module, String x, String y, String angle,
										  HttpServletRequest request) {
		if (null != fileName && StringUtils.hasLength(fileName)) {
			Attachment attachment = dao.getAttachmentByBizIdAndFileName(spotid, fileName.toLowerCase());
			if (null != attachment) {
				return new FileState(true, attachment.getId(), attachment.getUploadTime(), attachment.getFileName());
			} else {
				return save(fileName, null, spotid, module, x, y, angle, request, true);
			}
		}
		return save(fileName, null, spotid, module, x, y, angle, request, true);
	}

	public void download(String fileID, HttpServletResponse response) {
		Attachment attachment = dao.getAttachmentById(fileID);
		if (null == attachment)
			return;
		String path = Attachment.GetFileStorageFolder(attachment.getActualFile()) + attachment.getActualFile();
		DownloadAttachment(path, attachment.getFileName(), response);
	}

	public void getPreView(String fileID,HttpServletResponse response) throws IOException {
//		Attachment attachment = dao.getAttachmentById(fileID);
//		if (null == attachment)
//			return null;
//		String path = Attachment.GetFileStorageFolder(attachment.getActualFile()) + attachment.getActualFile();
//		File file = new File(path);
//		OutputStream out = response.getOutputStream();
//		out.write(FileUtils.readFileToByteArray(file));
//		out.close();
//
//		return response.getOutputStream();
	}


	private void DownloadAttachment(String path, String fileName, HttpServletResponse response) {
		Assert.hasLength(path);

		File file = new File(path);

		if (!file.exists()) {
			return;
		}

		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			OutputStream out = response.getOutputStream();
			out.write(FileUtils.readFileToByteArray(file));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 下载图片缩略图
	 *
	 * @param fileID
	 *            文件ID
	 * @return
	 */
	public void downloadThumb(String fileID, HttpServletResponse response) {
		Attachment attachment = dao.getAttachmentById(fileID);
		if (null == attachment)
			return;

		String path = Attachment.GetFileStorageFolder(attachment.getActualFile()) + attachment.getThumbFile();
		String fileName = attachment.getFileName().split("\\.")[0] + ".thumb.jpg";
		DownloadAttachment(path, fileName, response);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(List<String> fileIDs) {
		List<Attachment> attachs = new ArrayList<Attachment>();
		for (String fileID : fileIDs) {
			Attachment file = dao.getAttachmentById(fileID);

			if (null == file)
				return false;
			attachs.add(file);
		}
		// 批量删除附件
		dao.batchUpdate(fileIDs);
		for (Attachment attachment : attachs) {
			deleteAttachment(attachment);
		}
		return true;
	}

	private void deleteAttachment(Attachment attachment) {
		if (null == attachment)
			return;
		File file = new File(Attachment.GetFileStorageFolder(attachment.getActualFile()) + attachment.getActualFile());
		file.deleteOnExit();

		// 删除缩略图（如果有）
//		file = new File(Attachment.GetFileStorageFolder(attachment.getActualFile()) + attachment.getThumbFile());
//		file.deleteOnExit();
	}

	private FileState saveWithNoThum(String userid, String module, HttpServletRequest request) {
		String guid = UUID.randomUUID().toString();

		// 得到上传文件
		Multipart multipart = new Multipart();
		MultipartFile multipartFile = multipart.getUploadFile(request);
		String fileName = null;
		if (null == multipartFile)
			return new FileState();
		try {
			fileName = new String(multipartFile.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
			String ext =FilenameUtils.getExtension(fileName); // fileName.split("\\.")[1];
			String storeFile = guid + "." + ext;

			// 保存文件
			String storageFolder = Attachment.GetFileStorageFolder(guid);
			File file = new File(storageFolder + storeFile);

			if (!file.exists())
				file.mkdirs();
			multipartFile.transferTo(file);

			Attachment attachment = new Attachment(guid, fileName, ext, multipartFile.getSize(), storeFile, userid,
					Calendar.getInstance().getTime(), module, AttachmentSource.Client);

			attachment.setPath(storageFolder);
			attachment.setInputuserid(userid);
			//获取pdf中的文字
			if(ext.equals("pdf")){

				PDDocument document=PDDocument.load(file);

				// 获取页码
				int pages = document.getNumberOfPages();

				// 读文本内容
				PDFTextStripper stripper=new PDFTextStripper();
				// 设置按顺序输出
				stripper.setSortByPosition(true);
				stripper.setStartPage(1);
				stripper.setEndPage(pages);
				String content = stripper.getText(document);
				attachment.setContent(content);
			}

			dao.saveFgbz(attachment);
			return new FileState(true, attachment.getId(), attachment.getUploadTime(), fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return new FileState(false, null, null, fileName);
		}

	}
	private boolean isDocFile(String extName) {
		boolean result = false;
		String fileExt = ".xls;.xlsx;.doc;.docx;.ppt;.pptx;";
		result = fileExt.contains(extName.toLowerCase());

		return result;
	}

	private boolean isPDFFile(String extName) {
		boolean result = false;
		String fileExt = ".pdf";
		result = fileExt.contains(extName.toLowerCase());

		return result;
	}

	private FileState save(String fileName, String explain, String bizID, double x, double y, double angle,
						   HttpServletRequest request, boolean IsMobile) {
		String guid = UUID.randomUUID().toString();

		// 得到上传文件
		Multipart part = new Multipart();
		MultipartFile multifile = part.getUploadFile(request);
		if (null == multifile) {
			return new FileState();
		}

		String ext = null;
		String storeFile = null;

		try {
			fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
			fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
			ext = FilenameUtils.getExtension(fileName); //fileName.split("\\.")[1];
			storeFile = guid + "." + ext;

			// 保存文件
			String storageFolder = Attachment.GetFileStorageFolder(guid);
			String fileSavePath = storageFolder + storeFile; // guid + "." +
			// ext;
			File file = new File(fileSavePath);

			if (!file.exists()) {
				file.mkdirs();
			}
			multifile.transferTo(file);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// boolean IsMobile = false;

		Attachment attachment = new Attachment(guid, fileName, ext, multifile.getSize(), storeFile, explain, bizID,
				Calendar.getInstance().getTime(), IsMobile ? AttachmentSource.Moblie : AttachmentSource.Client);
		attachment.setIsdelete(IsDelete.VALID.getIndex());// 有效
		if (IsMobile) {
			attachment.setX(x);
			attachment.setY(y);
			attachment.setAngle(angle);
		}

		if (!postProcess(attachment))
			return new FileState(false, attachment.getId(), attachment.getUploadTime(), fileName);

		dao.save(attachment);

		return new FileState(true, attachment.getId(), attachment.getUploadTime(), fileName);
	}

	private FileState save(String fileName, String explain, String bizID, String module, String x, String y, String angle,
						   HttpServletRequest request, boolean IsMobile) {
		String guid = UUID.randomUUID().toString();

		// 得到上传文件
		Multipart part = new Multipart();
		MultipartFile multifile = part.getUploadFile(request);
		if (null == multifile) {
			return new FileState();
		}

		String ext = null;
		String storeFile = null;

		try {
			fileName = StrUtil.isNullOrEmpty(fileName) ? multifile.getOriginalFilename() : fileName;
			fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
			ext = FilenameUtils.getExtension(fileName); //fileName.split("\\.")[1];
			storeFile = guid + "." + ext;

			// 保存文件
			String storageFolder = Attachment.GetFileStorageFolder(guid);
			String fileSavePath = storageFolder + storeFile; // guid + "." +
			// ext;
			File file = new File(fileSavePath);

			if (!file.exists()) {
				file.mkdirs();
			}
			multifile.transferTo(file);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// boolean IsMobile = false;

		Attachment attachment = new Attachment(guid, fileName, ext, multifile.getSize(), storeFile, bizID,
				Calendar.getInstance().getTime(), module, IsMobile ? AttachmentSource.Moblie : AttachmentSource.Client);
		attachment.setIsdelete(IsDelete.VALID.getIndex());// 有效
		if(fileName != null && !fileName.equals("")){
			SimpleDateFormat toDate=new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat toString=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				java.util.Date parse = toDate.parse(fileName);
				explain = toString.format(parse);
				System.out.println(explain);
				attachment.setExplain(explain);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (IsMobile) {
			if(x != null && y != null && !x.equals("") && !y.equals("")){
				Double X = null;
				Double Y = null;
				try {
					X = Double.parseDouble(x);
					Y = Double.parseDouble(y);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				attachment.setX(X);
				attachment.setY(Y);
			}
			if(angle != null && !angle.equals("")){
				Double Z = null;
				try {
					Z = Double.parseDouble(angle);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				attachment.setAngle(Z);
			}

		}

		if (!postProcess(attachment))
			return new FileState(false, attachment.getId(), attachment.getUploadTime(), fileName);

		dao.save(attachment);

		return new FileState(true, attachment.getId(), attachment.getUploadTime(), fileName);
	}

	private boolean checkFileName(String fileName) {
		if (StrUtil.isNullOrEmpty(fileName))
			return false;

		// 文件名中是否包含无效字符
		char[] invalidChars = { '\\', '/', ':', '*', '?', '<', '>', '|' }; // 无效字符串
		// \/:*?"<>|

		for (char c : invalidChars) {
			if (fileName.indexOf(c) > 0)
				return false;
		}

		// 文件名是否为标准XXX.XXX格式
		int dot = fileName.lastIndexOf('.');
		if (dot <= 0 || dot == fileName.length() - 1)
			return false;

		return true;
	}

	/**
	 * 文件上传成功的后续处理工作
	 *
	 * @param file
	 */
	private boolean postProcess(Attachment attachment) {
		String storageFolder = Attachment.GetFileStorageFolder(attachment.getActualFile());

		// 生成并保存缩略图
		if (FileHelper.isImage(attachment.getFileExt())) {
			String thumbFile = attachment.getActualFile().split("\\.")[0] + ".thumb.jpg"; // 保存的图片文件名
			String thumbPath = storageFolder + thumbFile; // 保存的图片路径

			if (!saveThumbPhoto(storageFolder + attachment.getActualFile(), 222, 152, thumbPath))
				return  false;
			attachment.setThumbFile(thumbFile);
		}
		return true;
	}

	/**
	 * 生成图片缩略图并保存
	 *
	 * @param fs
	 * @param maxWidth
	 *            缩略图最大宽度
	 * @param maxHeight
	 *            缩略图最大高度
	 * @param thumbPath
	 *            缩略图保存路径
	 */
	private boolean saveThumbPhoto(String filePath, int maxWidth, int maxHeight, String thumbPath) {

		try {

			// 获取原始图片大小
			BufferedImage bufferedImage = ImageIO.read(new File(filePath));
			if (null == bufferedImage)
				return  false;

			int srcWidth = bufferedImage.getWidth();
			int srcHeight = bufferedImage.getHeight();

			int thumbWidth = 0, thumbHeight = 0;
			// 计算得到缩略图的宽高
			if (srcWidth <= maxWidth && srcHeight <= maxHeight) {
				thumbWidth = (int) srcWidth;
				thumbHeight = (int) srcHeight;
			} else {
				// 这里要考虑到
				if (srcHeight > srcWidth && (srcWidth / srcHeight > maxWidth / maxHeight)) {
					thumbWidth = (int) maxWidth;
					thumbHeight = (int) ((srcHeight / srcWidth) * maxWidth);
				} else {
					thumbHeight = (int) maxHeight;
					thumbWidth = (int) ((srcWidth * maxHeight) / srcHeight);
				}
			}

			// 生成缩略图
			Thumbnails.of(filePath).size(thumbWidth, thumbHeight).toFile(thumbPath);
			return  true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return  true;

	}
}
