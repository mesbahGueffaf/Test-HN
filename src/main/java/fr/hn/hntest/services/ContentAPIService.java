package fr.hn.hntest.services;

import fr.hn.hntest.model.ImageInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Controller
public class ContentAPIService {

    @Autowired
    private  Environment environment;
    private  static String URL_HOST;
    private  static String URL_HOST_IMG;
    private  static Integer DUREE_CACHE;
    private static LocalTime localTime;
    @PostConstruct
    public void initialize() {
        URL_HOST= environment.getProperty("URL_HOST");
        DUREE_CACHE= environment.getProperty("DUREE_CACHE",Integer.class);
    }
    /**
     * find url from cache
     * **/
    public String findFomCache(String url){

        LocalTime newLocalTime=LocalTime.now();
        if(localTime==null){URL_HOST_IMG=url;localTime=newLocalTime;}

        if(newLocalTime.toSecondOfDay()-localTime.toSecondOfDay()>DUREE_CACHE){
            URL_HOST_IMG=url;localTime=newLocalTime;
        }
        System.out.println("URL_HOST_IMG = " + URL_HOST_IMG);

        return URL_HOST_IMG;

    }


    /**
     * Service de récupération d'une image aléatoire, avec une mise en cache d'une durée de {@link ContentAPIService# DUREE_CACHE} secondes, après chaque appel.
     *
     * @return une image
     * @throws Exception
     */

    public Mono<String> generateImageUrl(){
        Mono<String> url=
                WebClient.create(URL_HOST)
                        .get()
                        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                        .retrieve()
                        .bodyToMono(ImageInformations.class)
                        .map(ImageInformations::getMessage)
                        .map(message ->findFomCache( message.replaceAll("'\'","")));



        return url;
    }
}
