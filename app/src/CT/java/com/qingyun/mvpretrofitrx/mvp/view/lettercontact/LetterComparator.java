package com.qingyun.mvpretrofitrx.mvp.view.lettercontact;

import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.bean.City;

import java.util.Comparator;


public class LetterComparator implements Comparator<GroupMember> {

    @Override
    public int compare(GroupMember l, GroupMember r) {
        if (l == null || r == null) {
            return 0;
        }

        String lhsSortLetters = l.getPinyin().substring(0, 1).toUpperCase();
        String rhsSortLetters = r.getPinyin().substring(0, 1).toUpperCase();
        if (lhsSortLetters == null || rhsSortLetters == null) {
            return 0;
        }
        return lhsSortLetters.compareTo(rhsSortLetters);
    }
}