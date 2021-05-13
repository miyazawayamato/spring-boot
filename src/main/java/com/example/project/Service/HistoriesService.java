package com.example.project.Service;


import java.util.Optional;

import com.example.project.Form.HistoriesForm;

// import java.util.List;
// import java.util.Optional;

import com.example.project.Repository.HistoriesRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoriesService {
    
    @Autowired
    HistoriesRepository historiesRepository;
    
    //一つの商品のみ削除
    public Integer delete(Integer id) {
        
        Optional<HistoriesForm> target = historiesRepository.findById(id);
        if(target.isEmpty()) {
            return 0;
            
        } else {
            historiesRepository.deleteById(id);
        }
        return id;
        
    }
}
