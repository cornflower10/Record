package com.record.utils;

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
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by xiejingbao on 2017/8/30.
 */

public class WordUtil {




    /**
     * demoFile 模板文件
     * newFile 生成文件
     * map 要填充的数据
     * */
    public static void writeDoc(File demoFile , File newFile , Map<String, String> map)
    {
        try
        {
            FileInputStream in = new FileInputStream(demoFile);
            HWPFDocument hdt = new HWPFDocument(in);
            // Fields fields = hdt.getFields();
            // 读取word文本内容
            Range range = hdt.getRange();
            // System.out.println(range.text());

            // 替换文本内容
            for(Map.Entry<String, String> entry : map.entrySet())
            {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            FileOutputStream out = new FileOutputStream(newFile, true);
            hdt.write(ostream);
            // 输出字节流
            out.write(ostream.toByteArray());
            out.close();
            ostream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * word文档转成html格式
     * */
    public static void convert2Html(String fileName, String outPutFile) {
        HWPFDocument wordDocument = null;
        try {
            wordDocument = new HWPFDocument(new FileInputStream(fileName));
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            //设置图片路径
//            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//                public String savePicture(byte[] content,
//                                          PictureType pictureType, String suggestedName,
//                                          float widthInches, float heightInches) {
//                    String name = docName.substring(0, docName.indexOf("."));
//                    return name + "/" + suggestedName;
//                }
//            });
            //保存图片
//            List<Picture> pics=wordDocument.getPicturesTable().getAllPictures();
//            if(pics!=null){
//                for(int i=0;i<pics.size();i++){
//                    Picture pic = (Picture)pics.get(i);
//                    System.out.println( pic.suggestFullFileName());
//                    try {
//                        String name = docName.substring(0,docName.indexOf("."));
//                        String file = outHtmlPath+ name + "/"
//                                + pic.suggestFullFileName();
//                        FileUtils.makeDirs(file);
//                        pic.writeImageContent(new FileOutputStream(file));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                file.createNewFile();
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
}
