package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Address;
import com.zsl.jysc.mapper.AddressMapper;
import com.zsl.jysc.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public boolean addNewAddress(Address address) {
        return addressMapper.addNewAddress(address);
    }

    @Override
    public List<Address> selectAddressByUserId(long userId) {
        return addressMapper.selectAddressByUserId(userId);
    }

    @Transactional
    @Override
    public boolean deleteAddress(long addressId) {
        return addressMapper.deleteAddress(addressId) >= 1;
    }

    @Transactional
    @Override
    public boolean updateAddress(Address address) {
        return addressMapper.updateAddress(address) >= 1;
    }
}
