package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;
import okhttp3.MultipartBody;


public class ChatModel<T> extends BaseModel {


    public void applyToFriendsRefuse(Context context,String fromwho,int fid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriendsRefuse( fromwho, fid,2), observerListener,transformer);

    }


    public void applyToFriendsPass(Context context,String fromwho,int fid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriendsPass( fromwho, fid,1), observerListener,transformer);

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


    public void createGroup(Context context,String uid,String name,String introduce,String ids,MultipartBody.Part photo, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        if (photo!=null){
            subscribe(context, Api.getApiService().createGroup(uid,name,introduce,ids,photo), observerListener,transformer);

        }else {
            subscribe(context, Api.getApiService().createGroupNotAvatar(uid,name,introduce,ids), observerListener,transformer);

        }

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


    public void applyToFriends(Context context,String fromwho,String towho,String remarks, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().applyToFriends(fromwho,towho,remarks), observerListener,transformer);

    }

    public void getGroupInfo(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGroupInfo(address,qcode), observerListener,transformer);

    }

    public void upDataAvatar(Context context, String address, MultipartBody.Part userphone, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().upDataAvatar(address,userphone), observerListener,transformer);

    }


    public void upDataGroupAvatar(Context context, String uid,String qid,MultipartBody.Part  photo, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().upDataGroupAvatar( uid, qid, photo), observerListener,transformer);

    }


    public void getChatToken(Context context, String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getChatToken(address), observerListener,transformer);

    }

    public void deleteFriends(Context context, String uid,String tid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().deleteFriends(uid,tid), observerListener,transformer);

    }


    public void setRemark(Context context,String uid, String tid, String name, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().setRemark( uid,  tid,  name), observerListener,transformer);

    }


    public void getNicknameByAdd(Context context,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNicknameByAdd(address), observerListener,transformer);

    }

    public void addGroupMember(Context context,String uid,String tid,String qid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addGroupMember(uid,tid,qid), observerListener,transformer);

    }


    public void removeGroupMenber(Context context,String uid,String tid,String qid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().removeGroupMenber(uid,tid,qid), observerListener,transformer);

    }

    public void upDataGroupName(Context context,String uid,String qid, String name, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().upDataGroupName(uid,qid,name), observerListener,transformer);

    }

    public void upDataGroupExplain(Context context,String uid,String qid, String introduce, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().upDataGroupExplain(uid,qid,introduce), observerListener,transformer);

    }

    public void addGroupAddressBook(Context context,String uid,String qid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addGroupAddressBook(uid,qid), observerListener,transformer);

    }


    public void exitGroup(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().exitGroup(address,qcode), observerListener,transformer);

    }

    public void deleteGroupAddressBook(Context context,String address,String qcode, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().deleteGroupAddressBook(address,qcode), observerListener,transformer);

    }

    public void addBlacklist(Context context,String uid,String tid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addBlacklist(uid,tid), observerListener,transformer);

    }
    public void removeBlacklist(Context context,String uid,String tid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().removeBlacklist(uid,tid), observerListener,transformer);

    }


    public void getBlacklist(Context context,String uid , ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getBlacklist(uid), observerListener,transformer);

    }



    public void dismissgroup(Context context,String uid,String qid , ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().dismissgroup(uid,qid), observerListener,transformer);

    }



}
