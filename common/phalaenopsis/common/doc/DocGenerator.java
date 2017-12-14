package phalaenopsis.common.doc;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import phalaenopsis.allWeather.controller.AllWeatherController;
import phalaenopsis.common.annotation.Comment;
import phalaenopsis.common.annotation.MethodComment;
import phalaenopsis.common.controller.AnalysisController;
import phalaenopsis.common.method.Tools.Linq;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/9
 * 修改历史：
 * 1. [2017/11/9]创建文件
 *
 * @author chunl
 */
public class DocGenerator {

    public static void main(String[] args) throws NoSuchMethodException, IOException {
        DocGenerator generator = new DocGenerator();
        generator.generate(new AnalysisController(), "analysis");

    }

    private <T> void generate(T t, String methodName) throws IOException {

        URL url = this.getClass().getResource("DocTemplate.html");
        File htmlTemplateFile = new File(url.getPath());
        String htmlString = FileUtils.readFileToString(htmlTemplateFile);


       // AllWeatherController controller = new AllWeatherController();
        Method[] methods = t.getClass().getMethods();
        RequestMapping clazzRequestMapper = t.getClass().getAnnotation(RequestMapping.class);


        List<Method> listMethod = Arrays.asList(methods);
        Method method = Linq.extEquals(listMethod, "name", methodName);
        RequestMapping methodRequestMapper = method.getAnnotation(RequestMapping.class);
        MethodComment methodComment = method.getAnnotation(MethodComment.class);


        //1,得到完整的URL路径
        String mappingUrl = clazzRequestMapper.value()[0] + methodRequestMapper.value()[0];


        //2,得到请求方法：GET、POST、PUT、Delete
        String requestMethod = methodRequestMapper.method()[0].name();


        //3,得到参数
        Parameter[] inParameters = method.getParameters();
        List<DocGenerator.Tuple> inParameterlist = new ArrayList<DocGenerator.Tuple>();

        for (Parameter parameter : inParameters) {
            String parameterType = parameter.getType().getName();
            Comment parameterComment = parameter.getAnnotation(Comment.class);
            if (null != parameterComment) {
                // 分别为：1，名称；2，说明；3，类型
                inParameterlist.add(new Tuple(parameterComment.argName(), parameterComment.value(), parameterType));
            }
        }


        //4,得到返回参数
        List<DocGenerator.Tuple> outParameterList = new ArrayList<DocGenerator.Tuple>();
        Class<?> clazz = method.getReturnType();
        if (clazz.getClass().equals(String.class) ||
                clazz.getClass().equals(Integer.class) ||
                clazz.getClass().equals(Double.class) ||
                clazz.getClass().equals(Long.class)) {

        } else {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Comment parameterComment = field.getDeclaredAnnotation(Comment.class);
                if (null != parameterComment) {
                    // 分别为：1，名称；2，说明；3，类型
                    outParameterList.add(new Tuple(field.getName(), parameterComment.value(), field.getType().getName()));
                }
            }
        }


        htmlString = htmlString.replace(" $funcDescribe", methodComment.value()); // 功能描述
        htmlString = htmlString.replace("$requestMethod", requestMethod);    // 请求方式
        htmlString = htmlString.replace("$requestUrl", mappingUrl);          // 服务地址
        htmlString = htmlString.replace("$inInfo", "");           //请求参数
        htmlString = htmlString.replace("$inTable", parameterTable(inParameterlist));           //请求参数

        htmlString = htmlString.replace("$returnInfo", methodComment.returnVal());           //参数
        if (null != outParameterList && 0 != outParameterList.size()) {
            htmlString = htmlString.replace("$returnTable", parameterTable(outParameterList));           //返回参数
        }else{
            htmlString = htmlString.replace("$returnTable","");
        }


        //File newHtmlFile = new File(this.getClass().getResource("").getPath(),"new.html");
        File newHtmlFile = new File("D:\\new.html");
        FileUtils.writeStringToFile(newHtmlFile, htmlString);


    }


    public String parameterTable(List<DocGenerator.Tuple> list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<table class=\"confluenceTable tablesorter\">\n" +
                "\t\t\t\t\t\t<thead>\n" +
                "\t\t\t\t\t\t\t<tr class=\"sortableHeader\">\n" +
                "\t\t\t\t\t\t\t\t<th class=\"confluenceTh sortableHeader\" data-column=\"0\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"tablesorter-header-inner\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span>名称</span>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</th>\n" +
                "\t\t\t\t\t\t\t\t<th class=\"confluenceTh sortableHeader\" data-column=\"1\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"tablesorter-header-inner\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span>说明</span>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</th>\n" +
                "\t\t\t\t\t\t\t\t<th class=\"confluenceTh sortableHeader\" data-column=\"2\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"tablesorter-header-inner\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span>类型</span>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</th>\n" +
                "\t\t\t\t\t\t\t\t<th class=\"confluenceTh sortableHeader\" data-column=\"2\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"tablesorter-header-inner\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span>示例</span>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</th>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t</thead>\n" +
                "\t\t\t\t\t\t<tbody>");

        for (DocGenerator.Tuple item : list) {
            buffer.append("\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t<td class=\"confluenceTd\">" + item.name + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td class=\"confluenceTd\">" + item.comment + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td class=\"confluenceTd\">" + item.type + "</td>\n" +
                    "\t\t\t\t\t\t\t\t<td class=\"confluenceTd\"></td>\n" +
                    "\t\t\t\t\t\t\t</tr>");
        }

        buffer.append("</tbody>\n" +
                "\t\t\t\t\t</table>\n" +
                "\t\t\t\t</div>");

        return buffer.toString();
    }


    public class Tuple {
        private String name;
        private String comment;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Tuple() {
        }

        public Tuple(String name, String comment, String type) {
            this.name = name;
            this.comment = comment;
            this.type = type;
        }
    }
}
