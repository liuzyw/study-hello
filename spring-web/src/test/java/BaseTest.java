import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2017-10-02
 *
 * @author liuzhaoyuan
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class BaseTest {


    @Test
    public void hello() {
        System.out.println("--- hello ---");
    }

}
