package pl.coderslab.model;

public class Plan {
    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public Plan() {
    }

    public Plan(int id, String name, String created, String description, int adminId) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.description = description;
        this.adminId = adminId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
