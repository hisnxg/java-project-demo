import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author mj
 * @create 2021-03-19 16:27
 * @describe:
 */
public class MyTest {

    @Test
    public void test(){
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        context.getBean("");
    }
}
