package com.rte.dsit.data.bdf.odessa.indexer.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rte.dsit.data.bdf.odessa.indexer"})
@Configuration
public class TestConfiguration {

}
