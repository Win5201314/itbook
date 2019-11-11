/**
 * 
 */
package com.inxedu.os.image.util;


import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.PDimension;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * pdf工具类
 */
public class PDFUtils {
    // 读取配置文件类
    //public static PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    float scale = 2f;
    float rotation = 0f;

    //传入pdf的路径，传入转出图片的路径
    public static Map<String,String> pdfToPng(String rootpath, String pdfurl, String width, String height) throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        System.out.println("pdfToPng");
        //转后的pdf图片路径
        String pngUrlStrs = "";
        //转后的pdf图片路径缩略图
        String pngUrlStrsTB = "";
        String pngFileUrl=pdfurl.substring(0,pdfurl.lastIndexOf("."));

        String pngFileName=pngFileUrl.substring(pngFileUrl.lastIndexOf("/"),pngFileUrl.length());

        createPath(pngFileUrl);
        Document document = new MyDocument();
        document.setFile(pdfurl);
        int pages = document.getNumberOfPages();

        for( int i = 0 ; i < pages ; i++ ){
            float rotation = 0f;
            tranferPer(document, rotation, pngFileUrl+""+pngFileName+"_pdf_" + i + ".png", i);
            if(i==0){
                pngUrlStrs = (pngFileUrl+""+pngFileName+"_pdf_" + i + ".png").replace(rootpath,"");
                pngUrlStrsTB = zoomImage(rootpath,(pngFileUrl+""+pngFileName+"_pdf_" + i + ".png").replace(rootpath,""),Integer.valueOf(width),Integer.valueOf(height));
            }else{
                pngUrlStrs += ","+(pngFileUrl+""+pngFileName+"_pdf_" + i + ".png").replace(rootpath,"");
                pngUrlStrsTB += ","+zoomImage(rootpath,(pngFileUrl+""+pngFileName+"_pdf_" + i + ".png").replace(rootpath,""),Integer.valueOf(width),Integer.valueOf(height));
            }
        }
        document.dispose();
        map.put("pngUrlStrs",pngUrlStrs);
        map.put("pngUrlStrsTB",pngUrlStrsTB);
        return map;
    }

    /**
     * 转换一页pdf为jpg大图
     */
    public static void tranferPer(Document document, float rotation, String imagepath, int index) throws Exception{
        float scale = 3f;
        Page page = document.getPageTree().getPage(index);
        page.init();
        PDimension sz = page.getSize(Page.BOUNDARY_CROPBOX, rotation, scale);
        int pageWidth = (int) sz.getWidth();
        int pageHeight = (int) sz.getHeight();
        BufferedImage image = new BufferedImage(pageWidth, pageHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        page.paint(g, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX,
                rotation, scale);
        g.dispose();
        try {
            System.out.println("转换第 " + (index + 1) + " 页");
            File file = new File(imagepath);
            ImageIO.write(image, "jpg", file);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        image.flush();
    }

    /**
     * 由原图图生成指定宽高的jpg小图
     */
    public static String zoomImage(String rootpath,String srcFileName,
                                 int width, int height) {
        String tagFileName = srcFileName.substring(0, srcFileName.lastIndexOf("."))+"-small"+srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
        srcFileName = rootpath +srcFileName;
        try {

            //得到原图片
            BufferedImage bufferedImage=ImageIO.read(new File(srcFileName));

            //获取图片高宽
            int srcWidth=bufferedImage.getWidth();
            int srcHeight=bufferedImage.getHeight();
            float _scale = Float.valueOf(width)/srcWidth;
            srcHeight = (int)(Float.valueOf(srcHeight)*_scale);

            /** width - 将图像缩放到的宽度。 height - 将图像缩放到的高度。 hints - 指示用于图像重新取样的算法类型的标志。  */
            Image image = bufferedImage.getScaledInstance(width, srcHeight,Image.SCALE_SMOOTH);

            ImageFilter cropFilter = new CropImageFilter(0, 0, width, height);
            Image img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image.getSource(),cropFilter));
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = outputImage.getGraphics();
            g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
            g.dispose();


            //写出新的图片
            ImageIO.write(outputImage, getSuffix(  rootpath+tagFileName), new File( rootpath +tagFileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagFileName;
    }

    /**
     * 新建目录
     */
    public static void createPath(String filePath) {
        filePath = filePath.toString();// 中文转换
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) myFilePath.mkdirs();
    }
    /**
     * 获取后缀
     * @param str
     * @return 返回图片后缀
     */
    private static String getSuffix(String str){
        return str = str.substring(str.lastIndexOf(".")+1);
    }
}
