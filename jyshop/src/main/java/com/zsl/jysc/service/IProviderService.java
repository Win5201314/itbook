package com.zsl.jysc.service;

import com.zsl.jysc.entity.Provider;

public interface IProviderService {

    boolean isExitBusinessNumber(String businessNumber);
    boolean addNewProvider(Provider provider);
    Provider selectProviderByBusinessNumber(String businessNumber);
    boolean deleteProvider(Long providerId);
    boolean updateProvider(Provider provider);
}
