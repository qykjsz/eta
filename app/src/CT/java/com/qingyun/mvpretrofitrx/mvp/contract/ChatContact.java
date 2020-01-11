package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;

import java.util.List;

import okhttp3.MultipartBody;


public interface ChatContact {

    interface View extends BaseView {

        void applyToFriendsSuccess(String s);
        void changeNicknameSuccess(String s);
        void setMessageReadGroupSuccess(String s);
        void getGroupChatLogSuccess(List<ChatMessage> chatMessageList);
        void registerChatSuccess(String s);
        void sendMessageToGroupSuccess(String s);
        void exitGroupSuccess(String s);
        void getGroupMemberListSuccess(List<GroupMember> groupMemberList);
        void transferGroupSuccess(String s);
        void getNicknameByAdressSuccess(GroupMember groupMember);
        void dealApplyIntoGroupApplySuccess(String s);
        void applyIntoGroupSuccess(String s);
        void setIfGroupReviewSuccess(String s);
        void getGroupListSuccess(List<Group> groupList);
        void createGroupSuccess(String s);
        void addGroupListSuccess(List<ApplyGroup> applyGroupList);
        void addFriendsListSuccess(List<ApplyGroup> applyGroupList);
        void newChaListSuccess(NewChat newChat);
        void seeChatMessageLogSuccess(List<ChatMessage> chatMessageList);
        void viewMessageSuccess(String s);
        void sendMessageSuccess(String s);
        void getFriendsListSuccess(List<GroupMember> groupMemberList);
        void applyToFriendsPassSuccess(String s);
        void applyToFriendsRefuseSuccess(String s);
        void getGroupInfoSuccess(Group group);
        void upDataAvatarSuccess(String s);
        void getChatTokenSuccess(String token);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void applyToFriendsRefuse(String fromwho,int fid);
        public abstract void applyToFriendsPass(String fromwho,int fid);
        public abstract void getFriendsList(String fromwho,String selectaddress);
        public abstract void sendMessage(String fromwho,String towho,String text);
        public abstract void viewMessage(String fromwho,String towho);
        public abstract void seeChatMessageLog(String fromwho,String towho,int page);
        public abstract void newChaList(String fromwho);
        public abstract void addFriendsList(String fromwho,int page);
        public abstract void addGroupList(String fromwho,int page);
        public abstract void createGroup(String address,String name,String introduce);
        public abstract void getGroupList(String address);
        public abstract void setIfGroupReview(String address,String code,String verification);
        public abstract void applyIntoGroup(String address,String code);

        public abstract void dealApplyIntoGroupApply(String address,String qcode,int sid,int state);
        public abstract void getNicknameByAdress(String address);
        public abstract void transferGroup(String address,String qcode,String toaddress);
        public abstract void getGroupMemberList(String address,String qcode);
        public abstract void exitGroup(String address,String qcode);
        public abstract void sendMessageToGroup(String fromwho,String togroup,String text);
        public abstract void registerChat(String address);
        public abstract void getGroupChatLog(String fromwho,String qcode,int page);
        public abstract void setMessageReadGroup(String fromwho,String qcode);
        public abstract void changeNickname(String address,String name);
        public abstract void applyToFriends(String fromwho,String towho);
        public abstract void getGroupInfo(String address,String qcode);
        public abstract void upDataAvatar(String address, MultipartBody.Part userphone);
        public abstract void getChatToken(String address);


    }
}
