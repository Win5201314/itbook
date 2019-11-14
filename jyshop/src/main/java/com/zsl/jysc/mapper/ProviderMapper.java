package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.Provider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProviderMapper {

    int isExitProviderId(Long providerId);
    int isExitBusinessNumber(String businessNumber);
    boolean addNewProvider(Provider provider);
    Provider selectProviderByBusinessNumber(String businessNumber);
    int deleteProvider(Long providerId);
    int updateProvider(Provider provider);
}
