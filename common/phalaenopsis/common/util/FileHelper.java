package phalaenopsis.common.util;

public final class FileHelper {
	
	protected FileHelper(){
	}
	
	/**
	 * 判断文件是否为图片
	 * @param ext
	 * @return
	 */
	public static boolean isImage(String ext)
	{
		switch (ext.toLowerCase()) {
		case "jpg":
		case "jpeg":
		case "png":
		case "gif":
		case "bmp":
		case "tif":
			return true;
		default:
			return false;
		}
	}

}
