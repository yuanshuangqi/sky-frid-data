package com.ylj.sky.boot.manager;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SkyBootManagerApplication.class})
@ActiveProfiles("dev")
public class BaseTest {
}
