package com.rte.dsit.data.bdf.odessa.indexer.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties
public class ApplicationArguments {

    @NotNull
    private String index;

    @NotNull
    private String type;

    @NotNull
    private String dbName;

    @NotNull
    private String tableName;

}
