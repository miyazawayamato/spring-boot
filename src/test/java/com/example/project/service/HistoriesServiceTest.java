package com.example.project.service;

import java.util.Optional;

import com.example.project.Form.HistoriesForm;
import com.example.project.Repository.HistoriesRepository;
import com.example.project.Service.HistoriesService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HistoriesServiceTest {
    
    @Mock
    private HistoriesRepository historiesRepository;

    @InjectMocks
    private HistoriesService historiesService;

    @BeforeEach
    public void initmocks() {
        
        MockitoAnnotations.openMocks(this);
        
    }
    
    @Test
    public void test_delete() throws Exception {
        
        HistoriesForm history = new HistoriesForm();
        Mockito.when(historiesRepository.findById(10)).thenReturn(Optional.of(history));
        Integer num = historiesService.delete(10);
        
        assertThat(num, is(10));
        
        verify(historiesRepository, times(1)).findById(10);
        verify(historiesRepository, times(1)).deleteById(10);
    }
}
