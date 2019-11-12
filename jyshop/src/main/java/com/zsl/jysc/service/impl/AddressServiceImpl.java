package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Address;
import com.zsl.jysc.service.IAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Override
    public boolean addNewAddress(Address address) {
        return false;
    }

    @Override
    public List<Address> selectAddressByUserId(long userId) {
        return null;
    }

    @Override
    public boolean deleteAddress(long addressId) {
        return false;
    }

    @Override
    public boolean updateAddress(Address address) {
        return false;
    }
}
