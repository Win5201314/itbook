package com.inxedu.os.edu.dao.impl.userprofile;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.userprofile.UserProfileDao;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author www.inxedu.com
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends GenericDaoImpl implements UserProfileDao {

    public Long addUserProfile(UserProfile userProfile) {
        return this.insert("UserProfileMapper.createUserProfile", userProfile);
    }

    public void deleteUserProfileById(int id) {
        this.delete("UserProfileMapper.deleteUserProfileById", id);
    }

    public void updateUserProfile(UserProfile userProfile) {
        this.update("UserProfileMapper.updateUserProfile", userProfile);
    }

    public List<UserProfile> getUserProfileByUserId(int userid) {
        return this.selectList("UserProfileMapper.getUserProfileByUserId", userid);
    }

    public List<UserProfile> getUserProfileList(UserProfile userProfile) {
        return this.selectList("UserProfileMapper.getUserProfileList", userProfile);
    }

    @Override
    public UserProfile queryUserProfileById(Long id) {
        return selectOne("UserProfileMapper.queryUserProfileById",id);
    }

    @Override
    public int queryUserProfileConflict(UserProfile userProfile) {
        return selectOne("UserProfileMapper.queryUserProfileConflict",userProfile);
    }
}
