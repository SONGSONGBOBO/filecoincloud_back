package com.songbo.filecoincloud.service;


import com.songbo.filecoincloud.bean.coinpayments.CreateRequest;
import com.songbo.filecoincloud.bean.coinpayments.GetTxInfoRequest;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;

public interface CoinpaymentsService {

    CreateTransactionResponse createTransaction(CreateRequest createRequest) throws Exception;

    TransactionDetailsResponse getTxInfo(GetTxInfoRequest getTxInfoRequest) throws Exception;
}
