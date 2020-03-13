import com.study.spring.logger.LoggerConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);


    @Test
    public void hello() {
        System.out.println("--- hello ---");
    }


    @Test
    public void test_Log() {
        LOGGER.debug("------test debug------");
        LOGGER.info("------test info------");
        LOGGER.warn("------test warn------");

        try {
            int a = 12 / 0;
            System.out.println(a);
        } catch (Exception e) {
            LOGGER.error("------test error------" + e);
        }

        System.out.println("=======" + LOGGER.getClass());

        LOGGER.trace("my trace log4j2 -- 日志 -- trace");
        LOGGER.debug("my debug log4j2 -- 日志 -- debug");
        LOGGER.info("my info log4j2 -- 日志 -- info");
        LOGGER.warn("my warn log4j2 -- 日志 -- warn");
        LOGGER.error("my error log4j2 -- 日志 -- error");

        LoggerConstant.SERVICE_DIGEST_LOG.info("SERVICE_DIGEST_LOG");

        LoggerConstant.DAO_DIGEST_LOG.info("DAO_DIGEST_LOG");

    }

}
