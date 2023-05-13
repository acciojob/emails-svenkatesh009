package com.driver;

public class Email {

    private final String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        if(newPassword.contains(" ")) return;
        if(oldPassword.compareTo(this.password)!=0) return;
        if(newPassword.length()<8) return;
        int upper=0;
        int lower=0;
        int digit=0;
        int special=0;
        int n=newPassword.length();
        for(int i=0;i<n;i++){
            if(newPassword.charAt(i)>='A' && newPassword.charAt(i)<='Z') upper++;
            else if(newPassword.charAt(i)>='a' && newPassword.charAt(i)<='z') lower++;
            else if(newPassword.charAt(i)>='0' && newPassword.charAt(i)<='9') digit++;
            else special++;
        }
        if(upper>0 && lower>0 && special>0 && digit>0 ){
            this.password=newPassword;
        }
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }
}
