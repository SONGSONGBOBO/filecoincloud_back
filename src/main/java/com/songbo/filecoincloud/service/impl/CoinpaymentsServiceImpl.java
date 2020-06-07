package com.songbo.filecoincloud.service.impl;

import com.songbo.filecoincloud.bean.coinpayments.CreateRequest;
import com.songbo.filecoincloud.bean.coinpayments.GetTxInfoRequest;
import com.songbo.filecoincloud.service.CoinpaymentsService;
import com.songbo.filecoincloud.service.RPCRequestService;
import com.songbo.filecoincloud.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClients;
import org.brunocvcunha.coinpayments.CoinPayments;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;
import org.brunocvcunha.coinpayments.requests.CoinPaymentsCreateTransactionRequest;
import org.brunocvcunha.coinpayments.requests.CoinPaymentsGetTransactionInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CoinpaymentsServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/3 下午3:39
 **/
@Service
@Slf4j
public class CoinpaymentsServiceImpl implements CoinpaymentsService {

    @Autowired
    private RPCRequestService rpcRequestService;

    private static Map<String, String> map = new HashMap<>();
    static {
        map.put(CommonUtil.COINPAYMENTS_HEADER_TYPE_KEY, CommonUtil.COINPAYMENTS_HEADER_RYPE_VALUE);
    }

    @Override
    public CreateTransactionResponse createTransaction(CreateRequest createRequest) throws Exception {
        /*String data = CommonUtil.COINPAYMENTS_PUBLIC_DATA + createRequest.toHttpString();
        String hmacSHA512 = HMAC.HmacSHA512(CommonUtil.COINPAYMENTS_PRIVATE_KEY, data);
        map.put(CommonUtil.COINPAYMENTS_HEADER_HMAC, hmacSHA512);
        Headers headers = Headers.of(map);*/
        //return rpcRequestService.currentRequest(CommonUtil.COINPAYMENTS_URL, "post", headers, data, CommonResponse.class);
        //log.warn("rpcRequest: " + url);
        //官方包
        CoinPayments api = CoinPayments.builder()
                .publicKey(CommonUtil.COINPAYMENTS_PUBLIC_KEY)
                .privateKey(CommonUtil.COINPAYMENTS_PRIVATE_KEY)
                .client(HttpClients.createDefault()).build();

        ResponseWrapper<CreateTransactionResponse> txResponse = api.sendRequest(CoinPaymentsCreateTransactionRequest.builder()
                .amount(createRequest.getAmount())
                .currencyPrice(createRequest.getCurrency1())
                .currencyTransfer(createRequest.getCurrency2())
                //.callbackUrl("<callback-url-if-wanted>")
                .custom(createRequest.getBuyer_name())
                .address(CommonUtil.COINPAYMENTS_ADDRESS)
                .build());
        log.info(String.valueOf(txResponse));
        if (!"ok".equals(txResponse.getError())) {
            throw new Exception(String.valueOf(txResponse.getResult()));
        }
        return txResponse.getResult();
    }

    @Override
    public TransactionDetailsResponse getTxInfo(GetTxInfoRequest getTxInfoRequest) throws Exception {
        /*String data =CommonUtil.COINPAYMENTS_PUBLIC_DATA +"&"+ getTxInfoRequest.toHttpString();
        String hmacSHA512 = HMAC.HmacSHA512(CommonUtil.COINPAYMENTS_PRIVATE_KEY, data);
        map.put(CommonUtil.COINPAYMENTS_HEADER_HMAC, hmacSHA512);
        Headers headers = Headers.of(map);
        return rpcRequestService.currentRequest(CommonUtil.COINPAYMENTS_URL, "post", headers, data, CommonResponse.class);*/
        CoinPayments api = CoinPayments.builder()
                .publicKey(CommonUtil.COINPAYMENTS_PUBLIC_KEY)
                .privateKey(CommonUtil.COINPAYMENTS_PRIVATE_KEY)
                .client(HttpClients.createDefault()).build();

        ResponseWrapper<TransactionDetailsResponse> txResponse = api.sendRequest(CoinPaymentsGetTransactionInfoRequest.builder()
                .txid(getTxInfoRequest.getTxid())
                .build());
        log.info(String.valueOf(txResponse));
        if (!"ok".equals(txResponse.getError())) {
            throw new Exception(String.valueOf(txResponse.getResult()));
        }
        return txResponse.getResult();
    }
}
