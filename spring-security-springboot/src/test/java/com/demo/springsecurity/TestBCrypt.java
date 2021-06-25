package com.demo.springsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {
    @Test
    public void test1() {
        /*
            对原始密码加密
            BCrypt.gensalt() 每次对password 加盐，输出的加密结果每次都是不一样的
         */
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw); //校验原始密码和BCrypt密码是否一致
        boolean checkpw = BCrypt.checkpw("123",
                "$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm");
        System.out.println(checkpw);
    }
}
