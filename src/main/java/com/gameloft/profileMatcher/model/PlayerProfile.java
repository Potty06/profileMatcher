package com.gameloft.profileMatcher.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

// PlayerProfile.java
@Data
public class PlayerProfile {
    private String playerId;
    private String credential;
    private String created;
    private String modified;
    private String lastSession;
    private int totalSpent;
    private int totalRefund;
    private int totalTransactions;
    private String lastPurchase;
    private List<String> activeCampaigns;
    private List<Device> devices;
    private int level;
    private int xp;
    private int totalPlaytime;
    private String country;
    private String language;
    private String birthdate;
    private String gender;
    private Map<String, Integer> inventory;
    private Clan clan;
    private String customField;

    @Data
    public static class Device {
        private int id;
        private String model;
        private String carrier;
        private String firmware;
    }

    @Data
    public static class Clan {
        private String id;
        private String name;
    }
}
