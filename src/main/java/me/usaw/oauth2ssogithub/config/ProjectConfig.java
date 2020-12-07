package me.usaw.oauth2ssogithub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {


    //// if we use some other authorization server we use the following

//    ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("github")
//            .clientId("e27aa97860e7a1cf48b5")
//            .clientSecret("1b886f0e73277c881d51a348ff5aa4391bf1bf4c")
//            .scope("read:user")
//            .authorizationUri("https://github.com/login/oauth/access_token")
//            .userInfoUri("https://api.github.com/user")
//            .userNameAttributeName("id")
//            .clientName("GitHub")
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
//            .build();

///// if authorization server of our choice is google, github, facebook and okta we use the following

//    ClientRegistration clientRegistration = CommonOAuth2Provider.GITHUB
//            .getBuilder("github")
//            .clientId("e27aa97860e7a1cf48b5")
//            .clientSecret("1b886f0e73277c881d51a348ff5aa4391bf1bf4c")
//            .build();

    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId("e27aa97860e7a1cf48b5")
                .clientSecret("1b886f0e73277c881d51a348ff5aa4391bf1bf4c")
                .build();
    }
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository(){
//        var c = clientRegistration();
//        return new InMemoryClientRegistrationRepository(c);
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.oauth2Login();
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated();
//    }

    private ClientRegistrationRepository clientRegistrationRepository(){
        var c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.oauth2Login(c -> {
            c.clientRegistrationRepository(clientRegistrationRepository());
        });
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
