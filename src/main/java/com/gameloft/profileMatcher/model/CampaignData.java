package com.gameloft.profileMatcher.model;

import java.util.List;
import lombok.Data;

// CampaignData.java
@Data
public class CampaignData {
    private String game;
    private String name;
    private double priority;
    private Matchers matchers;
    private String startDate;
    private String endDate;
    private boolean enabled;
    private String lastUpdated;

    @Data
    public static class Matchers {
        private Range level;
        private Has has;
        private DoesNotHave doesNotHave;
    }

    @Data
    public static class Range {
        private int min;
        private int max;
    }

    @Data
    public static class Has {
        private List<String> country;
        private List<String> items;
    }

    @Data
    public static class DoesNotHave {
        private List<String> items;
    }
}

