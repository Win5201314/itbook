package com.itbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentReposity studentReposity;

    @Transactional
    public void update() {
        studentReposity.update(1, 36);
    }
}
