package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId);
        inboxCapacity=Integer.MAX_VALUE;
        calendar=new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        if(calendar.size()==Integer.MAX_VALUE) calendar.remove(0);
        Meeting nm=meeting;
        calendar.add(nm);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.size()==0) return 0;
        int count=1;
        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if(o1.getEndTime().isBefore(o2.getEndTime()) )return -1;
                if(o1.getStartTime().isAfter(o2.getStartTime())) return 1;
                else return 0;
            }
        });
        LocalTime prevStart=calendar.get(0).getStartTime();
        LocalTime prevEnd=calendar.get(0).getEndTime();
        int n=calendar.size();
        for(int i=1;i<n;i++){
            LocalTime currStart=calendar.get(i).getStartTime();
            LocalTime currEnd=calendar.get(i).getEndTime();
          if(prevEnd.isBefore(currStart)){
              count++;
              prevStart=currStart;
              prevEnd=currEnd;
          }
          else{
              if(prevEnd.isBefore(currEnd)) prevEnd=currEnd;
              if(prevStart.isAfter(currStart)) prevStart=currStart;
          }
        }
        return count;
    }

}
