package io.mbhaphoenix.indexer.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource(locations = "classpath:test-application.properties")
public class ApplicationArgumentsIntegrationTest {

    @Autowired
    ApplicationArguments applicationArguments;


    @Test
    public void testValidApplicationArgumentsBean() throws Exception {
        assertThat(applicationArguments.getSqlQuery()).isEqualTo("select * FROM dbname.tablename");
        assertThat(applicationArguments.getIndexType()).isEqualTo("spark/type");
    }

    @Test
    public void testNonValidApplicationArgumentsBean() throws Exception {
        assertThat(applicationArguments.getSqlQuery()).isEqualTo("select * FROM dbname.tablename");
        assertThat(applicationArguments.getIndexType()).isEqualTo("spark/type");
    }

    @Test
    public void testGettingEnvironmentVariables() throws Exception {
        assertThat(applicationArguments.getEsNodes()).isEqualTo("localhost");
        assertThat(applicationArguments.getEsPort()).isEqualTo("9200");
    }

}