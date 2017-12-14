package phalaenopsis.common.method;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.cache.UserCache;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class Basis {

	public static final String coordinate = "coordinate";

	public static String getAuthId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getHeader("auth_id");
	}

	/**
	 * 获取当前Http请求的用户信息
	 * 
	 * @return
	 */
	public static final User getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		//Map map = request.getParameterMap();
		String ticket = request.getHeader("auth_id");
		if (null == ticket){
			ticket =  request.getParameter("AUTH_ID");
		}
		return (User) UserCache.get(ticket); // CacheUtils.getUserCache(ticket);
	}

	/**
	 * 获取Tomcat目录
	 * 
	 * @return
	 */
	public String getServerPath() {
		String path = this.getClass().getResource("/").getPath();
		path = path.replace("/Phalaenopsis/WEB-INF/classes/", ""); // 去掉后面字符串/Phalaenopsis/WEB-INF/classes/
		File file = new File(path);
		return path = file.getParent();
	}
	
	public String getUplodFolder(){
		String webInfPath = getServerPath();
		AppSettings appSettings = new AppSettings();
		String uploadFolder = webInfPath + appSettings.getUploadFolder();
		return uploadFolder;
	}

	/**
	 * 获取项目路径
	 * @return
	 */
	public String getProjectPath() {
		String path = this.getClass().getResource("/").getPath();
		path = path.replace("/WEB-INF/classes/", "");
		return path;
	}

	/**
	 * 获取保存shape的文件目录
	 * @return
	 */
	public String getShapFolder() {
		String projectPath = getProjectPath();
		File file = new File(projectPath);
		file = new File(projectPath, "shape");

		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getPath();
	}
	
	/**
	 * 获取上传坐标文件的路径
	 * @return
	 */
	public String getCoordiatePath(){
		AppSettings appSettings = new AppSettings();
		String filePath = getServerPath();
		File file = new File(filePath, appSettings.getUploadFolder());
		file = new File(file, coordinate);
		return file.getPath();
	}

	/**
	 * 删除文件夹下的文件
	 * @param path 文件路径
	 */
	public void deleteFolder(String path){
		File file = new File(path);
		if (file.exists() && file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();   //删除文件
			}
			file.delete();  // 删除文件夹
		}
		
		
	}


//	/**
//	 * 获取Http请求中将参数放在payload中的值 如果请求的方法带有参数带有@RequestBody，使用此方法得不到RequestBody中的值
//	 *
//	 * @return
//	 */
//	public String getRequestBody() {
//		String result = "";
//		try {
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//					.getRequest();
//			BufferedReader bufferedReader = new BufferedReader(
//					new InputStreamReader(request.getInputStream(), "utf-8"));
//			StringBuffer sBuffer = new StringBuffer("");
//			String temp;
//			while ((temp = bufferedReader.readLine()) != null) {
//				sBuffer.append(temp);
//			}
//			bufferedReader.close();
//			result = bufferedReader.toString();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}

}
