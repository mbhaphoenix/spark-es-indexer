package com.rte.dsit.data.bdf.odessa.indexer.conf;

import com.rte.dsit.data.bdf.odessa.indexer.Main;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = NonValidApplicationArgumentsIntegrationTest.NonValidTestConfiguration.class)
@ComponentScan(basePackages = {"com.rte.dsit.data.bdf.odessa.indexer"})
@TestPropertySource(locations = "classpath:nonvalid-test-application.properties")
public class NonValidApplicationArgumentsIntegrationTest {



    @Ignore
    @Test
    public void testNonValidApplicationArgumentsBean() {
        try {
            SpringApplication app = new SpringApplication();
        }catch (Exception e){
            System.out.println("9++++++++++++++++++++++++++++++++++++++++++++++++++");
            assertThat(true == true);
        }
    }

    //@EnableAutoConfiguration
    @ComponentScan(basePackages = {"com.rte.dsit.data.bdf.odessa.indexer.job"})
    @Configuration
    public class NonValidTestConfiguration {

    }

}