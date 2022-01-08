/**
 * Shahow Kakavandy, 2022.
 */

package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static services.Service.validateUrl;

/**
 * This is the class for the service poller that is scheduled to run and update
 * at a fixed rate, the status of the availability of the URLs for the services.
 */


@Component

public class ServicePoller {

    public ServiceModelAssembler assembler;
    public List<Service> serviceList;
    @Autowired
    public ServicesRepository repository;


    @Scheduled(fixedRate = 2000)
    public void reportURLStatus() {
        //System.out.println("Starting polling!!!!");
        List<Service> serviceList = repository.findAll();

        for (Service s : serviceList) {

            if (s.getUrl() != null && s.getName() != null) {
                try {
                    validateUrl(s.getUrl());

                    URL url = new URL(s.getUrl());
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    int responseCode = huc.getResponseCode();


                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        s.setStatus("OK");
                        repository.save(s);
                    } else {
                        s.setStatus("OK");
                        repository.save(s);
                    }
                } catch (IOException | IllegalArgumentException e) {
                    s.setStatus("Fail");
                    repository.save(s);
                }
            } else {
                s.setStatus("Fail");
                repository.save(s);
            }

        }
    }
}

