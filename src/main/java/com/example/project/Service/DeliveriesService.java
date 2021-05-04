package com.example.project.Service;

import java.util.List;
import java.util.Optional;

import com.example.project.Repository.DeliveriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeliveriesService {
    
    @Autowired
    DeliveriesRepository deliveriesRepository;
    
}
