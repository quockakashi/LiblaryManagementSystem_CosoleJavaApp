package libraryManagement.services;

import libraryManagement.entities.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberManagementService {

    private static MemberManagementService instance;
    private List<Member> members;

    public static MemberManagementService getInstance() {
        if(instance == null)
            instance = new MemberManagementService();
        return instance;
    }

    private MemberManagementService() {
        members = new ArrayList<>();
    }


    public boolean isUniqueEmail(String email) {
        for(var member : members) {
            if(member != null && member.getEmail().equalsIgnoreCase(email))
                return false;
        }

        return true;
    }
    public void register(Member member) {
        if(members == null) {
            members = new ArrayList<>();
        }
        if(member == null)
            return;
        members.add(member);
    }

    public Member findMember(String email, String password) {
        if(members == null)
            return  null;
        for(var member : members) {
            if(member.getEmail().equalsIgnoreCase(email) && member.getPassword().equals(password))
                return member;
        }

        return  null;
    }

}
