package com.inxedu.os.edu.entity.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * 会员商品
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSaleDTO extends MemberSale implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 会员类型名称
     */
    private String title;
    
    /**
     * 会员商品类型的状态
     */
    private int memberTypeState;
}

