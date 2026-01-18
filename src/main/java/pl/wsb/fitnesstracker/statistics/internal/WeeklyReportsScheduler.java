package pl.wsb.fitnesstracker.statistics.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class WeeklyReportsScheduler {

    private final UserProvider userProvider;
    private final TrainingRepository trainingRepository;
    private final EmailSender emailSender;

    @Scheduled(fixedRate = 60000)
    public void generateWeeklyReports() {
        log.info("Starting weekly reports generation...");
        List<User> users = userProvider.findAllUsers();
        LocalDate lastWeek = LocalDate.now().minusWeeks(1);
        Date lastWeekDate = Date.from(lastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (User user : users) {
            // log.info("Checking report for user {} (ID: {})...", user.getEmail(), user.getId());
            List<Training> latestTrainings = trainingRepository.findByUserId(user.getId())
                    .stream()
                    .filter(t -> t.getStartTime().after(lastWeekDate))
                    .toList();

            log.info("User {} (ID: {}): Found {} trainings in the last week", user.getEmail(), user.getId(), latestTrainings.size());

            if (!latestTrainings.isEmpty()) {
                try {
                    sendReportEmail(user, latestTrainings);
                } catch (Exception e) {
                    log.error("Failed to send report email to user {}", user.getEmail(), e);
                }
            } else {
                log.info("Skipping email for user {} (no trainings found)", user.getEmail());
            }
        }
        log.info("Weekly reports generation finished.");
    }

    private void sendReportEmail(User user, List<Training> trainings) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(String.format("Hello %s,\n\n", user.getFirstName()));
        contentBuilder.append(String.format("You have registered %d trainings in total (last week summary).\n", trainings.size()));
        
        if (!trainings.isEmpty()) {
            contentBuilder.append("\nBreakdown by activity:\n");
            trainings.stream()
                    .collect(java.util.stream.Collectors.groupingBy(t -> t.getActivityType(), java.util.stream.Collectors.counting()))
                    .forEach((activity, count) -> 
                        contentBuilder.append(String.format("- %s: %d training(s)\n", activity, count))
                    );
        }
        
        contentBuilder.append("\nKeep it up!");
        
        EmailDto email = new EmailDto(
                user.getEmail(),
                "Weekly Training Report",
                contentBuilder.toString()
        );

        emailSender.send(email);
    }
}
