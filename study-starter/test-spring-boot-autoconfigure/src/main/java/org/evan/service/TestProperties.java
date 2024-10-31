package org.evan.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Evan
 * @date 2021/12/27
 */
@ConfigurationProperties(prefix = "test.evan")
public class TestProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
