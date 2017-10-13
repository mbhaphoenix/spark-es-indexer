package com.rte.dsit.data.bdf.odessa.parquetpartitionconverter.conf;

import com.rte.dsit.data.bdf.odessa.indexer.conf.ApplicationArguments;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationArgumentsTest {

    private ApplicationArguments applicationArguments;

    @Before
    public void setUp() throws Exception {
        applicationArguments = new ApplicationArguments();
    }

    @Test
    public void buildPartitionsFilterExpression() throws Exception {
        applicationArguments.setDbName("phoenix_dev00_prive_db");
        assertThat(applicationArguments.getDbName()).isEqualTo("phoenix_dev00_prive_db");
    }

}