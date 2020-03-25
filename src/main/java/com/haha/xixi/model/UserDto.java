package com.haha.xixi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Administrator
 * @version 1.0
 **/
@Data
@Entity
public class UserDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
