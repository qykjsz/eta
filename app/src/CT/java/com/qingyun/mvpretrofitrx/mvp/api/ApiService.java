package com.qingyun.mvpretrofitrx.mvp.api;


import com.qingyun.mvpretrofitrx.mvp.base.BaseResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyListRespones;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetReviewResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessageLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.ContactsResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.GoldInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.Img;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.Node;
import com.qingyun.mvpretrofitrx.mvp.entity.NodeResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunGroupInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.entity.TheArticleDetails;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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


    @POST("et_upcontacts")
    Observable<BaseResponse<Object>> editContacts(@Query("contactsid") String contactsid,@Query("contacts") String contacts, @Query("name") String name, @Query("remarks") String remarks, @Query("wallettype") String wallettype, @Query("address") String address);

    @POST("et_delcontacts")
    Observable<BaseResponse<Object>> deleteContacts(@Query("contactsid") String contactsid,@Query("contacts") String contacts);




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


    @POST("get_jiedian")
    Observable <BaseResponse<NodeResponse>>getNodeList();


    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("et_allassets")
    Observable<BaseResponse<AssetReviewResponse>> getAssetsReview(@Body RequestBody route);

    @POST("api_update")
    Observable <BaseResponse<VersionInfo>> getVersion(@Query("type")String type);

    @POST("give_et_money")
    Observable<BaseResponse<List<CurrencyRate>>> getCurrencyRate();

    @POST("give_et_fimoney")
    Observable<BaseResponse<List<CoinTypeRate>>>  getCoinTypeRate();

    @POST("api_shoporder")
    Observable <BaseResponse<BusinessPayLogResponse>> getBusinessPayLog(@Query("address")String address, @Query("page")int page);

    @POST("api_shop")
    Observable <BaseResponse<NormalResponse>>addBusinessPayLog(@Query("from")String from, @Query("to")String to,@Query("hash") String hash,
                                                               @Query("money")String money,@Query("moneyid") String moneyid,
                                                               @Query("fimoney")String fimoney,@Query("fimoneyid") String fimoneyid);
    @GET("/")
    Observable <BaseResponse<NormalResponse>>checkAccount();

    @GET("?ct=new_recharge&ac=notice")
    Observable <BaseResponse<NormalResponse>>addInvestInfo(@Query("account")String account,
                             @Query("num")String num,
                             @Query("order_sn")String order_sn,
                             @Query("sign")String sign);

    @POST("support_et_games")
    Observable <BaseResponse<List<Platform>>>getSuprtPlatform();

    @POST("support_et_gamemoneys")
    Observable <BaseResponse<List<String>>>getInvestAmountList();

    @POST("recharge_et_game")
    Observable<BaseResponse<String>> invest( @Query("from")String from,
                       @Query("to")String to,
                       @Query("hash") String hash,
                       @Query("moneystate")String moneystate,
                       @Query("gameid")String gameid,
                       @Query("money")String money,
                       @Query("gamenumber") String gamenumber,
                       @Query("gameuser")String gameuser);

    @POST("recharge_et_gameorder")
    Observable <BaseResponse<InvestLogResponse>>getInvestLog(@Query("address")String address, @Query("page") int page);


    @POST("recharge_et_game_verification")
    Observable <BaseResponse<String>>checkInvestInfo(@Query("gameid")String gameid, @Query("gamenumber")String gamenumber, @Query("moneystate")String moneystate, @Query("money")String money);

    @POST("glod_details")
    Observable <BaseResponse<GoldInfo>>getGoldInfo(@Query("glod")String glod);

    @POST("et_transaction")
    Observable <BaseResponse<String>>checkCanTransfer(@Query("name")String name);

    @POST("rongyun_addfriend_agree")
    Observable <BaseResponse<String>>applyToFriendsRefuse(@Query("uid")String uid, @Query("tid")int tid,@Query("status")int status);

    @POST("rongyun_addfriend_agree")
    Observable <BaseResponse<String>>applyToFriendsPass(@Query("uid")String uid, @Query("tid")int tid,@Query("status")int status);


    @POST("rongyun_myfriends")
    Observable <BaseResponse<ContactsResponse>>getFriendsList(@Query("uid")String uid, @Query("selectaddress")String selectaddress);

    @POST("tochating")
    Observable <BaseResponse<String>>sendMessage(@Query("fromwho")String fromwho, @Query("towho")String towho, @Query("text")String text);


    @POST("lookchating")
    Observable <BaseResponse<String>>viewMessage(@Query("fromwho")String fromwho, @Query("towho")String towho);

    @POST("chatingorder")
    Observable<BaseResponse<ChatMessageLogResponse>> seeChatMessageLog(@Query("fromwho")String fromwho, @Query("towho") String towho, @Query("page")int page);

    @POST("chatinglist")
    Observable <BaseResponse<NewChat>>newChaList(@Query("fromwho")String fromwho);

    @POST("rongyun_givefriend")
    Observable<BaseResponse<List<ApplyGroup>>> addFriendsList(@Query("uid")String uid, @Query("page")int page);

    @POST("groupaddlist")
    Observable<BaseResponse<ApplyListRespones>> addGroupList(@Query("fromwho")String fromwho, @Query("page")int page);

    @POST("rongyun_addgrop")
    @Multipart
    Observable <BaseResponse<RyunGroupInfo>>createGroup(@Query("uid")String uid, @Query("name")String name, @Query("introduce")String introduce, @Query("ids")String ids, @Part MultipartBody.Part photo);

    @POST("rongyun_addgrop")
    Observable <BaseResponse<RyunGroupInfo>>createGroupNotAvatar(@Query("uid")String uid, @Query("name")String name, @Query("introduce")String introduce, @Query("ids")String ids);




    @POST("groupverification")
    Observable <BaseResponse<String>>setIfGroupReview(@Query("address")String address, @Query("code")String code,@Query("verification")String verification);

    @POST("useraddgroup")
    Observable<BaseResponse<String>> applyIntoGroup(@Query("address")String address,@Query("code") String code);


//    	2 通过 3 拒绝
    @POST("adminaddgroup")
    Observable <BaseResponse<String>>dealApplyIntoGroupApply(@Query("address")String address, @Query("qcode")String qcode,@Query("sid") int sid,@Query("state")int state);


    @POST("rongyun_getuser_id")
    Observable <BaseResponse<GroupMember>>getNicknameByAdress(@Query("id")String id);

    @POST("rongyun_getuser_address")
    Observable <BaseResponse<GroupMember>>getNicknameByAdd(@Query("address")String address);

    @POST("upgroupuser")
    Observable <BaseResponse<String>>transferGroup(@Query("address")String address, @Query("qcode")String qcode,@Query("toaddress") String toaddress);

    @POST("rongyun_groupsuser")
    Observable<BaseResponse<List<GroupMember>>> getGroupMemberList(@Query("address")String address,@Query("qid") String qid);

    @POST("togroupchating")
    Observable <BaseResponse<String>>sendMessageToGroup(@Query("fromwho")String fromwho, @Query("togroup")String togroup,@Query("text") String text);

    @POST("addchatuser")
    Observable <BaseResponse<String>>registerChat(@Query("address")String address);

    @POST("chatinggrouporder")
    Observable <BaseResponse<ChatMessageLogResponse>>getGroupChatLog(@Query("fromwho")String fromwho,@Query("qcode") String qcode,@Query("page") int page);

    @POST("lookgroupchating")
    Observable <BaseResponse<String>>setMessageReadGroup(@Query("fromwho")String fromwho, @Query("qcode")String qcode);

    @POST("rongyun_upmyname")
    Observable <BaseResponse<String>>changeNickname(@Query("uid")String uid, @Query("name")String name);

    @POST("rongyun_addfriend")
    Observable <BaseResponse<String>>applyToFriends(@Query("uid")String fromwho, @Query("tid")String towho, @Query("remarks")String remarks);


    @POST("rongyun_group")
    Observable <BaseResponse<Group>>getGroupInfo(@Query("address")String address, @Query("qid")String qid);

    @POST("rongyun_upmyphoto")
    @Multipart
    Observable  <BaseResponse<String>>upDataAvatar(@Query("uid")String uid, @Part MultipartBody.Part userphone);

    @POST("rongyun_gettoken")
    Observable <BaseResponse<RyunToken>>getChatToken(@Query("address")String address);

    @POST("rongyun_delmyfriend")
    Observable <BaseResponse<String>>deleteFriends(@Query("uid")String uid,@Query("tid")String tid);

    @POST("rongyun_userremarks")
    Observable <BaseResponse<String>>setRemark(@Query("uid")String uid,@Query("tid")String tid,@Query("name")String name);


    @POST("rongyun_addgropuser")
    Observable <BaseResponse<String>>addGroupMember(@Query("uid")String uid,@Query("tid")String tid,@Query("qid")String qid);


    @POST("rongyun_delgropuser_foradmin")
    Observable <BaseResponse<String>>removeGroupMenber(@Query("uid")String uid,@Query("tid")String tid,@Query("qid")String qid);


    @POST("rongyun_delgropuser_foruser")
    Observable <BaseResponse<String>>exitGroup(@Query("uid")String uid,@Query("qid")String qid);


    @POST("rongyun_upgroupphpoto")
    @Multipart
    Observable  <BaseResponse<String>>upDataGroupAvatar(@Query("uid")String uid,@Query("qid")String qid, @Part MultipartBody.Part userphone);

    @POST("rongyun_upgroupname")
    Observable  <BaseResponse<String>>upDataGroupName(@Query("uid")String uid,@Query("qid")String qid, @Query("name")String name);

    @POST("rongyun_upgroupintroduce")
    Observable  <BaseResponse<String>>upDataGroupExplain(@Query("uid")String uid,@Query("qid")String qid, @Query("introduce")String introduce);


    @POST("rongyun_addmygroups")
    Observable  <BaseResponse<String>>addGroupAddressBook(@Query("uid")String uid,@Query("qid")String qid);


    @POST("rongyun_delmygroups")
    Observable  <BaseResponse<String>>deleteGroupAddressBook(@Query("uid")String uid,@Query("qid")String qid);

    @POST("rongyun_mygroups")
    Observable  <BaseResponse<List<Group>>>getGroupList(@Query("uid")String uid);

    @POST("rongyun_addblacklist")
    Observable  <BaseResponse<String>>addBlacklist(@Query("uid")String uid,@Query("tid")String tid);

    @POST("rongyun_delblacklist")
    Observable  <BaseResponse<String>>removeBlacklist(@Query("uid")String uid,@Query("tid")String tid);

    @POST("rongyun_blacklist")
    Observable  <BaseResponse<List<GroupMember>>>getBlacklist(@Query("uid")String uid);


    @POST("rongyun_dismissgroup")
    Observable  <BaseResponse<String>>dismissgroup(@Query("uid")String uid,@Query("qid")String qid);


}
