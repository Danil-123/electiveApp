package app.model.command;

import app.db.DBException;
import app.db.DBManager;
import app.entities.Course;
import app.entities.Topic;
import app.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CourseLogic {

    private static final String SQL_SUBLIST = "SELECT * FROM"
            + " account where role = 'STUDENT' LIMIT %d OFFSET %d";

    public static List<User> pageOfStudent(int firstrow, int rowcount) throws DBException {
        String sql = String.format(SQL_SUBLIST, firstrow, rowcount);
        return DBManager.getInstance().findPageOfStudents(sql);
    }

    public static List<Topic> getAllTopic() throws DBException {
        return DBManager.getInstance().findAllTopic();
    }

    public static List<Course> getCoursesByTopic(String name) throws DBException {
        return DBManager.getInstance().findCoursesByTopic(name);
    }

    public static Course getCourse(int id) throws DBException {
        return DBManager.getInstance().findCourse(id);
    }

    public static boolean addStudentToCourse(int course_id, int student_id) {
        return DBManager.getInstance().insertStudentToCourse(course_id, student_id);
    }

    public static List<Course> getAllCoursesByStudent(int id) throws DBException {
        return DBManager.getInstance().findAllCoursesByStudent(id);
    }


    public static List<Course> checkStartedCourses(List<Course> courses) throws ParseException, DBException {
        List<Course> courseList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        Calendar calendar = null;
        DBManager db = DBManager.getInstance();
        for (Course course : courses) {
            calendar = new GregorianCalendar();
            String[] duration = db.getDuration(course).split(" ");
            Date startDate = sdf.parse(course.getStart_day());
            if (startDate.compareTo(todayDate) < 0) {
                calendar.setTime(startDate);
                calendar.add(Calendar.MONTH, Integer.parseInt(duration[0]));
                if (calendar.getTime().compareTo(todayDate) > 0) {
                    courseList.add(course);
                }
            }
        }

        return courseList;
    }

    public static List<Course> checkFinishedCourses(List<Course> courses) throws ParseException, DBException {
        List<Course> courseList = new ArrayList<>();
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = null;
        DBManager db = DBManager.getInstance();
        for (Course course : courses) {
            String[] duration = db.getDuration(course).split(" ");
            calendar = new GregorianCalendar();
            Date startDate = sdf.parse(course.getStart_day());
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH, Integer.parseInt(duration[0]));
            if (calendar.getTime().compareTo(todayDate) < 0) {
                course.setLast_day(sdf.format(calendar.getTime()));
                courseList.add(course);
            }
        }
        return courseList;
    }

    public static List<Course> checkNotStartedCourses(List<Course> courses) throws ParseException {
        List<Course> courseList = new ArrayList<>();
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Course course : courses) {
            Date startDate = sdf.parse(course.getStart_day());

            if (startDate.compareTo(todayDate) > 0) {
                courseList.add(course);
            }
        }
        return courseList;
    }


    public static boolean editCourse(String name, String newName) {
        return DBManager.getInstance().editCourse(name, newName);
    }

    public static boolean delCourse(String id_course) {
        return DBManager.getInstance().delCourse(id_course);
    }

    public static List<User> getAllStudents(int id) throws DBException {
        return DBManager.getInstance().findAllStudents(id);
    }

    public static List<User> getAllTeacher() throws DBException {
        return DBManager.getInstance().findAllTeacher();
    }

    public static boolean addTopic(String topicName) {
        return DBManager.getInstance().createTopic(topicName);
    }

    public static boolean setNewTeacher(String id_course, String id_account) {
        return DBManager.getInstance().setNewTeacher(id_course, id_account);
    }

    public static boolean delStudentFromCourse(int id_student, int id_course) {
        return DBManager.getInstance().delStudent(id_student, id_course);
    }

    public static boolean changeDecsription(int id_course, String description) {
        return DBManager.getInstance().changeDescription(id_course, description);
    }

    public static boolean createCourse(String courseName, String topicName, String start_day) {
        return DBManager.getInstance().createCourse(courseName, topicName, start_day);
    }

    public static boolean setTeacher(String id_course, String id_teacher) {
        return DBManager.getInstance().setTeacher(id_course, id_teacher);
    }

    public static boolean findTeacher(String id_course) {
        return DBManager.getInstance().checkTeacher(id_course);
    }

    public static boolean findDescription(int id_course) {
        return DBManager.getInstance().checkDescription(id_course);
    }

    public static boolean setDescription(int id_course, String description) {
        return DBManager.getInstance().setDescription(id_course, description);
    }

    public static boolean changeDuration(String id_course, String duration) {
        return DBManager.getInstance().changeDuration(id_course, duration);
    }

    public static List<User> getAllStudent() throws DBException {
        return DBManager.getInstance().findAllStudent();
    }

    public static boolean checkCourse(String courseName) {
        return DBManager.getInstance().checkCourse(courseName);
    }

    public static boolean createCourseTheSameName(String courseName, String topicName, String start_day) {
        return DBManager.getInstance().updateTopicOfCourse(courseName, topicName, start_day);
    }

    public static List<Course> getCoursesByTeacher(String id_teacher) throws DBException {
        return DBManager.getInstance().findAllCoursesByTeacher(id_teacher);
    }

    public static List<Course> findMarkToFinishedCourse(int id, List<Course> finishedCourses) throws DBException {
        return DBManager.getInstance().findFinishedCourse(id, finishedCourses);
    }

    public static boolean delTopic(String name) {
        return DBManager.getInstance().delTopic(name);
    }

    public static int numOfStudent() throws DBException {
        return DBManager.getInstance().getNumOfAllStudents();
    }

    public static List<Topic> getAllDelTopic() throws DBException {
        return DBManager.getInstance().getAllDelTopic();
    }

    public static boolean changeDelStatus(String name) {
        return DBManager.getInstance().changeDelStatus(name);
    }

    public static String findMarkOfCourse(int id_student, int id_course) throws DBException {
        return DBManager.getInstance().findMarkOfCourse(id_student, id_course);
    }
}
