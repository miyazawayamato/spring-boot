package com.example.project.Service;

import com.example.project.Repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {
    
    @Autowired
    UsersRepository usersRepository;
    
}
