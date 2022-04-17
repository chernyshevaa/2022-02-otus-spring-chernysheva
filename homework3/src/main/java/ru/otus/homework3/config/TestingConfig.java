package ru.otus.homework3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("testing")
@Component
public class TestingConfig {
    private int scoreToPass;

    public int getScoreToPass() {
        return scoreToPass;
    }

    public void setScoreToPass(int scoreToPass) {
        this.scoreToPass = scoreToPass;
    }
}
