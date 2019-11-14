package com.qingyun.mvpretrofitrx.mvp.api;


import com.qingyun.mvpretrofitrx.mvp.base.BaseResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.Img;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.entity.TheArticleDetails;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * api service
 */
public interface ApiService {

    @POST("file")
    @Multipart
    Observable<Img> upload(@Part MultipartBody.Part img);


    Observable<NormalResponse> getSimple();


    @POST("et_home")
    Observable<BaseResponse<AssetResponse>> getWalletInfo(@Query("address") String walletAddress);

    @POST("et_import")
    Observable<BaseResponse<Object>> addWallet(@Query("address") String walletAddress);

    @POST("et_platformglod")
    Observable<BaseResponse<List<Wallet>>> getSupportCoinList(@Query("address") String address);


    @POST("et_glodoperation")
    Observable<BaseResponse<Object>> bindCoin(@Query("operationtype") String operationtype, @Query("address") String address, @Query("glodid") String glodid);

    //    contacts	[string]	是	设备号
//    name	[string]	是	联系人姓名
//    remarks 复制	[string]	是	备注（可以为空）
//    wallettype	[string]	是	不知道你们怎么选 后台只能添加ETH
//    address	[string]	是	地址
    @POST("et_addcontacts")
    Observable<BaseResponse<Object>> addContacts(@Query("contacts") String contacts, @Query("name") String name, @Query("remarks") String remarks, @Query("wallettype") String wallettype, @Query("address") String address);

    @POST("et_contactsall")
    Observable<BaseResponse<List<Contact>>> getContactList(@Query("contacts") String contacts);


    @POST("et_recordorder")
    Observable<BaseResponse<TransferLogResponse>> getLog(@Query("address") String address, @Query("glod") String glod, @Query("type") int type, @Query("page") int page);

    @POST("et_newsflash")
    Observable<BaseResponse<Flash>> getflashmoel(@Query("page") String page);

    @POST("et_news")
    Observable<BaseResponse<Time>> gettimehmoel(@Query("page") String page);

    @POST("et_quotation")
    Observable<BaseResponse<List<Quotation>>> getmakelist();

    @POST("et_newscontent")
    Observable<BaseResponse<TheArticleDetails>> geTheArticleDetails(@Query("id") String id);

    ;
    @POST("et_node")
    Observable <BaseResponse<String>>getNode();

    @POST("et_recordorderhash")
    Observable  <BaseResponse<TransferLog>>searchLogByHash(@Query("address")String address, @Query("hash")String hash,@Query("glod") String glod);

    @POST("et_notice")
    Observable <BaseResponse<PlatformNoticResponse>>getNoticeList(@Query("page")int page, @Query("contacts")String contacts);

    @POST("et_noticeone")
    Observable <BaseResponse<NoticeDetail>>getNoticeDetail(@Query("id") int id, @Query("contacts")String contacts);

    @POST("et_recordorderone")
    Observable <BaseResponse<BusinessDetail>>getBusinessDetail(@Query("address")String address, @Query("glod")String glod, @Query("id")String id);

    @POST("givecharge")
    Observable <BaseResponse<List<GasPrice>>>getGasPrice();

    @POST("api_giveus")
    Observable <BaseResponse<List<Item>>>getAboutUsList();

    @POST("api_givehelp")
    Observable <BaseResponse<List<Item>>>getSupportList();
}
