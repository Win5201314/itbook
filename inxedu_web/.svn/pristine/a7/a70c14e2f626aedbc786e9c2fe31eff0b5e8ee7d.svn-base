package com.inxedu.os.edu.entity.account;

import com.inxedu.os.edu.constants.enums.account.AccountStatus;
import com.inxedu.os.edu.constants.enums.account.AccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 859063728570464654L;
    private Long id;
    private Long userId;// 用户id
    private java.util.Date createTime;// 创建时间
    private java.util.Date lastUpdateTime;// 最后更新时间
    private BigDecimal balance;// 账户总余额
    private BigDecimal forzenAmount;// 冻结金额
    private BigDecimal cashAmount;// 银行入账
    private BigDecimal vmAmount;// 充值卡入账
    private BigDecimal backAmount;// 分销返现充值到此字段
    private String accountStatus;// 账户状态
    private Long version;// 乐观锁版本号

    /**
     * 入款 根据入款金额 和 入款类型 计算
     * 
     * @param amount
     *            入款金额
     * @param accountType
     *            入款类型
     * @param amount
     */
    public void credit(BigDecimal amount, AccountType accountType) {
        amount = UserAccount.formatBigDecimal(amount);
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        setBalance(UserAccount.formatBigDecimal(this.balance.add(amount)));
        // cash 和vm
        if (AccountType.CASH.toString().equals(accountType.toString())) {
            setCashAmount(UserAccount.formatBigDecimal(this.cashAmount.add(amount)));
        } else if (AccountType.VM.toString().equals(accountType.toString())) {
            setVmAmount(UserAccount.formatBigDecimal(this.vmAmount.add(amount)));
        } else if (AccountType.BACK.toString().equals(accountType.toString())) {
            setBackAmount(UserAccount.formatBigDecimal(this.backAmount.add(amount)));
        }

    }

    /**
     * 出款
     * 根据出款金额 先扣除vm,不够时扣除back(返现金额)，再扣除cash 计算
     * @param amount
     */
    public void debit(BigDecimal amount) {
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }
        // 扣款时账户如果被冻结了。不准消费
        if (!AccountStatus.ACTIVE.toString().equals(getAccountStatus().toString())) {
            throw new IllegalStateException("account status is invalid");
        }
        setBalance(UserAccount.formatBigDecimal(this.balance.subtract(amount)));
        // 设置cashAmount和vmAmount 、backAmount ,出款时先扣除vm,不够时扣除back(返现金额)，再扣除cash
        if (getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额
            setVmAmount(UserAccount.formatBigDecimal(getVmAmount().subtract(amount)));
        } else if ((getVmAmount().add(getBackAmount())).compareTo(amount) >= 0) {// vm余额back(返现金额) 大于等于扣款的金额
            BigDecimal didAmount = amount.subtract(getVmAmount());// 需要扣除的back(返现金额)的金额
            setVmAmount(new BigDecimal(0));
            setBackAmount(UserAccount.formatBigDecimal(this.backAmount.subtract(didAmount)));
        } else {// vm不够的时候 再扣除cash的余额
            BigDecimal didAmount = amount.subtract(getVmAmount().add(getBackAmount()));// 需要扣除的cash的金额
            setVmAmount(new BigDecimal(0));// vm设置为0
            setBackAmount(new BigDecimal(0));// back(返现金额) 设置为0
            setCashAmount(UserAccount.formatBigDecimal(this.cashAmount.subtract(didAmount)));
        }

    }

    public static BigDecimal formatBigDecimal(BigDecimal decimal) {
        return decimal.setScale(2, RoundingMode.HALF_UP);
    }

}
