package com.record.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Range;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by 灌云县公安局 李秉键 on 2017/8/30.
 */

public class WordUtil {

    /**
     * 生成word
     * @param
     * @param outPutFilePath
     * @param hashMap
     */
    public static void doScan(InputStream inputStream,
                       String outPutFilePath,
                       Map<String,String> hashMap) throws IOException {
        //获取模板文件
//        File demoFile=new File(tempFilePath);
        //创建生成的文件

//        FileUtils.makeDirs(Constants.pathDir+Constants.dir);
        File newFile = null;
        File file=new File(outPutFilePath);
        if(!file.exists()){
            FileUtils.makeDirs(Constants.docPath);
            FileUtils.makeDirs(outPutFilePath);
            newFile = new File(outPutFilePath);
        }
//        newFile.mkdir()
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("$QYMC$", "xxx科技股份有限公司");
//        map.put("$QYDZ$", "上海市杨浦区xx路xx号");
//        map.put("$QYFZR$", "张三");
//        map.put("$FRDB$", "李四");
//        map.put("$CJSJ$", "2000-11-10");
//        map.put("$SCPZMSJWT$", "5");
//        map.put("$XCJCJBQ$", "6");
//        map.put("$JLJJJFF$", "7");
//        map.put("$QYFZRQM$", "张三");
//        map.put("$CPRWQM$", "赵六");
//        map.put("$ZFZH$", "100001");
//        map.put("$BZ$", "无");
        writeDoc(inputStream,newFile,hashMap);
//        //查看
//        doOpenWord();
    }
    /**
     * 调用手机中安装的可打开word的软件
     */
    public static void doOpenWord(Context context,String outputFilePath){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        String fileMimeType = "application/msword";
        intent.setDataAndType(Uri.fromFile(new File(outputFilePath)), fileMimeType);
        try{
            context.startActivity(intent);
        } catch(ActivityNotFoundException e) {
            //检测到系统尚未安装OliveOffice的apk程序
            Toast.makeText(context, "未找到软件,请先下载", Toast.LENGTH_LONG).show();
            //请先到www.olivephone.com/e.apk下载并安装
        }
    }


    /**
     * demoFile 模板文件
     * newFile 生成文件
     * map 要填充的数据
     * */
    public static void writeDoc(InputStream inputStream , File newFile , Map<String, String> map) throws IOException {

//            FileInputStream in = new FileInputStream(demoFile);
            HWPFDocument hdt = new HWPFDocument(inputStream);

            // Fields fields = hdt.getFields();
            // 读取word文本内容
            Range range = hdt.getRange();
            // System.out.println(range.text());

            // 替换文本内容
            for(Map.Entry<String, String> entry : map.entrySet())
            {
                range.replaceText(entry.getKey(), null==entry.getValue()?"":entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile, true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();


    }


    /**
     * word文档转成html格式
     * */
    public static void convert2Html(String inputFilePath, String outPutFile) throws Exception {
        initWordToHtmlConverter(new HWPFDocument(new FileInputStream(inputFilePath)),outPutFile);
    }


    /**
     * word文档转成html格式
     * */
    public static void convert2HtmlWithStream(InputStream inputStream, String outPutFile) throws Exception {
        initWordToHtmlConverter(new HWPFDocument(inputStream),outPutFile);

    }
    private static void initWordToHtmlConverter(HWPFDocument wordDocument,String outPutFile)throws Exception {

        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        out.close();
        //保存html文件
        writeFile(new String(out.toByteArray()), outPutFile);
    }

    /**
     * 将html文件保存到sd卡
     * */
    public static void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                FileUtils.makeDirs(Constants.htmlPath);
                FileUtils.makeDirs(path);
                file = new File(path);
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            bw.write(content);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }



    /**
     * 将html文件保存到sd卡
     * */
    public static String copyFile(InputStream is , String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                FileUtils.makeDirs(Constants.DOC_TEMP);
                FileUtils.makeDirs(Constants.LAW_PATH);
                FileUtils.makeDirs(path);
                file = new File(path);
                fos = new FileOutputStream(file);
                int byteCount;
                byte[] buffer = new byte[1024];
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
            }
            return path;
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        return "";
    }
}
