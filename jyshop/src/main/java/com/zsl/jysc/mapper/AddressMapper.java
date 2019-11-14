package com.zsl.jysc.mapper;

import com.zsl.jysc.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    int isExitAddress(Long addressId);
    boolean addNewAddress(Address address);
    List<Address> selectAddressByUserId(long userId);
    int deleteAddress(long addressId);
    int updateAddress(Address address);
}
