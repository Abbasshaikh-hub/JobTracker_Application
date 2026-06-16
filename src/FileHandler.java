import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static final String FILE_NAME = "jobs.txt";

    public static void saveJobs(JobService jobService) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Job currentJob : jobService.getAllJobs()) {
                String jobLine = currentJob.getCompany() + "," +
                        currentJob.getRole() + "," +
                        currentJob.getStatus() + "\n";
                writer.write(jobLine);
            }
            System.out.println("All jobs successfully saved to jobs.txt!");
        } catch (IOException e) {
            System.out.println("Error while saving jobs to the file.");
            e.printStackTrace();
        }
    }

    public static void loadJobs(JobService jobService) {
        jobService.clearAll(); // Wipe whatever is currently in memory

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    // Send directly to the Service Layer
                    jobService.addJob(parts[0].trim(), parts[1].trim(), parts[2].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("ℹ️ No existing job data found. Starting fresh.");
        }
    }
}