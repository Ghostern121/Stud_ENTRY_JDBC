// Student.java
public class Student {
    private String prn;
    private String name;
    private String batch;
    private String email;

    public Student(String prn, String name, String batch, String email) {
        this.prn = prn;
        this.name = name;
        this.batch = batch;
        this.email = email;
    }

    public String getPrn() { return prn; }
    public String getName() { return name; }
    public String getBatch() { return batch; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setBatch(String batch) { this.batch = batch; }
    public void setEmail(String email) { this.email = email; }