package com.prolink.prolink.service;


import com.prolink.prolink.domain.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookNotificationService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.webhooks.enabled:false}")
    private boolean webhooksEnabled;

    @Value("${app.webhooks.profile-improvement-url:}")
    private String profileImprovementWebhookUrl;

    public void sendProfileImprovementWebhook(Profile profile) {
        if (!webhooksEnabled || profileImprovementWebhookUrl == null || profileImprovementWebhookUrl.isBlank()) {
            return;
        }

        Map<String, Object> payload = new HashMap<>();

        payload.put("eventType", "PROFILE_IMPROVEMENT_REQUESTED");
        payload.put("profileId", profile.getIdProfile());
        payload.put("profileName", profile.getName());
        payload.put("location", profile.getLocation());
        payload.put("personalDetails", profile.getPersonalDetails());
        payload.put("requestedAt", LocalDateTime.now());

        sendWebhook(profileImprovementWebhookUrl, payload);
    }

    private void sendWebhook(String webhookUrl, Map<String, Object> payload) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

            restTemplate.postForEntity(webhookUrl, request, String.class);
        } catch (Exception ex) {
            System.out.println("Webhook failed: " + ex.getMessage());
        }
    }
}
