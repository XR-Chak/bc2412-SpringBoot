package com.example.demo_final_project.service;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.CookieManager;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class yahooCookieManager {
  @Autowired
  RestTemplate restTemplate;

  public static String cookie;

  public void clearYahooCookie(){
    // Create cookie store
        CookieStore cookieStore = new BasicCookieStore();
        // Create HTTP client with cookie store
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build()) {
            // After making your GET request, clear yahoo cookies
            cookieStore.getCookies().stream()
                .filter(cookie -> cookie.getDomain() != null && 
                        cookie.getDomain().contains("yahoo.com"))
                .forEach(cookie -> cookieStore.clearExpired(new java.util.Date()));
            
            // To clear all yahoo cookies immediately
            cookieStore.clear();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
  }

  public void getYahooCookies() {
        try {
            // Set up cookie manager
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            java.net.CookieHandler.setDefault(cookieManager);
            
            // Make GET request
            URL url = new URL("https://fc.yahoo.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            
            // Get cookies from cookie store
            List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
            
            if (!cookies.isEmpty()) {
                System.out.println("Cookies from fc.yahoo.com:");
                for (HttpCookie cookie : cookies) {
                    System.out.println("Name: " + cookie.getName());
                    System.out.println("Value: " + cookie.getValue());
                    System.out.println("------------------------");
                    yahooCookieManager.cookie = cookie.getValue();
                }
            } else {
                System.out.println("No cookies found");
            }
            
            connection.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
