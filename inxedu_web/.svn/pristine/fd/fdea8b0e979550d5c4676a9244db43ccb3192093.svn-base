package com.inxedu.os.edu.dao.impl.shopcart;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.shopcart.ShopcartDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.shopcart.Shopcart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 */
@Repository("shopcartDao")
public class ShopcartDaoImpl extends GenericDaoImpl implements ShopcartDao {

    public Long addShopcart(Shopcart shopcart) {
        return this.insert("ShopcartMapper.createShopcart", shopcart);
    }

    public void deleteShopcartById(Long id) {
        this.delete("ShopcartMapper.deleteShopcartById", id);
    }

    public List<Shopcart> getShopcartListByUserId(Long userId,Long type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("type", type);
        return this.selectList("ShopcartMapper.getShopcartListByUserId", map);
    }
    public List<Course> getShopcartCourseList(Long userId) {
        return this.selectList("ShopcartMapper.getShopcartCourseList", userId);
    }
    public List<Shopcart> getShopcartList( Shopcart shopcart) {
        return this.selectList("ShopcartMapper.getShopcartList", shopcart);
    }


    /**
     * 清空数据库的购物车 
     *
     * @param type
     *            要删除的类型
     */
    public void deleteShopcartByType(Long type,Long userId){
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("type", type);
        map.put("userId", userId);
        this.delete("ShopcartMapper.deleteShopcartByType", map);
    }

	
}
