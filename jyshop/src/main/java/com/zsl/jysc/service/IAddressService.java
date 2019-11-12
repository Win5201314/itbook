package com.zsl.jysc.service;

import com.zsl.jysc.entity.Address;

import java.util.List;

public interface IAddressService {

    boolean addNewAddress(Address address);
    List<Address> selectAddressByUserId(long userId);
    boolean deleteAddress(long addressId);
    boolean updateAddress(Address address);
}
