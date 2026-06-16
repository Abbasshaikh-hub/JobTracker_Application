import java.util.ArrayList;

public class JobService {
    // 1. The private list container
    private final ArrayList<Job> jobs;

    // 2. The constructor initializing the empty list
    public JobService() {
        this.jobs = new ArrayList<>();
    }

    // Give Main a way to see the jobs (used for viewing and saving)
    public ArrayList<Job> getAllJobs() {
        return jobs;
    }

    public boolean isEmpty() {
        return jobs.isEmpty();
    }

    public int getSize() {
        return jobs.size();
    }

    // --- CRUD Business Logic methods ---

    public void addJob(String company, String role, String status) {
        jobs.add(new Job(company, role, status));
    }

    public Job getJob(int index) {
        if (index >= 0 && index < jobs.size()) {
            return jobs.get(index);
        }
        return null;
    }

    public boolean updateStatus(int index, String newStatus) {
        if (index >= 0 && index < jobs.size()) {
            jobs.get(index).setStatus(newStatus);
            return true;
        }
        return false;
    }

    public boolean deleteJob(int index) {
        if (index >= 0 && index < jobs.size()) {
            jobs.remove(index);
            return true;
        }
        return false;
    }

    // Searches the list and returns an arraylist of matching items
    public ArrayList<Job> searchJobsByCompany(String searchText) {
        ArrayList<Job> results = new ArrayList<>();
        for (Job currentJob : jobs) {
            if (currentJob.getCompany().equalsIgnoreCase(searchText)) {
                results.add(currentJob);
            }
        }
        return results;
    }

    // Clear function specifically used by the FileHandler during loading
    public void clearAll() {
        jobs.clear();
    }
}