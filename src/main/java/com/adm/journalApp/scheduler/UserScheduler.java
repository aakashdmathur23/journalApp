package com.adm.journalApp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adm.journalApp.service.EmailService;
import com.adm.journalApp.entity.JournalEntry;
import com.adm.journalApp.entity.User;
import com.adm.journalApp.repository.UserRepositoryImpl;
import com.adm.journalApp.enums.Sentiment;

// You don't need @Data here, @Component is enough
@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    // This service isn't used in this method, which is fine
    // @Autowired
    // private SentimentAnalysisService sentimentAnalysisService;

    /**
     * Runs at 9:00 AM every Sunday.
     */
    @Scheduled(cron = "0 0 9 * * SUN") // <-- FIXED CRON
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)
                    .collect(Collectors.toList());

            // 1. First, build the map completely
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            // 2. NOW, find the most frequent sentiment
            Sentiment mostFrequentSentiment = null; // (Typo "frequient" fixed)
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            // 3. Send the email (if a sentiment was found)
            if (mostFrequentSentiment != null) {
                // FIX: Splitting the message into three arguments: TO, SUBJECT, and BODY
                emailService.sendEmail(
                        user.getEmail(),                             // 1. TO (user's email)
                        "Weekly Sentiment Summary",                  // 2. SUBJECT (The subject line)
                        "Your most frequent sentiment for the last 7 days was: " + mostFrequentSentiment.name() // 3. BODY (The content)
                );
            }
        }
    }
}