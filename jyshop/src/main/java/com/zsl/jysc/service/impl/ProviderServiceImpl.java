package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Provider;
import com.zsl.jysc.mapper.ProviderMapper;
import com.zsl.jysc.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public boolean isExitBusinessNumber(String businessNumber) {
        return providerMapper.isExitBusinessNumber(businessNumber) >= 1;
    }

    @Override
    public boolean addNewProvider(Provider provider) {
        return providerMapper.addNewProvider(provider);
    }

    @Override
    public Provider selectProviderByBusinessNumber(String businessNumber) {
        return providerMapper.selectProviderByBusinessNumber(businessNumber);
    }

    @Transactional
    @Override
    public boolean deleteProvider(Long providerId) {
        if (providerMapper.isExitProviderId(providerId) >= 1) {
            return providerMapper.deleteProvider(providerId) >= 1;
        } else {
            return true;
        }
    }

    @Transactional
    @Override
    public boolean updateProvider(Provider provider) {
        if (providerMapper.isExitProviderId(provider.getId()) >= 1) {
            return providerMapper.updateProvider(provider) >= 1;
        } else {
            return true;
        }
    }
}
