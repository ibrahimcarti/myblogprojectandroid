package com.example.finalexam;

public class UserModel {

    private String userId;
    private String userName;
    private String userMail;
    private static String userNumber;
    private String userBio;
    private String userProfile;
    private String userCover;

    public UserModel() {
    }

    public UserModel(String userId, String userName, String userMail, String userNumber, String userBio, String userProfile, String userCover) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userNumber = userNumber;
        this.userBio = userBio;
        this.userProfile = userProfile;
        this.userCover = userCover;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public static String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserCover() {
        return userCover;
    }

    public void setUserCover(String userCover) {
        this.userCover = userCover;
    }
}


