package com.vinnichenko.bdepot.model.service;

import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authorize(Map<String, String> parameters) throws ServiceException;
    Optional<User> findUserByLogin(String login) throws ServiceException;
    long saveUser(Map<String, String> parameters) throws ServiceException;
    List<User> findFreeDrivers() throws ServiceException;
    boolean updateUser(Map<String, String> parameters) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;
    boolean updatePassword(Map<String, String> parameters) throws ServiceException;
    List<User> findAll() throws ServiceException;

    class ErrorMessageType {

        public static final String LOGIN_ERROR = "login";
        public static final String PASSWORD_ERROR = "password";

        private ErrorMessageType(){
        }
    }
}
