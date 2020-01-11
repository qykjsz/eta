package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;
import okhttp3.MultipartBody;


public class ChatModel<T> extends BaseModel {


    public void applyToFriendsRefuse(Context context,String fromwho,int fid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriendsRefuse( fromwho, fid), observerListener,transformer);

    }


    public void applyToFriendsPass(Context context,String fromwho,int fid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriendsPass( fromwho, fid), observerListener,transformer);

    }


    public void getFriendsList(Context context,String fromwho, String selectaddress,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getFriendsList( fromwho,selectaddress), observerListener,transformer);

    }


    public void sendMessage(Context context,String fromwho,String towho,String text, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().sendMessage(  fromwho, towho, text), observerListener,transformer,false,false);

    }

    public void viewMessage(Context context,String fromwho,String towho , ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().viewMessage(  fromwho, towho), observerListener,transformer);

    }

    public void seeChatMessageLog(Context context,String fromwho,String towho,int page , ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().seeChatMessageLog(  fromwho, towho,page), observerListener,transformer);

    }

    public void newChaList(Context context,String fromwho , ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().newChaList(fromwho), observerListener,transformer,false,false);

    }

    public void addFriendsList(Context context,String fromwho,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addFriendsList(fromwho,page), observerListener,transformer);

    }


    public void addGroupList(Context context,String fromwho,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addGroupList(fromwho,page), observerListener,transformer);

    }


    public void createGroup(Context context,String address,String name,String introduce, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().createGroup(address,name,introduce), observerListener,transformer);

    }


    public void getGroupList(Context context,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGroupList(address), observerListener,transformer);

    }

    public void setIfGroupReview(Context context,String address,String code,String verification, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().setIfGroupReview(address,code,verification), observerListener,transformer);

    }

    public void applyIntoGroup(Context context,String address,String code, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyIntoGroup(address,code), observerListener,transformer);

    }

    public void dealApplyIntoGroupApply(Context context,String address,String qcode,int sid,int state, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().dealApplyIntoGroupApply(address,qcode,sid,state), observerListener,transformer);

    }

    public void getNicknameByAdress(Context context,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNicknameByAdress(address), observerListener,transformer,false,false);

    }

    public void transferGroup(Context context,String address,String qcode,String toaddress, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().transferGroup(address,qcode,toaddress), observerListener,transformer);

    }



    public void getGroupMemberList(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGroupMemberList(address,qcode), observerListener,transformer);

    }

    public void exitGroup(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().exitGroup(address,qcode), observerListener,transformer);

    }


    public void sendMessageToGroup(Context context,String fromwho,String togroup,String text, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().sendMessageToGroup(fromwho,togroup,text), observerListener,transformer,false,false);

    }


    public void registerChat(Context context,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().registerChat(address), observerListener,transformer);

    }


    public void getGroupChatLog(Context context,String fromwho,String qcode,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGroupChatLog(fromwho,qcode,page), observerListener,transformer);

    }



    public void setMessageReadGroup(Context context,String fromwho,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().setMessageReadGroup(fromwho,qcode), observerListener,transformer);

    }


    public void changeNickname(Context context,String address,String name, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().changeNickname(address,name), observerListener,transformer);

    }


    public void applyToFriends(Context context,String fromwho,String towho, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriends(fromwho,towho), observerListener,transformer);

    }

    public void getGroupInfo(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGroupInfo(address,qcode), observerListener,transformer);

    }

    public void upDataAvatar(Context context, String address, MultipartBody.Part userphone, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().upDataAvatar(address,userphone), observerListener,transformer);

    }


    public void getChatToken(Context context, String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getChatToken(address), observerListener,transformer);

    }

}
