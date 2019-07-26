package com.demo.util;

import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OAuthTokenUtil {
    public static OAuth2AccessToken getOAuth2AccessToken() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(getResource(),
                new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
        return template.getAccessToken();
    }
 //个贷APP OAUTH

    public static OAuth2ProtectedResourceDetails getResource() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        String configuredURI = ReadProperties.getValue("access_token_uri");
        resource.setAccessTokenUri(new StringBuilder().append(configuredURI).append("/oauth/token").toString());
        resource.setClientId(ReadProperties.getValue("clientId"));
        resource.setClientSecret(ReadProperties.getValue("client_secret"));
        resource.setGrantType(ReadProperties.getValue("grant_type"));
        return resource;
    }

    //个贷系统OAUTH
//    public static OAuth2ProtectedResourceDetails getResource(){
//        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
//        String configuredURI = ReadProperties.getValue("access_token_uri");
//        resource.setAccessTokenUri(new StringBuilder().append(configuredURI).append("/oauth/token").toString());
//        resource.setClientId("puhui-lend");
//        resource.setClientSecret("puhui-lend");
//        resource.setGrantType("client_credentials");
//        return resource;
//    }

    public static void main(String[] args) {
        System.out.println( OAuthTokenUtil.getResource().getAccessTokenUri());
        System.out.println(OAuthTokenUtil.getOAuth2AccessToken().getValue());

    }

}
