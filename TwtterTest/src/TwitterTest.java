import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterTest
{

    public static void main(String[] args) throws IOException, TwitterException{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("vjqRELGda5ISuIUmdUMkEBMKc")
            .setOAuthConsumerSecret("QLavVZJvSJWHSCcIu5p9u0kboPKrCqSrZl288uPdo0GdBbt35p");
        
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        
        //TwitterFactory tf = new TwitterFactory(cb.build());
        //twitter4j.Twitter twitter = tf.getInstance();
 
        // Si están seteados el Token y el TokenSecret la siguiente
        // linea lanzará IllegalStateException
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Obtenido request token.");
        System.out.println("Request token: " + requestToken.getToken());
        System.out.println("Request token secret: " + requestToken.getTokenSecret());
        AccessToken accessToken = null;
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken)
        {
            String osName = System.getProperty("os.name");
            String url = requestToken.getAuthorizationURL();
            System.out.println("La siguiente URL será abierta en su navegador:");
            System.out.println(requestToken.getAuthorizationURL());
            if (osName.contains("Windows"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            else
                if (osName.contains("Mac OS"))
                    Runtime.getRuntime().exec("open " + url);
                else
                    if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0)
                    {
 
                        String[] browsers =
                        { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx" };
 
                        StringBuffer cmd = new StringBuffer();
                        for (int i = 0; i < browsers.length; i++)
                            cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
 
                        Runtime.getRuntime().exec(new String[]
                        { "sh", "-c", cmd.toString() });
                    }
            System.out.print("Introduce el PIN y pulsa enter.\n[PIN]: ");
            String pin = br.readLine();
 
            if (pin.length() > 0)
                accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            else
                // Si eres una aplicación de confianza (una multinacional por
                // ejemplo) no necesitas PIN, por eso aparece esta línea
                accessToken = twitter.getOAuthAccessToken(requestToken);
        }
        System.out.println("Obtenido el access token.");
        System.out.println("Access token: " + accessToken.getToken());
        System.out.println("Access token secret: " + accessToken.getTokenSecret());
 
 
        
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
    }
}


