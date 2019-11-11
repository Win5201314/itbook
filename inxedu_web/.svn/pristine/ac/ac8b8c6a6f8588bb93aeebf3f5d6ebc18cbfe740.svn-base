package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.dao.course.CourseKpointDao;
import com.inxedu.os.edu.entity.course.CourseKpointAtlas;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.service.course.CourseKpointAtlasService;
import com.inxedu.os.edu.service.course.CourseKpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseKpoint管理接口
 * @author www.inxedu.com
 */
@Service("courseKpointService")
public class CourseKpointServiceImpl implements CourseKpointService {

 	@Autowired
    private CourseKpointDao courseKpointDao;
	@Autowired
	private CourseKpointAtlasService courseKpointAtlasService;

 	
    public int addCourseKpoint(CourseKpoint courseKpoint){
    	return courseKpointDao.addCourseKpoint(courseKpoint);
    }

	
	public List<CourseKpoint> queryCourseKpointByCourseId(int courseId) {
		return courseKpointDao.queryCourseKpointByCourseId(courseId);
	}

	
	public CourseKpointDto queryCourseKpointById(int kpointId) {
		CourseKpointDto  courseKpointDto= courseKpointDao.queryCourseKpointById(kpointId);

		if(ObjectUtils.isNull(courseKpointDto)){
			return  null;
		}
		//图片集和pdf课节将查询关联的图片集信息
		if(StringUtils.equals(courseKpointDto.getFileType(),"ATLAS")||StringUtils.equals(courseKpointDto.getFileType(), "PDF")){
			CourseKpointAtlas courseKpointAtlas= courseKpointAtlasService.queryKpointAtlasByKpointId(Long.valueOf(kpointId));
			if (ObjectUtils.isNotNull(courseKpointAtlas)&&StringUtils.isNotEmpty(courseKpointAtlas.getUrl())) {
				//将图片路径截取存入图片集集合
				String atlasUrlArr[] = courseKpointAtlas.getUrl().split(",");
				String atlasTBUrlArr[] = courseKpointAtlas.getUrlThumbnail().split(",");
				List<CourseKpointAtlas> atlasList = new ArrayList<>();
				for (int i=0;i<atlasUrlArr.length;i++) {
					if(StringUtils.isNotEmpty(atlasUrlArr[i])){
						courseKpointAtlas = new CourseKpointAtlas();
						courseKpointAtlas.setKpointId(Long.valueOf(kpointId));
						courseKpointAtlas.setUrl(atlasUrlArr[i]);
						courseKpointAtlas.setUrlThumbnail(atlasTBUrlArr[i]);
						atlasList.add(courseKpointAtlas);
					}
				}
				courseKpointDto.setKpointAtlasesList(atlasList);
			}
		}
		return courseKpointDto;
	}

	
	public void updateKpoint(CourseKpoint courseKpoint) {
		courseKpointDao.updateKpoint(courseKpoint);

		//修改图片集和pdf关联
		if(StringUtils.equals(courseKpoint.getFileType(), "ATLAS")||StringUtils.equals(courseKpoint.getFileType(), "PDF")){
			if(StringUtils.isNotEmpty(courseKpoint.getAtlas().replaceAll(",",""))&&StringUtils.isNotEmpty(courseKpoint.getAtlasThumbnail().replaceAll(",",""))) {
				CourseKpointAtlas courseKpointAtlas = courseKpointAtlasService.queryKpointAtlasByKpointId(Long.valueOf(courseKpoint.getKpointId()));
				if (ObjectUtils.isNotNull(courseKpointAtlas)){
					courseKpointAtlas.setUrl(courseKpoint.getAtlas());
					courseKpointAtlas.setUrlThumbnail(courseKpoint.getAtlasThumbnail());
					//修改图片集
					courseKpointAtlasService.updateCourseKpointAtlas(courseKpointAtlas);
				}else{
					//创建图片集和pdf图片关联
					courseKpointAtlas=new CourseKpointAtlas();
					courseKpointAtlas.setKpointId(Long.valueOf(courseKpoint.getKpointId()));
					courseKpointAtlas.setUrl(courseKpoint.getAtlas());
					courseKpointAtlas.setUrlThumbnail(courseKpoint.getAtlasThumbnail());
					courseKpointAtlasService.createCourseKpointAtlas(courseKpointAtlas);
				}
			}
		}
	}

	
	public void deleteKpointByIds(String ids) {
		if(ids!=null && ids.trim().length()>0){
			if(ids.trim().endsWith(",")){
				ids = ids.trim().substring(0,ids.trim().length()-1);
			}
			courseKpointDao.deleteKpointByIds(ids);
		}
	}

	
	public void updateKpointParentId(int kpointId, int parentId) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("kpointId", kpointId);
		map.put("parentId", parentId);
		courseKpointDao.updateKpointParentId(map);
		
	}


	@Override
	public int getSecondLevelKpointCount(Long courseId) {
		return courseKpointDao.getSecondLevelKpointCount(courseId);
	}

	@Override
	public void updCourseKpointCount(int courseKpointId) {
		courseKpointDao.updCourseKpointCount(courseKpointId);
	}

	@Override
	public List<CourseKpointDto> queryKpointList(CourseKpoint courseKpoint) {
		return courseKpointDao.queryKpointList(courseKpoint);
	}

	@Override
	public List<CourseKpointDto> queryCourseNearestKpointList(CourseKpoint courseKpoint) {
		return courseKpointDao.queryCourseNearestKpointList(courseKpoint);
	}

	@Override
	public void updateKpointSort(CourseKpoint kpoint) {
		courseKpointDao.updateKpoint(kpoint);
	}
}