package org.intership.clubmate;

import org.intership.clubmate.service.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClubMateApplicationTests {
    @Autowired
    private ClubService clubService;
    @Test
    void typeList(){
        clubService.typeList(1,10,0);
    }

}
