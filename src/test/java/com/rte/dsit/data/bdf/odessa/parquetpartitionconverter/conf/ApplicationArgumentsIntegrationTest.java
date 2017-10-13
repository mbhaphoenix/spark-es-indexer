package com.rte.dsit.data.bdf.odessa.parquetpartitionconverter.conf;

import com.rte.dsit.data.bdf.odessa.indexer.conf.ApplicationArguments;
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
        assertThat(applicationArguments.getDbName()).isEqualTo("convergence_isoprod_public_db");
        assertThat(applicationArguments.getTableName()).isEqualTo("sr_xiidm_network_isoprod_public_csv");
        assertThat(applicationArguments.getIndex()).isEqualTo("phoenix_dev00_prive_db");
        assertThat(applicationArguments.getType()).isEqualTo("sr_xiidm_network_parquet");
    }

}