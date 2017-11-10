package io.mbhaphoenix.indexer.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Component
@Validated
@ConfigurationProperties
public class ApplicationArguments {

    @NotNull
    @Pattern(regexp = ".+/.+")
    private String indexType;

    @NotNull
    @Pattern(regexp = "^(select|SELECT)\\s.+(from|FROM)\\s.+\\..+$")
    private String sqlQuery;

    @Min(1)
    @Max(1000)
    private int splitsNumber;

    @NotNull
    private String esNodes;

    @NotNull
    private String esPort;


}
