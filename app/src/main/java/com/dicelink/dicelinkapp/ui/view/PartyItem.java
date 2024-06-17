package com.dicelink.dicelinkapp.ui.view;

import java.util.List;

public class PartyItem {
    private int mainUserImage;
    private String mainUserName;
    private int userIcon1, userIcon2, userIcon3;
    private String userName1, userName2, userName3;
    private String userRole1, userRole2, userRole3;
    private String userRoleType1, userRoleType2, userRoleType3;
    private String dungeonMasterStatus;
    private String distance;
    private int partyImage;
    private List<AdditionalUser> additionalUsers;

    public PartyItem(int mainUserImage, String mainUserName, int userIcon1, int userIcon2, int userIcon3,
                     String userName1, String userName2, String userName3,
                     String userRole1, String userRole2, String userRole3,
                     String userRoleType1, String userRoleType2, String userRoleType3,
                     String dungeonMasterStatus, String distance, int partyImage) {
        this.mainUserImage = mainUserImage;
        this.mainUserName = mainUserName;
        this.userIcon1 = userIcon1;
        this.userIcon2 = userIcon2;
        this.userIcon3 = userIcon3;
        this.userName1 = userName1;
        this.userName2 = userName2;
        this.userName3 = userName3;
        this.userRole1 = userRole1;
        this.userRole2 = userRole2;
        this.userRole3 = userRole3;
        this.userRoleType1 = userRoleType1;
        this.userRoleType2 = userRoleType2;
        this.userRoleType3 = userRoleType3;
        this.dungeonMasterStatus = dungeonMasterStatus;
        this.distance = distance;
        this.partyImage = partyImage;
    }

    public class PartyItemCONST {
        private int mainUserImage;
        private String mainUserName;
        private int userIcon1, userIcon2, userIcon3;
        private String userName1, userName2, userName3;
        private String userRole1, userRole2, userRole3;
        private String userRoleType1, userRoleType2, userRoleType3;
        private String dungeonMasterStatus;
        private String distance;
        private int partyImage;
        private List<AdditionalUser> additionalUsers; // New list for additional users
    }

    public List<AdditionalUser> getAdditionalUsers() {
        return additionalUsers;
    }

    public void setAdditionalUsers(List<AdditionalUser> additionalUsers) {
        this.additionalUsers = additionalUsers;
    }

    // AdditionalUser class
    public class AdditionalUser {
        private int icon;
        private String name;
        private String role;
        private String roleType;

        public AdditionalUser(int icon, String name, String role, String roleType) {
            this.icon = icon;
            this.name = name;
            this.role = role;
            this.roleType = roleType;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRoleType() {
            return roleType;
        }

        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }
    }

    public PartyItem() {

    }

    public int getMainUserImage() {
        return mainUserImage;
    }

    public void setMainUserImage(int mainUserImage) {
        this.mainUserImage = mainUserImage;
    }

    public String getMainUserName() {
        return mainUserName;
    }

    public void setMainUserName(String mainUserName) {
        this.mainUserName = mainUserName;
    }

    public int getUserIcon1() {
        return userIcon1;
    }

    public void setUserIcon1(int userIcon1) {
        this.userIcon1 = userIcon1;
    }

    public int getUserIcon2() {
        return userIcon2;
    }

    public void setUserIcon2(int userIcon2) {
        this.userIcon2 = userIcon2;
    }

    public int getUserIcon3() {
        return userIcon3;
    }

    public void setUserIcon3(int userIcon3) {
        this.userIcon3 = userIcon3;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserName3() {
        return userName3;
    }

    public void setUserName3(String userName3) {
        this.userName3 = userName3;
    }

    public String getUserRole1() {
        return userRole1;
    }

    public void setUserRole1(String userRole1) {
        this.userRole1 = userRole1;
    }

    public String getUserRole2() {
        return userRole2;
    }

    public void setUserRole2(String userRole2) {
        this.userRole2 = userRole2;
    }

    public String getUserRole3() {
        return userRole3;
    }

    public void setUserRole3(String userRole3) {
        this.userRole3 = userRole3;
    }

    public String getUserRoleType1() {
        return userRoleType1;
    }

    public void setUserRoleType1(String userRoleType1) {
        this.userRoleType1 = userRoleType1;
    }

    public String getUserRoleType2() {
        return userRoleType2;
    }

    public void setUserRoleType2(String userRoleType2) {
        this.userRoleType2 = userRoleType2;
    }

    public String getUserRoleType3() {
        return userRoleType3;
    }

    public void setUserRoleType3(String userRoleType3) {
        this.userRoleType3 = userRoleType3;
    }

    public String getDungeonMasterStatus() {
        return dungeonMasterStatus;
    }

    public void setDungeonMasterStatus(String dungeonMasterStatus) {
        this.dungeonMasterStatus = dungeonMasterStatus;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getPartyImage() {
        return partyImage;
    }

    public void setPartyImage(int partyImage) {
        this.partyImage = partyImage;
    }
}
