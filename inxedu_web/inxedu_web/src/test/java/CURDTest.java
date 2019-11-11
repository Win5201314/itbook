import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * 文 件 名:CURDTest
 * 创 建 人:hackeridear
 * 创建日期:2016-10-18
 * 描   述:CURDTest
 * 修 改 人:
 * 修改日期:
 * 版 本 号:v 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class CURDTest {

    @Before
    public void setConfig(){
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }


    @Test
    public void test1(){

     /*   List<Article> list = articleBiz.findAll();
        for(Article article :list){
            System.out.println(article.getAuthor());
        }*/
    }
}
