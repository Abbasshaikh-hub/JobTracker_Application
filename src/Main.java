import java.util.ArrayList;
import java.util.Scanner;

class main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        // 1. Create instance of our service layer
        JobService jobService = new JobService();

        // 2. Load jobs automatically through our FileHandler utility
        FileHandler.loadJobs(jobService);

        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.println("___ Add a New Job Target___ \n");
                    addJob(sc, jobService);
                }
                case 2 -> viewJob(jobService);
                case 3 -> updateStatus(sc, jobService);
                case 4 -> deleteJob(sc, jobService);
                case 5 -> searchJob(sc, jobService);
                case 6 -> {
                    System.out.println("Saving data...");
                    FileHandler.saveJobs(jobService);
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please pick 1, 2, 3, 4, 5, or 6.");
            }
        }
        sc.close();
    }

    public static void addJob(Scanner sc, JobService service) {
        System.out.println("Enter a Company: ");
        String company = sc.nextLine();
        System.out.println("Enter a Job Title: (e.g., Backend Developer) ");
        String title = sc.nextLine();
        System.out.println("Enter a Status: (e.g., Applied, Working) ");
        String status = sc.nextLine();

        // Let the service process the execution creation
        service.addJob(company, title, status);
        System.out.println("Added to your List");
    }

    public static void viewJob(JobService service) {
        if (service.isEmpty()) {
            System.out.println("\n[!] The List is EMPTY.");
            System.out.println("[!] First, you have to add something.");
            return;
        }

        System.out.println("\n--- My Saved Jobs ---");
        for (Job currentJob : service.getAllJobs()) {
            System.out.println(currentJob);
        }
    }

    public static void updateStatus(Scanner sc, JobService service) {
        if (service.isEmpty()) {
            System.out.println("\n[!] The list is empty. Add a job first!");
            return;
        }

        System.out.println("\n--- Select a Job to Update ---");
        for (int i = 0; i < service.getSize(); i++) {
            Job job = service.getJob(i);
            System.out.println((i + 1) + " ." + job.getCompany() + " " + job.getRole());
        }

        System.out.print("\nEnter the number of the job: ");
        int jobchoice = sc.nextInt();
        sc.nextLine();
        int actualIndex = jobchoice - 1;

        System.out.print("Enter new status (e.g., Interview, Rejected, Offer): ");
        String newStatus = sc.nextLine();

        if (service.updateStatus(actualIndex, newStatus)) {
            System.out.println("\nSuccess! Status updated.");
        } else {
            System.out.println("Invalid job number.");
        }
    }

    public static void deleteJob(Scanner sc, JobService service) {
        if (service.isEmpty()) {
            System.out.println("\n[!] The list is empty. Add a job first!");
            return;
        }

        System.out.println("\n--- Select a Job to Delete ---");
        for (int i = 0; i < service.getSize(); i++) {
            Job job = service.getJob(i);
            System.out.println((i + 1) + " ." + job.getCompany() + " " + job.getRole() + " " + job.getStatus());
        }

        System.out.print("\nEnter the number of the job: ");
        int jobchoice = sc.nextInt();
        sc.nextLine();
        int actualIndex = jobchoice - 1;

        if (service.deleteJob(actualIndex)) {
            System.out.println("\nSuccess! Job Deleted.");
        } else {
            System.out.println("Invalid job number.");
        }
    }

    public static void searchJob(Scanner sc, JobService service) {
        if (service.isEmpty()) {
            System.out.println("No jobs saved yet.");
            return;
        }

        System.out.println("Enter a Company Name: ");
        String searchText = sc.nextLine();

        // Ask the service layer to do the calculation loop
        ArrayList<Job> foundJobs = service.searchJobsByCompany(searchText);

        if (foundJobs.isEmpty()) {
            System.out.println("No jobs found for " + searchText);
        } else {
            System.out.println("Found! ");
            for (Job job : foundJobs) {
                System.out.println(job);
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a new Job");
        System.out.println("2. View all Jobs");
        System.out.println("3. Update status");
        System.out.println("4. Delete Job");
        System.out.println("5. Search Job");
        System.out.println("6. Save Job & Exit");
        System.out.print("Choose an option: ");
    }
}