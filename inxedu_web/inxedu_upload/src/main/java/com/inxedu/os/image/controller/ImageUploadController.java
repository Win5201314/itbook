package com.inxedu.os.image.controller;


import com.google.gson.JsonObject;
import com.inxedu.os.image.util.FastJsonUtil;
import com.inxedu.os.image.util.FileUploadUtils;
import com.inxedu.os.image.util.PDFUtils;
import com.inxedu.os.image.util.UploadPropertyUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.inxedu.os.image.util.FileUploadUtils.checkFileExt;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/image")
public class ImageUploadController {
	private static Logger logger = LoggerFactory.getLogger(ImageUploadController.class);



	public static UploadPropertyUtil propertyUtil = UploadPropertyUtil.getInstance("project");

	/**
	 *	获得项目根目录
     */
	private static String getProjectRootDirPath(){
		return propertyUtil.getProperty("file.root");
	}

	/**
	 * 图片上传，原图保存
	 * @param request
	 * @param response
	 * @param uploadfile 文件数据
	 * @param param 参数据
	 * @param pressText 是否上水印
	 * @return
	 */
	@RequestMapping(value="/gok4",method={RequestMethod.POST})
	public String gok4(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="uploadfile" ,required=true) MultipartFile uploadfile,
			@RequestParam(value="param",required=false) String param,
			@RequestParam(value="pressText",required=false) String pressText){
		try{
			//最大文件大小
			long maxSize = 4096000;
			System.out.println(uploadfile.getSize());
			//检查文件大小
			if(uploadfile.getSize() > maxSize){
				return responseErrorData(response,1,"上传的图片大小不能超过4M。");
			}
			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(uploadfile.getOriginalFilename());
			if(!checkFileExt(ext)){
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			uploadfile.transferTo(file);
			/*获取参数cutImg 判断是否切图*/
			String cutImg = request.getParameter("cutImg");
			if ("true".equals(cutImg)){
				/*获取传过来的图片宽高*/
				String width = request.getParameter("width");
				String height = request.getParameter("height");
				/*如果图片尺寸不为空调用图片拉伸方法*/
				if ((null != width && width.trim().length() != 0)&&(null != height && height.trim().length() != 0)&&!"undefined".equals(width)&&!"undefined".equals(height)){
					zoomImage(filePath,Integer.parseInt(width),Integer.parseInt(height));
				}
			}
			//上水印
//			if(pressText!=null && pressText.equals("true")){
//				FileUploadUtils.pressText("inxedu", file.getPath(), file.getPath(), "Segoe Script", Font.BOLD, Color.BLUE,0.2f);
//			}
			//返回数据

			//kindeditor4.x编辑器中上传图片 返回ke中需要的url和error值 注意：同域中本方法直接返回json格式字符即可
			String referer = request.getHeader("referer");
			Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
			Matcher mathcer = p.matcher(referer);
			logger.info("imgk4 referer:" + referer);
			if (mathcer.find()) {
				String callBackPath = mathcer.group();// 请求来源
				JsonObject json = new JsonObject();
				// 编辑器中需要返回完整的路径
				json.addProperty("url", filePath);
				json.addProperty("error", 0);
				json.addProperty("message", "上传成功");

				// 同域时直接返回json即可无需redirect
				String url = "redirect:" + callBackPath + "/kindeditor/plugins/image/redirect.html?s=" + URLEncoder.encode(json.toString(), "utf8") + "#" + URLEncoder.encode(json.toString(), "utf8");
				logger.info("imgk4 ok");
				return url;
			} else {
				return responseData(filePath,0,"上传成功",response);
			}

		}catch (Exception e) {
			logger.error("gok4()--error",e);
			return responseErrorData(response,2,"系统繁忙，上传失败");
		}
	}
	
	/**
	 * 编辑器图片上传，原图保存
	 * @param request
	 * @param response
	 * @param param 参数据
	 * @param fileType 允许上传类型
	 * @param pressText 是否上水印
	 * @return
	 */
	@RequestMapping(value="/keupload",method={RequestMethod.POST})
	public String kindEditorUpload(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="param",required=false) String param,
			@RequestParam(value="fileType",required=true) String fileType,
			@RequestParam(value="pressText",required=false) String pressText){
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile imgFile = multipartRequest.getFile("imgFile");
			if(imgFile==null){
				return responseData("",1,"请选择上传的文件，上传失败",response);
			}
			//最大文件大小
			long maxSize = 4096000;
			System.out.println(imgFile.getSize());
			//检查文件大小
			if(imgFile.getSize() > maxSize){
				return responseErrorData(response,1,"上传的图片大小不能超过4M。");
			}

			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
			if(!checkFileExt(ext)){
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			imgFile.transferTo(file);
			//上水印
//			if(pressText!=null && pressText.equals("true")){
//				FileUploadUtils.pressText("inxedu", file.getPath(), file.getPath(), "Segoe Script", Font.BOLD, Color.BLUE,0.2f);
//			}

			//kindeditor4.x编辑器中上传图片 返回ke中需要的url和error值 注意：同域中本方法直接返回json格式字符即可
			String referer = request.getHeader("referer");
			if(referer==null){
				//返回数据
				return responseData(request.getRequestURL().toString().replace(request.getRequestURI(),"")+filePath,0,"上传成功",response);
			}
			Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
			Matcher mathcer = p.matcher(referer);
			logger.info("imgk4 referer:" + referer);
			if (mathcer.find()) {
				String callBackPath = mathcer.group();// 请求来源
				JsonObject json = new JsonObject();
				// 编辑器中需要返回完整的路径
				json.addProperty("url", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath() +filePath);
				json.addProperty("error", 0);
				json.addProperty("message", "上传成功");

				// 同域时直接返回json即可无需redirect
				String url = "redirect:" + callBackPath + "/kindeditor/plugins/image/redirect.html?s=" + URLEncoder.encode(json.toString(), "utf8") + "#" + URLEncoder.encode(json.toString(), "utf8");
				logger.info("imgk4 ok");
				return url;
			} else {
				//返回数据
				return responseData(filePath,0,"上传成功",response);
			}

		}catch (Exception e) {
			logger.error("kindEditorUpload()--error",e);
			return responseErrorData(response,2,"系统繁忙，上传失败。");
		}
	}

	/**
	 * 图片上传（带缩列图）
	 * @param request
	 * @param response
	 * @param param 参数据
	 * @param fileType 允许上传类型
	 * @param pressText 是否上水印
	 * @return
	 */
	@RequestMapping(value="/shrinkDiagram",method={RequestMethod.POST})
	@ResponseBody
	public String kindEditorUploadShrinkDiagram(HttpServletRequest request,HttpServletResponse response,
								   @RequestParam(value="param",required=false) String param,
								   @RequestParam(value="fileType",required=true) String fileType,
								   @RequestParam(value="pressText",required=false) String pressText){
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile imgFile = multipartRequest.getFile("imgFile");
			if(imgFile==null){
				return responseData("",1,"请选择上传的文件，上传失败",response);
			}
			//最大文件大小
			long maxSize = 1024000;
			System.out.println(imgFile.getSize());
			//检查文件大小
			if(imgFile.getSize() > maxSize){
				return responseErrorData(response,1,"上传文件大小超过限制。");
			}

			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
			if(!checkFileExt(ext)){
				//return responseData("",1,"文件格式错误，上传失败");
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			imgFile.transferTo(file);
			//上水印
//			if(pressText!=null && pressText.equals("true")){
//				FileUploadUtils.pressText("inxedu", file.getPath(), file.getPath(), "Segoe Script", Font.BOLD, Color.BLUE,0.2f);
//			}



			String width = request.getParameter("width");
			String height = request.getParameter("height");
			//返回缩列图
			String smallUrl=zoomImage(request,filePath,Integer.valueOf(width), Integer.valueOf(height));
			//返回数据

			Map<String,Object> map = new HashMap<String, Object>();
			map.put("url", filePath);
			map.put("error", 0);
			map.put("message", "上传成功");
			map.put("smallurl",smallUrl);
			String url = FastJsonUtil.obj2JsonString(map);
			/*response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println(url);*/
			return url;
		}catch (Exception e) {
			logger.error("kindEditorUpload()--error",e);
			//return responseData("",2,"系统繁忙，上传失败");
			return responseErrorData(response,2,"系统繁忙，上传失败。");
		}
	}


	
	/**
	 * 用户头像上传
	 */
	@RequestMapping({ "/saveface" })
	@ResponseBody
	public String saveface(HttpServletRequest request,HttpServletResponse response) {
		try {
			String photoPath = request.getParameter("photoPath");
			
			//原图片的宽（现显示的宽度，非原图片宽度）
			int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
			//原图片的高（现显示的高度，非原图片高度）
			int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
			
			//因页面显示关系，需要转换图片大小（转成现页面显示的图片大小）
			//FileUploadUtils.changeSize(getProjectRootDirPath(request)+photoPath,getProjectRootDirPath(request)+photoPath,imageWidth,imageHeight);

			//选中区域距离顶部的大小
			int cutTop = Integer.parseInt(request.getParameter("txt_top"));
			//选中区域距离左边的大小
			int cutLeft = Integer.parseInt(request.getParameter("txt_left"));

			//选中区域的宽
			int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
			//选中区域的高
			int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
			
			String ext = FileUploadUtils.getSuffix(photoPath);
			if(!checkFileExt(ext)){
				//return responseData("",1,"文件格式错误，上传失败");
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			String newPath = getPath(request,ext,"customer");
			FileUploadUtils.cut(getProjectRootDirPath()+photoPath, getProjectRootDirPath()+newPath, cutLeft, cutTop, dropWidth, dropHeight,imageWidth,imageHeight);
			//上传剪裁完成后，删除模板图片
			File file = new File(getProjectRootDirPath()+photoPath);
			if(file.exists()){
				file.delete();
			}

			//最大文件大小
			long maxSize = 4096000;
			//检查文件大小
			if(file.length() > maxSize){
				return responseErrorData(response,1,"上传的图片大小不能超过4M。");
			}

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("src",newPath);
			map.put("status","1");
			return FastJsonUtil.obj2JsonString(map);
		} catch (Exception e) {
			logger.error("saveface()---error", e);
		}
		return null;
	}

	/**
	 *
	 *  pdf并转换为swf和图片
	 */
	@RequestMapping(value = "/pdfUploadAndImg")
	@ResponseBody
	public String pdfUploadAndImg(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="param",required=false) String param) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			String width = request.getParameter("width");
			String height = request.getParameter("height");

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgFile = multipartRequest.getFile("fileupload");

			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
			if(!"pdf".equals(ext)){
				//return responseData("",1,"文件格式错误，上传失败");
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			imgFile.transferTo(file);

			Map<String,String> pdfToPngmap = PDFUtils.pdfToPng(getProjectRootDirPath(),getProjectRootDirPath()+filePath, width, height);
			map.put("pdfUrl", filePath);
			map.put("pngUrlStrs", pdfToPngmap.get("pngUrlStrs"));
			map.put("pngUrlStrsTB", pdfToPngmap.get("pngUrlStrsTB"));

			return FastJsonUtil.obj2JsonString(map);
			//return swfFileName.replace(FileUtil.rootpath, "/");
		} catch (Exception e) {
			logger.error("pdfUploadAndImg error", e);
		}
		return null;
	}

	/**
	 * swf使用上传文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadfile")
	@ResponseBody
	public String uploadfile(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="param",required=false) String param) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgFile = multipartRequest.getFile("fileupload");
			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(imgFile.getOriginalFilename());
			if(!"mp3".equals(ext)){
				//return responseData("",1,"文件格式错误，上传失败");
				return responseErrorData(response,1,"文件格式错误，上传失败。");
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			imgFile.transferTo(file);

			logger.info("++++upload img return:" + filePath);
			return filePath;
		} catch (Exception e) {
			logger.error("uploadfile error", e);
		}
		return null;
	}


	/**
	 * 删除文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deletefile",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteFile(HttpServletRequest request,@RequestParam(value="filePath",required=true) String filePath){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(filePath!=null && filePath.trim().length()>0 && filePath.startsWith("/images/upload/")){
				File file = new File(getProjectRootDirPath()+filePath);

				if(file.exists()){
					file.delete();
					json = setJson(true, "文件删除成功", null);
				}else{
					json = setJson(false, "文件不存在，删除失败", null);
				}
			}else{
				json = setJson(false, "删除失败", null);
			}
		}catch (Exception e) {
			json = setJson(false, "系统繁忙，文件删除失败", null);
			logger.error("deleteFile()--error",e);
		}
		return json;
	}
	
	//--------------------------------------------------------------------------------------
	
	/**
	 * 获取文件保存的路径
	 * @param request
	 * @param ext 文件后缀
	 * @param param 传入参数
	 * @return 返回文件路径
	 */
	private String getPath(HttpServletRequest request,String ext,String param){
		String filePath = "/images/upload/";
		if(param!=null && param.trim().length()>0){
			filePath+=param;
		}else{
			filePath+= propertyUtil.getProperty("projectName");
		}
		filePath+="/"+ toString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+"."+ext;
		return filePath;
	}
	
	/**
	 * 返回数据
	 * @param path 文件路径
	 * @param error 状态 0成功 其状态均为失败
	 * @param message 提示信息
	 * @return 回调路径 
	 */
	public String responseData(String path,int error,String message,HttpServletResponse response) throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("url", path);
		map.put("error", error);
		map.put("message", message);
		response.getWriter().write(FastJsonUtil.obj2JsonString(map));
		return null;
	}

	/**
	 * 返回错误数据
	 * @param error 状态 0成功 其状态均为失败
	 * @param message 提示信息
	 */
	public String responseErrorData(HttpServletResponse response,int error,String message){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("error", error);
			map.put("message", message);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(FastJsonUtil.obj2JsonString(map));
			response.getWriter().flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}



	/**
	 * 由原图图生成指定宽高的jpg小图
	 */
	public String zoomImage(HttpServletRequest request,String srcFileName,int width, int height) {
		String tagFileName = srcFileName.substring(0, srcFileName.lastIndexOf("."))+"-small"+srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
		srcFileName = getProjectRootDirPath() +srcFileName;
		try {

			//得到原图片
			BufferedImage bufferedImage= ImageIO.read(new File(srcFileName));

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
			ImageIO.write(outputImage, FileUploadUtils.getSuffix(getProjectRootDirPath() +tagFileName), new File(getProjectRootDirPath() +tagFileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tagFileName;
	}

	/*
    * 图片缩放,w，h为缩放的目标宽度和高度
    * src为源文件目录
    */
	public static void zoomImage(String src,int w,int h) throws Exception {
		if (w==0||h==0||src==null||src.trim().length()==0){
			return;
		}
		double wr=0,hr=0;
		String dest = getProjectRootDirPath();
		File srcFile = new File(dest+src);
		BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
		Image Itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);//设置缩放目标图片模板

		wr=w*1.0/bufImg.getWidth();     //获取缩放比例
		hr=h*1.0 / bufImg.getHeight();

		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		srcFile.delete();
		try {
			ImageIO.write((BufferedImage) Itemp,src.substring(src.lastIndexOf(".")+1), new File(dest+src)); //写入缩减后的图片
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 设置ajax请返回结果
	 *
	 * @param success
	 *            请求状态
	 * @param message
	 *            提示信息
	 * @param entity
	 *            返回数据结果对象
	 */
	public static Map<String,Object> setJson(boolean success, String message, Object entity) {
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success", Boolean.valueOf(success));
		json.put("message", message);
		json.put("entity", entity);
		return json;
	}

	public static String toString(Date date, String pattern) {
		if(date == null) {
			return "";
		} else {
			if(pattern == null) {
				pattern = "yyyy-MM-dd";
			}

			String dateString = "";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);

			try {
				dateString = sdf.format(date);
			} catch (Exception var5) {
				var5.printStackTrace();
			}

			return dateString;
		}
	}

	/**
	 * 编辑器图片上传，原图保存
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value="/ueditorUpload")
	public String ueditorUpload(HttpServletRequest request,HttpServletResponse response){
		try{
			request.setCharacterEncoding( "utf-8" );
			response.setHeader("Content-Type" , "text/html");
			response.getWriter().write( new ActionEnter( request, getProjectRootDirPath() ).exec() );
			return null;
		}catch (Exception e) {
			logger.error("ueditorUpload()--error",e);
			return responseErrorData(response,2,"系统繁忙，上传失败。");
		}
	}*/

	/**
	 * ueditor上传图片
	 * @param request
	 * @return UEDITOR 需要的json格式数据
	 */
	@RequestMapping(value="/ueditorupload",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object ueditorupload(HttpServletRequest request,HttpServletResponse response,@Param(value="param") String param){
		Map<String,Object> result = new HashMap<String, Object>();

		MultipartHttpServletRequest mReq  =  null;
		MultipartFile multipartFile = null;
		InputStream is = null ;
		String fileName = "";
		// 原始文件名   UEDITOR创建页面元素时的alt和title属性
		String originalFileName = "";

		try {
			mReq = (MultipartHttpServletRequest)request;
			// 从config.json中取得上传文件的ID
			multipartFile = mReq.getFile("upfile");
			// 取得文件的原始文件名称
			fileName = multipartFile.getOriginalFilename();

			/*originalFileName = fileName;

			if(!StringUtils.isEmpty(fileName)){
				is = file.getInputStream();
				fileName = FileUtils.reName(fileName);
				filePath = FileUtils.saveFile(fileName, is, fileuploadPath);
			} else {
				throw new IOException("文件名为空!");
			}*/


			//最大文件大小
			/*long maxSize = 4096000;
			System.out.println(file.getSize());
			//检查文件大小
			if(file.getSize() > maxSize){
				return responseErrorData(response,1,"上传的图片大小不能超过4M。");
			}*/
			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = FileUploadUtils.getSuffix(multipartFile.getOriginalFilename());
			if(!checkFileExt(ext)){
				result.put("state", "文件格式错误，上传失败!");
				result.put("url","");
				result.put("title", "");
				result.put("original", "");
				return result;
			}
			//获取文件路径
			String filePath = getPath(request,ext,param);
			File file = new File(getProjectRootDirPath()+filePath);

			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			multipartFile.transferTo(file);
			/*获取参数cutImg 判断是否切图*/
			String cutImg = request.getParameter("cutImg");
			if ("true".equals(cutImg)){
				/*获取传过来的图片宽高*/
				String width = request.getParameter("width");
				String height = request.getParameter("height");
				/*如果图片尺寸不为空调用图片拉伸方法*/
				if ((null == width || width.trim().length() == 0)&&(null == height || height.trim().length() == 0)&&!"undefined".equals(width)&&!"undefined".equals(height)){
					zoomImage(filePath,Integer.parseInt(width),Integer.parseInt(height));
				}
			}

			result.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
			result.put("url",request.getRequestURL().toString().replace(request.getRequestURI(),"") + filePath);
			result.put("title", originalFileName);
			result.put("original", originalFileName);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("state", "文件上传失败!");
			result.put("url","");
			result.put("title", "");
			result.put("original", "");
			System.out.println("文件 "+fileName+" 上传失败!");
		}
		return result;
	}
	/**
	 * 根据图片地址下载图片
	 * @param request
	 * @return imageUrl 需要的图片地址
	 */
	@RequestMapping(value="/download",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object download(HttpServletRequest request,@RequestParam(value="imageUrl") String imageUrl,@RequestParam(value="param",required=false) String param){
		Map<String,Object> json = new HashMap<String,Object>();

		try {
			// 构造URL
			URL url = new URL(imageUrl);
			// 打开连接
			URLConnection con = url.openConnection();
			//设置请求超时为5s
			con.setConnectTimeout(5*1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;

			String ext = "jpg";

			//获取文件路径
			String savePath = getPath(request,ext,param);

			// 输出的文件流
			File sf=new File(getProjectRootDirPath()+savePath);
			//如果目录不存在，则创建
			if(!sf.getParentFile().exists()){
				sf.getParentFile().mkdirs();
			}
			System.out.println(sf.getPath());
			OutputStream os = new FileOutputStream(sf.getPath());
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();

			json.put("success", "true");
			json.put("message", "");
			json.put("entity", savePath);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			json.put("success", "false");
			json.put("message", "文件上传失败!");
			json.put("entity", "");
		}
		return json;
	}
}
