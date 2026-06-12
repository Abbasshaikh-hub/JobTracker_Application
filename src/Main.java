import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

class main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Job> jobs = new ArrayList<>();
        loadJobs(jobs);

        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("___ Add a New Job Target___");
                System.out.println();
                addJob(sc, jobs);
            } else if (choice == 2) {
                viewJob(jobs);
            } else if (choice == 3) {
                updateStatus(sc, jobs);
            } else if (choice == 4) {
                deleteJob(sc, jobs);
            } else if (choice == 5) {
                searchJob(sc, jobs);
            } else if (choice == 6) {
                saveJobs(jobs);
                running = false;
            } else if (choice == 7) {
                loadJobs(jobs);
            } else if (choice == 8) {
                System.out.println("Shutting down tracker. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice. Please pick 1, 2, 3, or 4.");
            }
        }
        sc.close();
    }

    public static void addJob(Scanner sc, ArrayList<Job> jobs) {
        System.out.println("Enter a Company: ");
        String company = sc.nextLine();
        System.out.println("Enter a Job Title: (e.g., Backend Developer) ");
        String title = sc.nextLine();
        System.out.println("Enter a Status: (e.g., Applied,Working ) ");
        String status = sc.nextLine();
        jobs.add(new Job(company, title, status));
        System.out.println("Added to your List");
    }

    public static void viewJob(ArrayList<Job> jobs) {
        if (jobs.isEmpty()) {
            System.out.println("\n[!] The List is EMPTY.");
            System.out.println("[!] First, you have to add something.");
            return;
        } else {
            System.out.println("\n--- My Saved Jobs ---");
            for (Job currentJob : jobs) {
                System.out.println(currentJob);
            }
        }
    }

    public static void updateStatus(Scanner sc, ArrayList<Job> jobs) {
        if (jobs.isEmpty()) {
            System.out.println("\n[!] The list is empty. Add a job first!");
            return;
        } else {
            System.out.println("\n--- Select a Job to Update ---");

            for (int i = 0; i < jobs.size(); i++) {
                System.out.println((i + 1) + " ." + jobs.get(i).getCompany() + " " + jobs.get(i).getRole());
            }

            System.out.print("\nEnter the number of the job: ");
            int jobchoice = sc.nextInt();
            sc.nextLine();
            int actualIndex = jobchoice - 1;

            System.out.print("Enter new status (e.g., Interview, Rejected, Offer): ");
            String newStatus = sc.nextLine();
            if (actualIndex >= 0 && actualIndex < jobs.size()) {
                jobs.get(actualIndex).setStatus(newStatus);
                System.out.println("\nSuccess! Status updated.");
            } else {
                System.out.println("Invalid job number.");
            }
        }
    }

    public static void deleteJob(Scanner sc, ArrayList<Job> jobs) {
        if (jobs.isEmpty()) {
            System.out.println("\n[!] The list is empty. Add a job first!");
            return;
        } else {
            System.out.println("\n--- Select a Job to Delete ---");

            for (int i = 0; i < jobs.size(); i++) {
                System.out.println((i + 1) + " ." + jobs.get(i).getCompany() + " " + jobs.get(i).getRole() + " " + jobs.get(i).getStatus());
            }

            System.out.print("\nEnter the number of the job: ");
            int jobchoice = sc.nextInt();
            sc.nextLine();
            int actualIndex = jobchoice - 1;
            if (actualIndex >= 0 && actualIndex < jobs.size()) {
                jobs.remove(actualIndex);
                System.out.println("\nSuccess! Job Delete.");
            } else {
                System.out.println("Invalid job number.");
            }
        }
    }

    public static void searchJob(Scanner sc, ArrayList<Job> jobs) {
        if (jobs.isEmpty()) {
            System.out.println("No jobs saved yet.");
            return;
        }
        System.out.println("Enter a Company Name: ");
        String searchText = sc.nextLine();
        boolean found = false;

        for (Job currentJob : jobs) {

            if (currentJob.getCompany().equalsIgnoreCase(searchText)) {
                System.out.println("Found! ");
                System.out.println(currentJob);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No jobs found for " + searchText);
        }
    }

    public static void saveJobs(ArrayList<Job> jobs) {
        try (FileWriter writer = new FileWriter("jobs.txt");) {

            for (Job currentJob : jobs) {
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

    public static void loadJobs(ArrayList<Job> jobs) {
        try (BufferedReader reader = new BufferedReader(new FileReader("jobs.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {

        }
    }

    public static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new Job");
        System.out.println("2. View all Jobs");
        System.out.println("3. Update status");
        System.out.println("4. Delete Job");
        System.out.println("5. Search Job");
        System.out.println("6. Save Job");
        System.out.println("7. Load Job");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }
}