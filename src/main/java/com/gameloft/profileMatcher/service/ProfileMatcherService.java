package com.gameloft.profileMatcher.service;

import com.gameloft.profileMatcher.model.CampaignData;
import com.gameloft.profileMatcher.model.PlayerProfile;
import com.gameloft.profileMatcher.repository.ProfileRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

// ProfileMatcherService.java
@Service
public class ProfileMatcherService {


    private final ProfileRepository profileRepository;

    public ProfileMatcherService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public PlayerProfile getClientConfig(String playerId) {
        // Retrieve player profile from the database
        PlayerProfile playerProfile = profileRepository.findById(playerId).orElse(null);

        if (playerProfile != null) {
            // Retrieve the current campaign data (mocked here, replace with actual logic)
            CampaignData currentCampaignData = getMockCampaignData();

            // Check if the player profile matches the current campaign conditions
            if (matchesCampaign(playerProfile, currentCampaignData)) {
                // Update player profile with the active campaign
                playerProfile.getActiveCampaigns().add(currentCampaignData.getName());
                // Save the updated profile to the database
                profileRepository.save(playerProfile);
            }
        }

        return playerProfile;
    }

    private boolean matchesCampaign(PlayerProfile playerProfile, CampaignData campaignData) {
        // Check level range
        int playerLevel = playerProfile.getLevel();
        CampaignData.Range levelRange = campaignData.getMatchers().getLevel();
        if (playerLevel < levelRange.getMin() || playerLevel > levelRange.getMax()) {
            return false;
        }

        // Check country and item conditions
        String playerCountry = playerProfile.getCountry();
        List<String> allowedCountries = campaignData.getMatchers().getHas().getCountry();
        if (!allowedCountries.contains(playerCountry)) {
            return false;
        }

        Map<String, Integer> playerInventory = playerProfile.getInventory();
        List<String> requiredItems = campaignData.getMatchers().getHas().getItems();
        List<String> restrictedItems = campaignData.getMatchers().getDoesNotHave().getItems();

        for (String requiredItem : requiredItems) {
            if (!playerInventory.containsKey(requiredItem) || playerInventory.get(requiredItem) <= 0) {
                return false; // Player doesn't have the required item
            }
        }

        for (String restrictedItem : restrictedItems) {
            if (playerInventory.containsKey(restrictedItem) && playerInventory.get(restrictedItem) > 0) {
                return false; // Player has a restricted item
            }
        }

        // If all conditions are met, return true
        return true;
    }


    private CampaignData getMockCampaignData() {
        // Mock campaign data
        CampaignData campaignData = new CampaignData();
        campaignData.setGame("mygame");
        campaignData.setName("mycampaign");
        campaignData.setPriority(10.5);

        // Set matchers
        CampaignData.Matchers matchers = new CampaignData.Matchers();
        CampaignData.Range levelRange = new CampaignData.Range();
        levelRange.setMin(1);
        levelRange.setMax(3);
        matchers.setLevel(levelRange);

        CampaignData.Has has = new CampaignData.Has();
        has.setCountry(Arrays.asList("US", "RO", "CA"));
        has.setItems(Arrays.asList("item_1"));
        matchers.setHas(has);

        CampaignData.DoesNotHave doesNotHave = new CampaignData.DoesNotHave();
        doesNotHave.setItems(Arrays.asList("item_4"));
        matchers.setDoesNotHave(doesNotHave);

        campaignData.setMatchers(matchers);

        return campaignData;
    }

}

