package fr.hn.hntest.services;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ContentAPIServiceTest {
@Autowired
    ContentAPIService contentAPIService;
    @Before
    public void intTest(){

    }
    @Test
    void findFomCache() throws InterruptedException {
       String url= contentAPIService.findFomCache("www.hn.fr");
        assertEquals(url,"www.hn.fr");

        url= contentAPIService.findFomCache("www.hn2.fr");
        assertEquals(url,"www.hn.fr");

        Thread.sleep(6000);

        url= contentAPIService.findFomCache("www.hn3.fr");
        assertEquals(url,"www.hn3.fr");

    }

}