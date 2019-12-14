package com.capgemini.rhf.timer;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.search.TwitterSearchComponent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * A {@link RouteBuilder} demonstrating the use of CDI (Contexts and Dependency Injection).
 * <p>
 * Note that for the {@code @Inject} and {@code @ConfigProperty} annotations to work, this class has to be annotated
 * with {@code @ApplicationScoped}.
 */
@ApplicationScoped
public class TwitterSearch extends RouteBuilder {

    @ConfigProperty(name = "twitter.rateLimit.delay") 
    String delay;
    
    @ConfigProperty(name = "twitter.app.camel.accessToken")
    String accessToken;
    
    @ConfigProperty(name = "twitter.app.camel.accessTokenSecret")
    String accessTokenSecret;
    
    @ConfigProperty(name = "twitter.app.camel.consumerKey")
    String consumerKey;
    
    @ConfigProperty(name = "twitter.app.camel.consumerSecret")
    String consumerSecret;
    
    @ConfigProperty(name = "twitter.busqueda")
    String terminoBusqueda;
        

    @Override
    public void configure() throws Exception {
    	// Setup Twitter component
        TwitterSearchComponent tc = getContext().getComponent("twitter-search", TwitterSearchComponent.class);
        tc.setAccessToken(accessToken);
        tc.setAccessTokenSecret(accessTokenSecret);
        tc.setConsumerKey(consumerKey);
        tc.setConsumerSecret(consumerSecret);
        
       // polling twitter search para nuevos tweets
        fromF("twitter-search://%s?delay=%s", terminoBusqueda, delay)        
        .log("Tweet search: ${body}")        
        .to("websocket://localhost:9090/camel-tweet?sendToAll=true&staticResources=classpath:.");
    }
}

