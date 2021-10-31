package aoplearn;

import com.aoplearn.AdminApplication;
import com.aoplearn.security.CurrentUserHolder;
import com.aoplearn.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class RunnerTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testInsert() {
        CurrentUserHolder.set("Tom");
        productService.delete(1L);
    }

    @Test
    public void adminInsert() {
        CurrentUserHolder.set("admin");
        productService.delete(1L);
    }


    @Test
    public void adminInsert2() {
        CurrentUserHolder.set("admin");
        productService.delete2(1L);
    }
}
