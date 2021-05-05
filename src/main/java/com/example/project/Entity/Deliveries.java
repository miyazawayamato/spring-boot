package com.example.project.Entity;

import java.util.ArrayList;
import java.util.List;
// import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Deliveries {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String time;
    
    private Integer customers_id;
    
    
    // @OneToMany(mappedBy="deliveries")
    // private List<Histories> histories;
    @OneToMany(mappedBy="deliveries", cascade = CascadeType.ALL)
    private List<Histories> histories = new ArrayList<>();
}
