package com.matchmyjob.integration.test;

import com.matchmyjob.app.MatchMyJobApp;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MatchMyJobApp.class)
@AutoConfigureWebTestClient
public abstract class AbstractIntegrationTest {}
