package phalaenopsis.common.method.Attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class Multipart {

//	@Autowired
//	private HttpServletRequest request;

	@SuppressWarnings("null")
	public MultipartFile getUploadFile(HttpServletRequest request) {

		// 解析器解析request的上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		MultipartFile multifile = null;
		FileInputStream inputStream = null;

		// 先判断request中是否包涵multipart类型的数据
		if (multipartResolver.isMultipart(request)) {
			// 再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				multifile = multiRequest.getFile(name);
			}
		}



		return multifile;
	}

//	public boolean saveFile(InputStream inputStream, String fileName) {
//
//		byte[] bs = new byte[1024]; // 1K的数据缓冲
//		int len; // 读取到的数据长度
//
//		OutputStream os = null;
//
//		// 保存上传文件
//		File file = new File(fileName);
//		try {
//
//			os = new FileOutputStream(file.getPath() + File.separator + fileName);
//			// 开始读取
//			while ((len = inputStream.read(bs)) != -1) {
//				os.write(bs, 0, len);
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				os.close();
//				inputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return true;
//	}

}
