package com.wipro.cloud.api.helloworld.acceptance;

import javax.script.ScriptException;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;

import org.junit.runner.RunWith;

@RunWith(Karate.class)
@KarateOptions(features = "classpath:features/client-api.feature")
public class KarateRunnerIT {
    
}
