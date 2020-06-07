package com.songbo.filecoincloud.bean.coinpayments;

import lombok.Data;

/**
 * @ClassName CreateRequest
 * @Description TODO
 * cmd	create_transaction	Yes
 * amount	The amount of the transaction in the original currency (currency1 below).	Yes
 * currency1	The original currency of the transaction.	Yes
 * currency2	The currency the buyer will be sending. For example if your products are priced in USD but you are receiving BTC, you would use currency1=USD and currency2=BTC.
 * currency1 and currency2 can be set to the same thing if you don't need currency conversion.	Yes
 * buyer_email	Set the buyer's email address. This will let us send them a notice if they underpay or need a refund. We will not add them to our mailing list or spam them or anything like that.	Yes
 * address
 * buyer_name
 * @Author songbo
 * @Date 2020/4/3 下午2:27
 **/
@Data
public class CreateRequest {

    private String cmd;
    private Double amount;
    private String currency1;
    private String currency2;
    private String buyer_email;
    private String address;
    private String buyer_name;

    public CreateRequest() {
    }

    public CreateRequest(String cmd, Double amount, String currency1, String currency2, String buyer_email, String address, String buyer_name) {
        this.cmd = cmd;
        this.amount = amount;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.buyer_email = buyer_email;
        this.address = address;
        this.buyer_name = buyer_name;
    }

    public String toHttpString() {
        return
                "cmd=" + cmd +
                "&amount=" + amount +
                "&currency1=" + currency1 +
                "&currency2=" + currency2 +
                "&buyer_email=" + buyer_email +
                "&address=" + address +
                "&buyer_name=" + buyer_name;
    }
}
