package com.jaypal.journalApp.scheduler;

import com.jaypal.journalApp.cache.AppCache;
import com.jaypal.journalApp.entity.JournalEntry;
import com.jaypal.journalApp.entity.User;
import com.jaypal.journalApp.enums.Sentiment;
import com.jaypal.journalApp.repository.UserRepositoryImpl;
import com.jaypal.journalApp.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    private EmailService emailService;
    private UserRepositoryImpl userRepository;
    private AppCache appCache;

    public UserScheduler(EmailService emailService, UserRepositoryImpl userRepository, AppCache appCache) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.appCache = appCache;
    }


    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate()
                            .isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment).toList();

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(),
                        "Sentiment for last 7 days",
                        mostFrequentSentiment.toString());
            }
        }

    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
