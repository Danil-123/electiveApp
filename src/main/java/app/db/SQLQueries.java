package app.db;

public class SQLQueries {
    private SQLQueries() {
    }

    public static final String SQL_FIND_USER_BY_LOGIN = "select * from account where login=?";
    public static final String SQL_CREATE_STUDENT = "INSERT INTO gradebook (course_id,student_id) VALUES(?,?);";
    public static final String SQL_FIND_ALL_TOPIC = "select * from topic where name!='null'";
    public static final String SQL_FIND_COURSES_BY_TOPIC = "select * from course where course_topic = ?";
    public static final String SQL_FIND_COURSE_BY_ID = "select * from course where id = ?";
    public static final String SQL_FIND_COURSE_DESCRIPTION = "select * from course_description where course_description_id_course = ?";
    public static final String SQL_FIND_COURSE_DURATION = "select * from profile_course where id_course = ?";
    public static final String SQL_FIND_USER_BY_ID_COURSE = "select * from profile_course where id_course = ?";
    public static final String SQL_FIND_USER_BY_ID = "select * from account where id = ?";
    public static final String SQL_FIND_ALL_USERS_IN_COURSE = "select * from gradebook where course_id = ?";
    public static final String SQL_FIND_COURSES_BY_STUDENT_ID = "select * from gradebook where student_id = ?";
    public static final String SQL_FIND_DURATION_COURSE = "select * from profile_course where id_course = ?";
    public static final String SQL_CREATE_USER = "insert into account" +
            " (id, login, password, email, telephone, name, surname, role)" +
            "values(default, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SQL_FIND_USER_BY_PHONE = "select * from account where telephone = ?";
    public static final String SQL_FIND_USER_BY_EMAIL = "select * from account where email = ?";
    public static final String SQL_UPDATE_COURSE = "UPDATE course SET course_name = ? WHERE (course_name = ?);";
    public static final String SQL_DELETE_COURSE = "UPDATE course SET course_topic = 'null' WHERE (id = ?);";
    public static final String SQL_FIND_ALL_STUDENT = "select  * from account where id = ?";
    public static final String SQL_FIND_ALL_STUDENTS = "select  * from account where role = 'STUDENT'";
    public static final String SQL_FIND_ALL_ID_STUDENT = "select  * from gradebook where course_id = ?";
    public static final String SQL_FIND_ALL_TEACHER = "select * from account where role = ?";
    public static final String SQL_CREATE_TOPIC = "insert into topic (topic_id,name) values (default, ?);";
    public static final String SQL_UPDATE_TEACHER = "UPDATE profile_course SET id_account = ? WHERE id_course = ?;";
    public static final String SQL_DELETE_STUDENT_FROM_COURSE =
            "DELETE FROM gradebook where (student_id=? and course_id = ?)";
    public static final String SQL_UPDATE_COURSE_DESCRIPTION = "UPDATE course_description SET course_info = ? " +
            "WHERE course_description_id_course = ?;";
    public static final String SQL_CREATE_COURSE = "insert into course (id, course_name, course_topic, start_day) " +
            "VALUES (default , ?, ?, ?);";
    public static final String SQL_SET_TEACHER = "insert into profile_course(id_account,id_course) value (?,?)";
    public static final String SQL_FIND_TEACHER_BY_COURSE = "select id_account from profile_course where id_course = ?";
    public static final String SQL_CREATE_DESCRIPTION = "insert into course_description(course_description_id_course,course_info) value (?,?)";
    public static final String SQL_SET_DURATION = "UPDATE profile_course SET duration = ? " +
            "WHERE id_course = ?;";
    public static final String SQL_FIND_COURSE = "select * from course where course_name = ? and course_topic = 'null'";
    public static final String SQL_UPDATE_DELETED_COURSE = "UPDATE course SET course_topic = ?, start_day = ?" +
            " WHERE (course_topic = 'null' and course_name = ?);";
    public static final String SQL_BLOCK_STUDENT = "UPDATE account SET block_status = '1' WHERE (id = ?);";
    public static final String SQL_UNBLOCK_STUDENT = "UPDATE account SET block_status = '0' WHERE (id = ?);";
    public static final String SQL_FIND_COURSES_BY_USER_ID = "select * from profile_course where id_account = ?";
    public static final String SQL_UPDATE_MARK = "UPDATE gradebook SET mark = ? WHERE (student_id = ?) and (course_id = ?);";
    public static final String SQL_FIND_MARK = "SELECT * FROM gradebook where student_id = ? and course_id = ?;";
    public static final String SQL_DELETE_TOPIC = "UPDATE topic SET del_status = '1' WHERE (name = ?);";
    public static final String SQL_UPDATE_AVATAR = "UPDATE account SET user_avatar = ? WHERE (login = ?);";
}
