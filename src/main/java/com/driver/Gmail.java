package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {
    public Gmail(String emailId) {
        super(emailId);
    }

    class mailsInfo{
        Date date;
        String sender;
        String message;
        mailsInfo(Date date, String sender, String message){
            this.date=date;
            this.sender=sender;
            this.message=message;
        }
    }
    private ArrayList<mailsInfo> messages =new ArrayList<>();
    private ArrayList<mailsInfo> trashbin =new ArrayList<>();
    protected int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        mailsInfo m=new mailsInfo(date,sender,message);
        if(messages.size()==inboxCapacity){
            trashbin.add(messages.get(0));
            messages.remove(0);
        }
        messages.add(m);
    }

    public void deleteMail(String messag){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
            for(int i=0;i< messages.size();i++){
                if(messages.get(i).message.compareTo(messag)==0){
                    trashbin.add(messages.get(i));
                    messages.remove(i);
                }
            }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(messages.size()==0) return "";
        int n=messages.size();
        String latest=messages.get(n-1).message;
        return latest;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(messages.size()==0) return "";
        return messages.get(0).message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        int n=messages.size();
        for(int i=0;i<n;i++){
            if((messages.get(i).date.compareTo(start)==0 ||messages.get(i).date.after(start)) && (messages.get(i).date.compareTo(end)==0  ||messages.get(i).date.before(end))){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return messages.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashbin.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trashbin.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
//        return this.inboxCapacity- messages.size();
        return this.inboxCapacity;

    }
}
