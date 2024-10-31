package org.evan.service;

/**
 * @author Evan
 * @date 2021/12/27
 */
public class TestService {

    TestProperties testProperties;

    public TestProperties getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(TestProperties testProperties) {
        this.testProperties = testProperties;
    }

    public String stringConvert(String name) {
        return testProperties.getPrefix() + "-" + name + testProperties.getSuffix();
    }
}
