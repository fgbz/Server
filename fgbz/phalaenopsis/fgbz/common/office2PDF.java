package phalaenopsis.fgbz.common;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.suwell.ofd.custom.agent.ConvertException;
import com.suwell.ofd.custom.agent.HTTPAgent;
import com.suwell.ofd.custom.wrapper.PackException;
import phalaenopsis.common.entity.AppSettings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class office2PDF {

    private static final Logger logger = LoggerFactory.getLogger(office2PDF.class);

    public static int office2PDF(String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;// 找不到源文件, 则返回-1
            }

            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            // //这里是OpenOffice的安装目录, 需要时启动
            String OpenOffice_HOME = new AppSettings().getOpenoffice();
            // // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
                OpenOffice_HOME += "\\";
            }
            // // 启动OpenOffice的服务
            String command = OpenOffice_HOME
                    + "program\\soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
            Process pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);

            // close the connection
            connection.disconnect();
            // 关闭OpenOffice服务的进程
            // pro.destroy();

            return 0;
        }
        // catch (FileNotFoundException e) {
        // e.printStackTrace();
        // return -1;
        // }
        catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public static int office2OFD(String sourceFile, String destFile) {
        String agentUrl = new AppSettings().getOfdagent();
        logger.info("convert sourcefile:" + sourceFile + "\t\t destfile:" + destFile + "\t\t ofdagent:" + agentUrl);
        File inFile = null;
        OutputStream out = null;
        HTTPAgent ha = null;
        try {
            ha = new HTTPAgent(agentUrl);
            inFile = new File(sourceFile);
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputFile);

            ha.officeToOFD(inFile, out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("convert to ofd faild because of IOException.\t\t" + e.toString());
        } catch (ConvertException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("convert to ofd faild because of ConvertException.\t\t" + e.toString());
        } catch (PackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.info("convert to ofd faild because of PackException.\t\t" + e.toString());
        } catch (Throwable ta) {
            ta.printStackTrace();
            logger.info("convert to ofd faild because of Throwable.\t\t" + ta.toString());
        } finally {
            try {
                if (ha != null) {
                    ha.close();// 注意：一定要记得关闭ha
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

}
