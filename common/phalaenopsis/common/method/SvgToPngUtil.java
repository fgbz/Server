package phalaenopsis.common.method;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

//import org.apache.batik.transcoder.TranscoderException;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 *@Description: 将svg转换为png格式的图片
 */
public class SvgToPngUtil {

	/** 
     *@Description: 将svg字符串转换为png 
     *@Author: 
     *@param svgCode svg代码 
     *@param pngFilePath  保存的路径 
     *@throws IOException io异常
    */  
    public static void convertToPng(String svgCode,String pngFilePath) throws IOException{
  
        File file = new File (pngFilePath);  
  
        FileOutputStream outputStream = null;  
        try { 
        	if(file.exists()){
        		file.delete();
        	}
            file.createNewFile();  
            outputStream = new FileOutputStream (file);  
            convertToPng (svgCode, outputStream);  
        } finally {  
            if (outputStream != null) {  
                try {  
                    outputStream.close ();  
                } catch (IOException e) {  
                    e.printStackTrace ();  
                }  
            }  
        }  
    }  
       
    /** 
     *@Description: 将svgCode转换成png文件，直接输出到流中 
     *@param svgCode svg代码 
     *@param outputStream 输出流
     *@throws IOException io异常 
     */  
    public static void convertToPng(String svgCode,OutputStream outputStream) throws IOException{
        try {  
            byte[] bytes = svgCode.getBytes ("UTF-8");  
//            PNGTranscoder t = new PNGTranscoder ();
//            TranscoderInput input = new TranscoderInput (new ByteArrayInputStream (bytes));
//            TranscoderOutput output = new TranscoderOutput (outputStream);
//            t.transcode (input, output);
            outputStream.flush ();  
        } finally {  
            if (outputStream != null) {  
                try {  
                    outputStream.close ();  
                } catch (IOException e) {  
                    e.printStackTrace ();  
                }  
            }  
        }  
    }  
    /**
     * 单个文件下载功能
     * 
     * @param response
     * @param downloadFile
     * @param showFileName
     */
    public static void downloadFile(HttpServletResponse response,
            String downloadFile, String showFileName) {

        BufferedInputStream bis = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(downloadFile);
            String fileName = file.getName();
            is = new FileInputStream(file);
            os = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(os);

            fileName = java.net.URLEncoder.encode(showFileName, "UTF-8");
            fileName = new String(fileName.getBytes("UTF-8"), "GBK");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload");

            response.setHeader("Content-Disposition", "attachment; filename="
                    + fileName);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {

            try {
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != os) {
                    os.close();
                    os = null;
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
}