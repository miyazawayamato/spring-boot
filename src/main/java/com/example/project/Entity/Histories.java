package com.example.project.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Histories {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Integer qty;
    @Getter @Setter
    private Integer price;
    
    // @ManyToOne
    // private Deliveries deliveries;
    // @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliveries_id")
    private Deliveries deliveries;
}
