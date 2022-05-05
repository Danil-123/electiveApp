package app.entities;

import java.io.Serializable;

/**
 * @author Danil Shatov
 */

public class User implements Serializable {

    /**
     * user id in the database
     */
    private int id;

    /**
     * user login
     */
    private String login;

    /**
     * user password
     */
    private String password;

    /**
     * Username
     */
    private String name;

    /**
     * surname user
     */
    private String surname;

    /**
     * user role
     */
    private String role;

    /**
     * user email
     */
    private String email;

    /**
     * user phone
     */
    private String phone;

    /**
     * student mark for the course
     */
    private String mark;

    /**
     * student avatar
     */
    private String avatar;

    /**
     * user block status:
     * '0' - blocked;
     * '1' - unblock
     */
    private String status;

    public User() {
    }

    /**
     * creating a user to work with the database
     *
     * @param login
     * @param pass
     * @param email
     * @param name
     * @param surname
     * @param phone
     * @param role
     * @return user
     */
    public static User createUser(String login, String pass, String email, String name, String surname, String phone, String role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPhone(phone);
        user.setRole(role);
        return user;
    }

    public String getAvatar() {return avatar;}

    public void setAvatar(String avatar) {this.avatar = avatar;}

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", mark='" + mark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}