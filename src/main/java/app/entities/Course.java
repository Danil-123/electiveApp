package app.entities;

import java.io.Serializable;

/**
 * @author Danil Shatov
 */

public class Course implements Serializable {
    /**
     * id in database
     */
    private int id;

    /**
     * name of course
     */
    private String course_name;

    /**
     * topic of course
     */
    private String course_topic;

    /**
     * course start day
     */
    private String start_day;

    /**
     * duration of course
     */
    private String duration;

    /**
     * course description
     */
    private String description;

    /**
     * course finish date
     */
    private String last_day;

    /**
     * number of students on the course
     */
    private int numOfStudents;

    public Course(int id, String course_name, String course_topic, String start_day) {
        this.id = id;
        this.course_name = course_name;
        this.course_topic = course_topic;
        this.start_day = start_day;
    }

    public Course() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_topic() {
        return course_topic;
    }

    public void setCourse_topic(String course_topic) {
        this.course_topic = course_topic;
    }

    public String getStart_day() {return start_day;}

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public String getLast_day() {
        return last_day;
    }

    public void setLast_day(String last_day) {
        this.last_day = last_day;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", course_topic='" + course_topic + '\'' +
                ", start_day='" + start_day + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", last_day='" + last_day + '\'' +
                ", numOfStudents=" + numOfStudents +
                '}';
    }
}
