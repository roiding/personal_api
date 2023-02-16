package ran.ding.opensea.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ran.ding.result.ResponseResult;

@RestController
@RequestMapping("/online")
public class OnlineController implements EnvironmentAware {
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env=environment;
    }
    @RequestMapping("/getEnv")
    public ResponseResult getEnv(@RequestParam String envName){

        return ResponseResult.success(env.getProperty(envName));

    }
}
