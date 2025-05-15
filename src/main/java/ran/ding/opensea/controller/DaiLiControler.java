package ran.ding.opensea.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

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
        
        // 从env中获取代理地址
        String proxyUrl = env.getProperty("proxy_url");
        String proxyToken = env.getProperty("proxy_token");
        
        // 从Authorization头中获取并解码token
        String auth = request.getHeader("Authorization");
        String token = null;
        if (auth != null && auth.startsWith("Basic ")) {
            String base64Credentials = auth.substring("Basic ".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded);
            // Basic认证格式是 username:password，我们取username部分作为token
            token = credentials.split(":")[0];
        }
        
        // 校验token
        if(proxyToken.equals(token)){
            try {
                // 处理带有认证信息的URL
                String urlWithoutAuth = proxyUrl;
                if (proxyUrl.contains("@")) {
                    urlWithoutAuth = "http://" + proxyUrl.substring(proxyUrl.indexOf("@") + 1);
                }
                
                // 解析URL
                URL url = new URL(urlWithoutAuth);
                String host = url.getHost();
                
                // 检查是否是域名（简单判断是否包含字母）
                if (host.matches(".*[a-zA-Z].*")) {
                    // 解析域名获取IP
                    InetAddress address = InetAddress.getByName(host);
                    String ip = address.getHostAddress();
                    
                    // 替换域名为IP
                    proxyUrl = proxyUrl.replace(host, ip);
                }
            } catch (Exception e) {
                System.out.println("域名解析失败: " + e.getMessage());
                // 如果解析失败，返回原始URL
                return proxyUrl;
            }
            return proxyUrl;
        }else{
            return "密钥不对,滚远点";
        }
    }
}
