package io.mbhaphoenix.indexer.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"io.mbhaphoenix.indexer"})
@Configuration
public class TestConfiguration {

}
