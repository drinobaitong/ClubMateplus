package org.intership.clubmate;

import org.intership.clubmate.entity.Club;
import org.intership.clubmate.entity.User;
import org.intership.clubmate.service.ClubService;
import org.intership.clubmate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClubMateApplicationTests {
    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService userService;
    @Test
    void typeList(){
        clubService.typeList(1,10,0);
    }

    @Test
    void insert(){
        Club club=new Club();
        club.setName("中文");
        clubService.insertClub(club);
    }

    @Test
    void image(){
        clubService.updateImage(10086,"url");
    }

    @Test
    void Id(){
        User user=userService.getById(10000);
      //  System.out.println(user.getId());
    }
}
