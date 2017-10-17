/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twittertest;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class Twittertest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException {
        // obtener datos de twiiter
        ConfigurationBuilder cf = new ConfigurationBuilder();
        
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("vjqRELGda5ISuIUmdUMkEBMKc")
                .setOAuthConsumerSecret("QLavVZJvSJWHSCcIu5p9u0kboPKrCqSrZl288uPdo0GdBbt35p")
                .setOAuthAccessToken("61879262-X2wRGnRi7BZRkdq42VhDytYQbeFXElNBAzmjqwr1J")
                .setOAuthAccessTokenSecret("2hwLRnTGd1QrrEdDY0NeMQ80vrwHmqyFKvP3G5CUgYagR");
                
        
        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();
        
        List<twitter4j.Status> status = twitter.getHomeTimeline();//lista de stats
        
        
        // obtener username, stats, time of post, location
        for(twitter4j.Status st : status){
            System.out.println(st.getUser().getLocation()+"-----"+st.getUser().getName()+"-----"+st.getText());
        }
        //-----------datos publicos del usuario
        String datos="";
        User usuario;
            
        usuario=twitter.showUser(twitter.getScreenName());

        datos=usuario.toString();

        twitter.showUser(twitter.getScreenName());

        System.out.println(usuario);
        
        //------TimeLine del usuario
        List<Status> statuses=null;
        statuses = twitter.getUserTimeline();
        System.out.println("Status--- "+statuses);
       
        //------Post tweet
        twitter4j.Twitter mtwitter = TwitterFactory.getSingleton();
        Status mstatus = twitter.updateStatus("Hola Mundo desde Java");
        System.out.println("Exito al actualizar estado [" + mstatus.getText() + "].");
        
        //--- Enviar y recibir mensajes directos
        /*
        twitter4j.Twitter sender = TwitterFactory.getSingleton();
        twitter4j.DirectMessage  message = sender.sendDirectMessage("@pancho77c", "Hola desde Java");*/
        
    }
}


     
