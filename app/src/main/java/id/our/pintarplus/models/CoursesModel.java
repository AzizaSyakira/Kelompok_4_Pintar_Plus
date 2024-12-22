package id.our.pintarplus.models;

public class CoursesModel {
    private String id;
    private String courses;
    private String judul;
    private String link;
    private String matpel_id;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMatpelId() {
        return matpel_id;
    }

    public void setMatpelId(String matpel_id) {
        this.matpel_id = matpel_id;
    }
}

