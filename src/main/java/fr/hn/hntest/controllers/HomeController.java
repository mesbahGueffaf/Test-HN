package fr.hn.hntest.controllers;

import fr.hn.hntest.services.ContentAPIService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
public class HomeController  {

    private static final String MSG_EXCEPTION = "Une exception est levée : {} ";

    @Autowired
    ContentAPIService contentAPIService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    /**
     * Affichage d'une image aléatoire.
     *
     * @return Une image.
     */
    @RequestMapping(value = "/random-image", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public void randomImage(HttpServletResponse response)  {
        try {
            Resource resource = contentAPIService.generateImageUrl()
                .map(imageUrl -> WebClient.create(imageUrl) )
                .map(WebClient::get)
                .map(WebClient.RequestHeadersSpec::retrieve)
                .flatMap(responseSpec -> responseSpec.bodyToMono(Resource.class))
                .block();

        InputStream in = resource.getInputStream();

        IOUtils.copy(in, response.getOutputStream());
        } catch (Exception ex) {
            logger.info(MSG_EXCEPTION, ex);
        }
    }
}
