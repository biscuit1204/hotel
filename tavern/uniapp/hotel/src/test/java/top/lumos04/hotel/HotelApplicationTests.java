package top.lumos04.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lumos04.hotel.service.UserService;

@SpringBootTest
class HotelApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

    }

}
