package app.db;

import app.entities.Course;
import app.entities.Topic;
import app.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.db.SQLQueries.*;

public class DBManager {
    public static final Logger log = LogManager.getLogger(DBManager.class);
    private static DBManager instance;
    private DataSource ds;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/elective_db");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot obtain a connection", ex);
        }
        return con;
    }

    public List<Topic> findAllTopic() throws DBException {
        List<Topic> allTopic = new ArrayList<>();
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_TOPIC);

            while (rs.next()) {
                allTopic.add(extractTopic(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of all topics", e);
        }
        return allTopic;
    }

    public User getTeacherName(int id) throws DBException {
        User user = null;
        int id_account = 0;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_USER_BY_ID_COURSE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id_account = rs.getInt("id_account");
            }
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, id_account);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            log.info("teacher name = " + user.getName());
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot obtain teacher name", e);
        }
        return user;
    }

    public List<User> findAllTeacher() throws DBException {
        List<User> teachers = new ArrayList<>();

        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_ALL_TEACHER);
            pstmt.setString(1, "TEACHER");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                teachers.add(extractUser(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of teachers", e);
        }
        return teachers;
    }

    public List<User> findAllStudents(int id) throws DBException {
        List<User> students = new ArrayList<>();
        List<String> id_account = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_ALL_ID_STUDENT);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                id_account.add(rs.getString("student_id"));
            }
            pstmt = con.prepareStatement(SQL_FIND_ALL_STUDENT);
            PreparedStatement pstmt2 = con.prepareStatement(SQL_FIND_MARK);
            for (String id_stud : id_account) {
                pstmt2.setString(1, id_stud);
                pstmt2.setString(2, String.valueOf(id));
                pstmt.setString(1, id_stud);
                rs = pstmt.executeQuery();
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs.next()) {
                    User user = extractUser(rs);
                    if (rs2.next()) {
                        user.setMark(rs2.getString("mark"));
                    }
                    students.add(user);
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of all students", e);
        }
        return students;
    }

    public User findUser(String login) throws DBException {
        User user = null;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find user", e);
        }
        return user;
    }

    public boolean chekPhone(String phone) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_USER_BY_PHONE);
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                log.info("phone = " + rs.getString("telephone"));
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public boolean chekEmail(String email) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                log.info("email = " + rs.getString("email"));
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public boolean chekLogin(String login) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                log.info("login = " + rs.getString("login"));
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public Course findCourse(int id) throws DBException {
        Course course = null;
        String description = null;
        String duration = null;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_COURSE_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                course = extractCourse(rs);
            }
            pstmt = con.prepareStatement(SQL_FIND_COURSE_DESCRIPTION);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                description = rs.getString("course_info");
                course.setDescription(description);
            }

            pstmt = con.prepareStatement(SQL_FIND_COURSE_DURATION);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                duration = rs.getString("duration");
                course.setDuration(duration);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find course", e);
        }
        return course;
    }

    public List<Course> findCoursesByTopic(String name) throws DBException {
        List<Course> course = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_COURSES_BY_TOPIC);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                course.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of courses by topic", e);
        }
        return course;
    }

    public int getNumOfStudents(int id) throws DBException {
        int count = 0;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_USERS_IN_COURSE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            log.info("num of student: " + count);
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot count num of students", e);
        }
        return count;
    }

    public boolean checkDescription(int id_course) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_COURSE_DESCRIPTION);
            pstmt.setInt(1, id_course);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public boolean checkCourse(String courseName) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_COURSE);
            pstmt.setString(1, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public boolean checkTeacher(String id_course) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt =
                    con.prepareStatement(SQL_FIND_TEACHER_BY_COURSE);
            pstmt.setString(1, id_course);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return false;
        }
        return false;
    }

    public boolean insertStudentToCourse(int course_id, int student_id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(
                    SQL_CREATE_STUDENT,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, course_id);
            pstmt.setInt(2, student_id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            // (1) write to logger
            log.error(e);

            // (2) rollback transaction
            rollback(con);

            return false;
        } finally {
            close(con);
        }
        return true;
    }

    public List<Course> findAllCoursesByTeacher(String id_teacher) throws DBException {
        List<Course> courses = new ArrayList<>();
        List<Integer> course_idList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_COURSES_BY_USER_ID);
            pstmt.setString(1, id_teacher);
            ResultSet rs = pstmt.executeQuery();

            while ((rs.next())) {
                course_idList.add(rs.getInt("id_course"));
            }
            pstmt = con.prepareStatement(SQL_FIND_COURSE_BY_ID);
            for (Integer id_course : course_idList) {
                pstmt.setInt(1, id_course);
                rs = pstmt.executeQuery();
                if ((rs.next())) {
                    courses.add(extractCourse(rs));
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of courses by teacher", e);
        }
        return courses;
    }

    public List<Course> findAllCoursesByStudent(int id) throws DBException {
        List<Course> courses = new ArrayList<>();
        List<Integer> course_idList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_COURSES_BY_STUDENT_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while ((rs.next())) {
                course_idList.add(rs.getInt("course_id"));
            }
            pstmt = con.prepareStatement(SQL_FIND_COURSE_BY_ID);
            for (Integer id_course : course_idList) {
                pstmt.setInt(1, id_course);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Course course = extractCourse(rs);

                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of courses by student", e);
        }
        return courses;
    }

    public boolean createCourse(String courseName, String topicName, String start_day) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_COURSE);
            pstmt.setString(1, courseName);
            pstmt.setString(2, topicName);
            pstmt.setString(3, start_day);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean createTopic(String topicName) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_TOPIC);
            pstmt.setString(1, topicName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public List<Course> findFinishedCourse(int id, List<Course> finishedCourses) throws DBException {
        List<Course> courseList = new ArrayList<>();

        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_MARK);
            for (Course course : finishedCourses) {
                pstmt.setInt(1, id);
                pstmt.setInt(2, course.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    courseList.add(course);
                }
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of finished courses", e);
        }
        return courseList;
    }

    public String findMarkOfCourse(int id_student, int id_course) throws DBException {
        String mark = null;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_MARK);

            pstmt.setInt(1, id_student);
            pstmt.setInt(2, id_course);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mark = rs.getString("mark");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find mark of course", e);
        }
        return mark;
    }

    public boolean setTeacher(String id_course, String id_teacher) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_SET_TEACHER);

            pstmt.setString(1, id_teacher);
            pstmt.setString(2, id_course);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean changeDuration(String id_course, String duration) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_SET_DURATION);

            pstmt.setString(1, duration);
            pstmt.setString(2, id_course);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean createUser(User user) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER);

            pstmt.setString(1, user.getLogin());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getName());
            pstmt.setString(6, user.getSurname());
            pstmt.setString(7, user.getRole());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public String getDuration(Course course) throws DBException {
        String duration = null;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_DURATION_COURSE);
            pstmt.setInt(1, course.getId());
            ResultSet rs = pstmt.executeQuery();
            if ((rs.next())) {
                duration = rs.getString("duration");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot obtain a duration of course", e);
        }
        return duration;
    }


    public boolean delTopic(String name) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_TOPIC);

            pstmt.setString(1, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean delStudent(int id_student, int id_course) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_STUDENT_FROM_COURSE);

            pstmt.setInt(1, id_student);
            pstmt.setInt(2, id_course);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean delCourse(String id_course) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_COURSE);

            pstmt.setString(1, id_course);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean unblockStudent(String id_student) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UNBLOCK_STUDENT);

            pstmt.setString(1, id_student);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean blockStudent(String id_student) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_BLOCK_STUDENT);

            pstmt.setString(1, id_student);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean setDescription(int id_course, String description) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_DESCRIPTION);

            pstmt.setString(1, String.valueOf(id_course));
            pstmt.setString(2, description);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean setMark(String id_student, String mark, String course_id) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_MARK);
            pstmt.setString(1, mark);
            pstmt.setString(2, id_student);
            pstmt.setString(3, course_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean setNewTeacher(String id_course, String id_account) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_TEACHER);

            pstmt.setString(2, id_course);
            pstmt.setString(1, id_account);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean setAvatar(String url, String login) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_AVATAR);

            pstmt.setString(1, url);
            pstmt.setString(2, login);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean changeDescription(int id_course, String description) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_COURSE_DESCRIPTION);

            pstmt.setInt(2, id_course);
            pstmt.setString(1, description);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean updateTopicOfCourse(String courseName, String topicName, String start_day) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_DELETED_COURSE);
            pstmt.setString(1, topicName);
            pstmt.setString(2, start_day);
            pstmt.setString(3, courseName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean editCourse(String name, String newName) {

        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_COURSE);

            pstmt.setString(1, newName);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public List<User> findAllStudent() throws DBException {
        List<User> studetList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_STUDENTS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studetList.add(extractUser(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find list of all users", e);
        }
        return studetList;
    }

    public List<User> findPageOfStudents(String sql) throws DBException {
        List<User> studetList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                studetList.add(extractUser(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot find page of student(pagination)", e);
        }
        return studetList;
    }

    public int getNumOfAllStudents() throws DBException {
        int num = 0;
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM account where role = 'STUDENT'");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                num++;
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot count the number of students", e);
        }
        return num;
    }

    public List<Topic> getAllDelTopic() throws DBException {
        List<Topic> topicList = new ArrayList<>();
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM topic where (del_status=1)");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                topicList.add(extractTopic(rs));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DBException("Cannot obtain list of deleted topic", e);
        }
        return topicList;
    }

    public boolean changeDelStatus(String topicName) {
        try (Connection con = getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE topic SET del_status = '0' WHERE (name = ?);");
            pstmt.setString(1, topicName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setPassword(rs.getString("password"));
        user.setLogin(rs.getString("login"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setId(rs.getInt("id"));
        user.setRole(rs.getString("role"));
        user.setStatus(rs.getString("block_status"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("telephone"));
        user.setAvatar(rs.getString("user_avatar"));
        return user;
    }

    private Course extractCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setCourse_name(rs.getString("course_name"));
        course.setCourse_topic(rs.getString("course_topic"));
        course.setStart_day(rs.getString("start_day"));
        return course;
    }

    private Topic extractTopic(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        topic.setName(rs.getString("name"));
        topic.setStatus(rs.getString("del_status"));

        return topic;
    }

    private void close(Connection cn) {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }
}