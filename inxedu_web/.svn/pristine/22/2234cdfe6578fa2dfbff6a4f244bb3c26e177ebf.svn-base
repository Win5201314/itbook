package com.inxedu.os.edu.entity.kpoint;

import com.inxedu.os.edu.entity.course.CourseKpointAtlas;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseKpoint implements Serializable{
    private static final long serialVersionUID = -2252970709827434582L;
    /**视频节点ID*/
    private int kpointId;
    /**课程ID*/
    private int courseId;
    /**视频名*/
    private String name;
    /**视频父节点*/
    private int parentId;
    /**创建时间*/
    private Date addTime;
    /**排序*/
    private int sort;
    /**播放次数*/
    private int playCount;
    /**是否是免费 1免费 2收费*/
    private int free;
    /**视频路径*/
    private String videoUrl;
    /**教师ID*/
    private int teacherId;
    /**播放时间*/
    private String playTime;
    /**节点类型 0文件目录 1视频*/
    private int kpointType;
    /** 视频类型 */
    private String videoType;
    /**节点list*/
    private List<CourseKpoint> kpointList=new ArrayList<CourseKpoint>();
    private String fileType;//节点文件格式
    private String content;//文本内容
    private int pageCount;//页数
    private Date liveBeginTime;//直播开始时间
    private Date liveEndTime;//直播结束时间
    private String supplier;//直播供应商 gensee展示互动

    //辅助字段
    private String atlas;//图片集url
    private String atlasThumbnail;//图片集url缩略图
    private List<CourseKpoint> childKpoints=new ArrayList<CourseKpoint>();
    private List<CourseKpointAtlas> kpointAtlasesList=new ArrayList<>();//返回图片集
    private int queryLimitNum;//查询限制条数
    private String queryOrder;//查询排序
    private Date queryLiveIngTime;//正在直播的
    private int isavaliable;//1 正常　２　下架   3删除
    private String isStudy;//是否学完该课程（用于个人中心和播放大厅）
    private String openType;//打开方式 web网页 app客户端
}
