package com.example.catchroom_be.domain.user.entity;


import com.example.catchroom_be.domain.buyhistory.entity.BuyHistory;
import com.example.catchroom_be.domain.orderhistory.entity.OrderHistory;
import com.example.catchroom_be.domain.product.entity.Product;
import com.example.catchroom_be.domain.review.entity.Review;
import com.example.catchroom_be.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name ="user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phonenumber;

    @Column(name = "type")
    private String type;

    @Column(name = "bank")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "account_owner")
    private String accountOwner;

    @OneToMany(mappedBy = "user")
    List<DepositDetails> depositDetailsList = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Product> productList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<OrderHistory> orderHistories = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<BuyHistory> buyHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Review> reviewList = new ArrayList<>();



    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setBankAccountNumAccountOwnerBalance(Account account) {
        this.bankName = account.getBankName();
        this.accountNumber = account.getAccountNumber();
        this.accountOwner = account.getAccountOwner();
        this.balance = account.getBalance();
    }

    public void deleteBankAccountNumAccountOwnerBalance() {
        this.bankName = null;
        this.accountOwner = null;
        this.accountNumber = null;
        this.balance = null;
    }

    public void minusDepositWithdraw(Long deposit) {
        int tempBalance = balance;
        tempBalance -= deposit;
        this.balance = tempBalance;
    }

    public void updateBalance(int actualSellProfit) {
        if (this.balance != null)
            this.balance = balance + actualSellProfit;
        else
            this.balance = actualSellProfit;
    }
}
