package com.zsl.boss.demo.mapper;


import com.zsl.boss.demo.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insertWithPhone(@Param("phone") String phone);

    int updateWithPhoneByOpenid(@Param("phone") String phone, @Param("openid") String openid);

    int insertWithPhoneAndOpenid(@Param("phone") String phone, @Param("openid") String openid);

    Users selectUserByOpenid(@Param("openid") String openid);

    Users selectUserByPhone(@Param("phone") String phone);

    Users selectUserById(@Param("id") int id);

    Users selectByPhone(@Param("phone") String phone);

    Integer selectIdByPhone(@Param("phone") String phone);

    int updateJoinWorkTime(@Param("id") int id, @Param("joinWorkTime") String joinWorkTime);

    int updateDateOfBirth(@Param("id") int id, @Param("dateOfBirth") String dateOfBirth);

    int updateSuperiority(@Param("id") int id, @Param("superiority") String superiority);

    int updateJobStatus(@Param("id") int id, @Param("jobStatus") int jobStatus);

    int updateIsHideResume(@Param("id") int id, @Param("isHideResume") boolean isHideResume);

    int updateIshideResumeToHeadhunter(@Param("id") int id, @Param("isHideResumeToHeadhunter") boolean isHideResumeToHeadhunter);

    int updateIsLookHeadhunterJobs(@Param("id") int id, @Param("isLookHeadhunterJobs") boolean isLookHeadhunterJobs);

    int updateIsEncryptionTelephoneCanCallMe(@Param("id") int id, @Param("isEncryptionTelephoneCanCallMe") boolean isEncryptionTelephoneCanCallMe);

    int selectJobStatus(@Param("id") int id);
}
