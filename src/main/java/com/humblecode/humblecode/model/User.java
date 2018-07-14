package com.humblecode.humblecode.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class User {

    static final String DEFAULT = "default";
    static final String MONTHLY = "monthly";
    static final String YEARLY = "yearly";
    
    @Id
    UUID id = UUID.randomUUID();

    String username;

    String credentials;

    String accountType = DEFAULT; //default/monthly/etc.

    String loginType; // github/facebook/etc.

    List<UUID> courseIdsPaidFor = new LinkedList<>();

    List<TestResult> testResults = new LinkedList<>();
    
}
