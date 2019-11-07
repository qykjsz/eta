package com.qingyun.mvpretrofitrx.mvp.api;


import com.qingyun.mvpretrofitrx.mvp.base.BaseResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Img;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
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


    Observable <NormalResponse>getSimple();


    @POST("et_home")
    Observable <BaseResponse<AssetResponse>>getWalletInfo(@Query("address") String walletAddress);

    @POST("et_import")
    Observable <BaseResponse<Object>>addWallet(@Query("address")String walletAddress);
}
