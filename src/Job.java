public class Job {
    private String company;
    private String role;
    private String status;

    public Job(String company, String role, String status){
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public String getCompany(){
        return company;
    }

    public String getRole() {
        return role;
    }
    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" +
                "company : '" + company + '\'' +
                ", role : '" + role + '\'' +
                ", status : '" + status + '\'' +
                '}';
    }
}
