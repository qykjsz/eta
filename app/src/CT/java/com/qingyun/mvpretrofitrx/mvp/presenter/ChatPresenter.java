package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyListRespones;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessageLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.ContactsResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.ChatModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class ChatPresenter extends ChatContact.Presenter {
    private ChatModel model;
    private Context context;


    public ChatPresenter(Context context) {
        this.model = new ChatModel();
        this.context = context;
    }




    @Override
    public void applyToFriendsRefuse(String fromwho, int fid) {
        model.applyToFriendsRefuse(context,fromwho,fid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().applyToFriendsRefuseSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void applyToFriendsPass(String fromwho, int fid) {
        model.applyToFriendsPass(context,fromwho,fid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().applyToFriendsPassSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getFriendsList(String fromwho,String selectaddress) {
        final List<GroupMember> groupMemberList = new ArrayList<>();
        model.getFriendsList(context,fromwho,selectaddress,getView().bindLifecycle(), new ObserverResponseListener<ContactsResponse>() {

            @Override
            public void onNext(ContactsResponse contactsResponse) {
                groupMemberList.addAll(contactsResponse.getA());
                groupMemberList.addAll(contactsResponse.getB());
                groupMemberList.addAll(contactsResponse.getC());
                groupMemberList.addAll(contactsResponse.getD());
                groupMemberList.addAll(contactsResponse.getE());
                groupMemberList.addAll(contactsResponse.getF());
                groupMemberList.addAll(contactsResponse.getG());
                groupMemberList.addAll(contactsResponse.getH());
                groupMemberList.addAll(contactsResponse.getI());
                groupMemberList.addAll(contactsResponse.getJ());
                groupMemberList.addAll(contactsResponse.getK());
                groupMemberList.addAll(contactsResponse.getL());
                groupMemberList.addAll(contactsResponse.getM());
                groupMemberList.addAll(contactsResponse.getN());
                groupMemberList.addAll(contactsResponse.getO());
                groupMemberList.addAll(contactsResponse.getP());
                groupMemberList.addAll(contactsResponse.getQ());
                groupMemberList.addAll(contactsResponse.getR());
                groupMemberList.addAll(contactsResponse.getS());
                groupMemberList.addAll(contactsResponse.getT());
                groupMemberList.addAll(contactsResponse.getU());
                groupMemberList.addAll(contactsResponse.getV());
                groupMemberList.addAll(contactsResponse.getW());
                groupMemberList.addAll(contactsResponse.getX());
                groupMemberList.addAll(contactsResponse.getY());
                groupMemberList.addAll(contactsResponse.getZ());
                groupMemberList.addAll(contactsResponse.getXx());

                if(getView() != null){
                    getView().getFriendsListSuccess(groupMemberList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void sendMessage(String fromwho, String towho, String text) {
        model.sendMessage(context,fromwho,towho,text,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().sendMessageSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void viewMessage(String fromwho, String towho) {
        model.viewMessage(context,fromwho,towho,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().viewMessageSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void seeChatMessageLog(String fromwho, String towho, int page) {
        model.seeChatMessageLog(context,fromwho,towho,page,getView().bindLifecycle(), new ObserverResponseListener<ChatMessageLogResponse>() {

            @Override
            public void onNext(ChatMessageLogResponse chatMessageLogResponse) {
                if(getView() != null){
                    getView().seeChatMessageLogSuccess(chatMessageLogResponse.getChat());
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void newChaList(String fromwho) {
        model.newChaList(context,fromwho,getView().bindLifecycle(), new ObserverResponseListener<NewChat>() {

            @Override
            public void onNext(NewChat newChat) {
                if(getView() != null){
                    getView().newChaListSuccess(newChat);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addFriendsList(String fromwho, int page) {
        model.addFriendsList(context,fromwho,page,getView().bindLifecycle(), new ObserverResponseListener<List<ApplyGroup>>() {

            @Override
            public void onNext(List<ApplyGroup> applyGroupList) {
                if(getView() != null){
                    getView().addFriendsListSuccess(applyGroupList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addGroupList(String fromwho, int page) {
        model.addGroupList(context,fromwho,page,getView().bindLifecycle(), new ObserverResponseListener<ApplyListRespones>() {

            @Override
            public void onNext(ApplyListRespones applyListRespones) {
                if(getView() != null){
                    if (applyListRespones.getList()==null){
                        getView().addGroupListSuccess(new ArrayList<ApplyGroup>());
                    }else {
                        getView().addGroupListSuccess(applyListRespones.getList());

                    }
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void createGroup(String address, String name, String introduce) {
        model.createGroup(context,address,name,introduce,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().createGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getGroupList(String address) {
        model.getGroupList(context,address,getView().bindLifecycle(), new ObserverResponseListener<List<Group>>() {

            @Override
            public void onNext(List<Group> list) {
                if(getView() != null){
                    getView().getGroupListSuccess(list);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void setIfGroupReview(String address, String code, String verification) {
        model.setIfGroupReview(context,address,code,verification,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().setIfGroupReviewSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void applyIntoGroup(String address, String code) {
        model.applyIntoGroup(context,address,code,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().applyIntoGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void dealApplyIntoGroupApply(String address, String qcode, int sid,int status) {
        model.dealApplyIntoGroupApply(context,address,qcode,sid,status,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().dealApplyIntoGroupApplySuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getNicknameByAdress(String address) {
        model.getNicknameByAdress(context,address,getView().bindLifecycle(), new ObserverResponseListener<GroupMember>() {

            @Override
            public void onNext(GroupMember groupMember) {
                if(getView() != null){
                    getView().getNicknameByAdressSuccess(groupMember);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void transferGroup(String address, String qcode, String toaddress) {
        model.transferGroup(context,address,qcode,toaddress,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().transferGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getGroupMemberList(String address, String qcode) {
        model.getGroupMemberList(context,address,qcode,getView().bindLifecycle(), new ObserverResponseListener<List<GroupMember>>() {

            @Override
            public void onNext(List<GroupMember> groupMemberList) {
                if(getView() != null){
                    getView().getGroupMemberListSuccess(groupMemberList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void exitGroup(String address, String qcode) {
        model.exitGroup(context,address,qcode,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().exitGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void sendMessageToGroup(String fromwho, String togroup, String text) {
        model.sendMessageToGroup(context,fromwho,togroup,text,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().sendMessageToGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void registerChat(String address) {
        model.registerChat(context,address,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().registerChatSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getGroupChatLog(String fromwho, String qcode, int page) {
        model.getGroupChatLog(context,fromwho,qcode,page,getView().bindLifecycle(), new ObserverResponseListener<ChatMessageLogResponse>() {

            @Override
            public void onNext(ChatMessageLogResponse chatMessageLogResponse) {
                if(getView() != null){
                    getView().getGroupChatLogSuccess(chatMessageLogResponse.getChat());
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void setMessageReadGroup(String fromwho, String qcode) {
        model.setMessageReadGroup(context,fromwho,qcode,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().setMessageReadGroupSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void changeNickname(String address, String name) {
        model.changeNickname(context,address,name,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().changeNicknameSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void applyToFriends(String fromwho, String towho) {
        model.applyToFriends(context,fromwho,towho,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().applyToFriendsSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getGroupInfo(String address, String qcode) {
        model.getGroupInfo(context,address,qcode,getView().bindLifecycle(), new ObserverResponseListener<Group>() {

            @Override
            public void onNext(Group group) {
                if(getView() != null){
                    getView().getGroupInfoSuccess(group);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void upDataAvatar(String address, MultipartBody.Part userphone) {

        model.upDataAvatar(context,address,userphone,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().upDataAvatarSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getChatToken(String address) {
        model.getChatToken(context,address,getView().bindLifecycle(), new ObserverResponseListener<RyunToken>() {

            @Override
            public void onNext(RyunToken ryunToken) {
                if(getView() != null){
                    getView().getChatTokenSuccess(ryunToken);
                }
            }
            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void deleteFriends(String uid, String tid) {
        model.deleteFriends(context,uid,tid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().deleteFriendsSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
