package ran.ding.opensea.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/daili")
@RestController
public class DaiLiControler implements EnvironmentAware {
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env=environment;
    }

    @RequestMapping("/getProxyUrl")
    public String getProxyUrl() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        // 从env中获取代理地址
        String proxyUrl = env.getProperty("proxy_url");
        String proxyToken = env.getProperty("proxy_token");
        
        // 从URL中提取token
        String token = null;
        if (url.contains("@")) {
            // 获取@前面的部分
            String beforeAt = url.substring(0, url.indexOf("@"));
            // 获取最后一个/后面的内容
            token = beforeAt.substring(beforeAt.lastIndexOf("/") + 1);
        }
        StringBuilder result = new StringBuilder();
        result.append("Authorization: ").append(request.getHeader("Authorization")).append("\n");
        result.append("Request URI: ").append(request.getRequestURI()).append("\n");
        result.append("Query String: ").append(request.getQueryString()).append("\n");
        result.append("Remote User: ").append(request.getRemoteUser()).append("\n");
        result.append("Request URL: ").append(request.getRequestURL()).append("\n");
        System.out.println(result.toString());
        // 校验token
        if(proxyToken.equals(token)){
            return proxyUrl;
        }else{
            return "密钥不对,滚远点";
        }
    }
}
