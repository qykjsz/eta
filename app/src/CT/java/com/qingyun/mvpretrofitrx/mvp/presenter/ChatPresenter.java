package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;
import android.util.Log;

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
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunGroupInfo;
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


                groupMemberList.addAll(contactsResponse.getXx());
                Log.e("---------------","2");


                Log.e("---------------","1");
                groupMemberList.addAll(contactsResponse.getA());

                Log.e("---------------","2");

                groupMemberList.addAll(contactsResponse.getB());
                Log.e("---------------","3");

                groupMemberList.addAll(contactsResponse.getC());
                Log.e("---------------","4");

                groupMemberList.addAll(contactsResponse.getD());
                Log.e("---------------","5");

                groupMemberList.addAll(contactsResponse.getE());
                Log.e("---------------","6");

                groupMemberList.addAll(contactsResponse.getF());
                Log.e("---------------","7");

                groupMemberList.addAll(contactsResponse.getG());
                Log.e("---------------","8");

                groupMemberList.addAll(contactsResponse.getH());
                Log.e("---------------","9");

                groupMemberList.addAll(contactsResponse.getI());
                Log.e("---------------","10");

                groupMemberList.addAll(contactsResponse.getJ());
                Log.e("---------------","11");

                groupMemberList.addAll(contactsResponse.getK());
                Log.e("---------------","12");

                groupMemberList.addAll(contactsResponse.getL());
                Log.e("---------------","13");

                groupMemberList.addAll(contactsResponse.getM());
                Log.e("---------------","14");

                groupMemberList.addAll(contactsResponse.getN());
                Log.e("---------------","15");

                groupMemberList.addAll(contactsResponse.getO());
                Log.e("---------------","16");

                groupMemberList.addAll(contactsResponse.getP());
                Log.e("---------------","17");

                groupMemberList.addAll(contactsResponse.getQ());
                Log.e("---------------","18");

                groupMemberList.addAll(contactsResponse.getR());
                Log.e("---------------","19");

                groupMemberList.addAll(contactsResponse.getS());
                Log.e("---------------","20");

                groupMemberList.addAll(contactsResponse.getT());
                Log.e("---------------","21");

                groupMemberList.addAll(contactsResponse.getU());
                Log.e("---------------","22");

                groupMemberList.addAll(contactsResponse.getV());
                Log.e("---------------","23");

                groupMemberList.addAll(contactsResponse.getW());
                Log.e("---------------","24");

                groupMemberList.addAll(contactsResponse.getX());
                Log.e("---------------","25");

                groupMemberList.addAll(contactsResponse.getY());
                Log.e("---------------","26");

                groupMemberList.addAll(contactsResponse.getZ());
                Log.e("---------------","27");


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
    public void createGroup(String uid, String name, String introduce, String ids, MultipartBody.Part photo) {
        model.createGroup(context,uid,name,introduce,ids,photo,getView().bindLifecycle(), new ObserverResponseListener<RyunGroupInfo>() {

            @Override
            public void onNext(RyunGroupInfo ryunGroupInfo) {
                if(getView() != null){
                    getView().createGroupSuccess(ryunGroupInfo.getQid()+"");
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
    public void deleteGroupAddressBook(String address, String qcode) {
        model.deleteGroupAddressBook(context,address,qcode,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().addGroupAddressBookSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addBlacklist(String uid, String tid) {

        model.addBlacklist(context,uid,tid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().addBlacklistSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    @Override
    public void removeBlacklist(String uid, String tid) {
        model.removeBlacklist(context,uid,tid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().addBlacklistSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    @Override
    public void getBlacklist(String uid) {
        model.getBlacklist(context,uid,getView().bindLifecycle(), new ObserverResponseListener<List<GroupMember>>() {

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
    public void dismissgroup(String uid, String qid) {
        model.dismissgroup(context,uid,qid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

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
    public void checkIsInGroup(String uid, String qid) {
        model.checkIsInGroup(context,uid,  qid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().getGroupInfoSuccess(null);
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
    public void applyToFriends(String fromwho, String towho,String remark) {
        model.applyToFriends(context,fromwho,towho,remark,getView().bindLifecycle(), new ObserverResponseListener<String>() {

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

    @Override
    public void setRemark(String uid, String tid, String name) {
        model.setRemark(context, uid,  tid,  name,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().setRemarkSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getNicknameByAdd(String address) {
        model.getNicknameByAdd(context,address,getView().bindLifecycle(), new ObserverResponseListener<GroupMember>() {

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
    public void addGroupMember(String uid, String tid, String qid) {
        model.addGroupMember(context,uid,  tid,  qid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().addGroupMemberSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void removeGroupMenber(String uid, String tid, String qid) {
        model.removeGroupMenber(context,uid,  tid,  qid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().removeGroupMenberSuccess(string);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void upDataGroupAvatar(String uid, String qid, MultipartBody.Part userphone) {
        model.upDataGroupAvatar(context,uid,  qid,  userphone,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().upDataAvatarSuccess(string);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void upDataGroupName(String uid, String qid, String name) {
        model.upDataGroupName(context,uid,  qid,  name,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().upDataGroupNameSuccess(string);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void upDataGroupExplain(String uid, String qid, String introduce) {
        model.upDataGroupExplain(context,uid,  qid,  introduce,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().upDataGroupExplainSuccess(string);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addGroupAddressBook(String uid, String qid) {
        model.addGroupAddressBook(context,uid,  qid,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String string) {
                if(getView() != null){
                    getView().addGroupMemberSuccess(string);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
