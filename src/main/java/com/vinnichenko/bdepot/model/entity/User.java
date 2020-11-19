package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;

/**
 * The type User.
 * Business data object: user.
 */
public class User implements Serializable {

    /**
     * The enum Role.
     * Describes the roles of users.
     */
    public enum Role {
        /**
         * Dispatcher role.
         */
        DISPATCHER,
        /**
         * Driver role.
         */
        DRIVER,
        /**
         * Customer role.
         */
        CUSTOMER;
    }

    private long userId;
    private String login;
    private byte isActive;
    private String name;
    private String surname;
    private String phoneNumber;
    private Role role;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param login       the login
     * @param isActive    the is active
     * @param name        the name
     * @param surname     the surname
     * @param phoneNumber the phone number
     * @param role        the role
     */
    public User(String login, byte isActive, String name, String surname, String phoneNumber, Role role) {
        this.login = login;
        this.isActive = isActive;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param userId      the user id
     * @param login       the login
     * @param isActive    the is active
     * @param name        the name
     * @param surname     the surname
     * @param phoneNumber the phone number
     * @param role        the role
     */
    public User(long userId, String login, byte isActive, String name, String surname, String phoneNumber, Role role) {
        this.userId = userId;
        this.login = login;
        this.isActive = isActive;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets is active.
     *
     * @return the is active
     */
    public byte getIsActive() {
        return isActive;
    }

    /**
     * Sets is active.
     *
     * @param isActive the is active
     */
    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (isActive != user.isActive) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (int) isActive;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", login='").append(login).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
