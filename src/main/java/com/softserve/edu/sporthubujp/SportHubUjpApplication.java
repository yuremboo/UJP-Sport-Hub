package com.softserve.edu.sporthubujp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportHubUjpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportHubUjpApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer configure() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry reg) {
//                reg.addMapping("/**").allowedOrigins("*");
//            }
//        };
//
//    }


//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
