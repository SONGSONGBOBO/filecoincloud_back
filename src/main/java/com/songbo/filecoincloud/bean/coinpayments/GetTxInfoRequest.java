package com.songbo.filecoincloud.bean.coinpayments;

import lombok.Data;

/**
 * @ClassName GetTxInfo
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/3 下午3:07
 **/
@Data
public class GetTxInfoRequest {

    private String cmd;
    private String txid;

    public GetTxInfoRequest() {
    }

    public GetTxInfoRequest(String cmd, String txid) {
        this.cmd = cmd;
        this.txid = txid;
    }

    public String toHttpString(){
        return  "cmd=" + cmd +
                "&txid=" + txid ;
    }

}
