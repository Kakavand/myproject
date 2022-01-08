/**
 * Shahow Kakavandy, 2022.
 */

package services;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * The main class of the web service, contains all the necessary methods for
 * the building of my web service in spring boot.
 * <p>
 * The class contains mainly getters, setters, the constructor for the service.
 * and the validation method for the URL.
 */

@Entity
public class Service {
    @Autowired
    public static ServicesRepository repository;


    @Id
    @GeneratedValue
    private Long id;

    private String url;
    private String name;
    private String status;
    @CreationTimestamp
    private Timestamp creationTime;


    public Service() {
    }


    public Service(String url, String name, String status, Timestamp creationTime, Long id) {

        this.url = url;
        this.name = name;
        this.id = id;
        this.status = status;
        this.creationTime = creationTime;
    }


    public Timestamp getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void validateUrl(String url) {
        try {
            new URL(url).toURI();

        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException("The following String is not a valid URL:  " + url);
        }
    }


    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id.equals(service.id) && url.equals(service.url) && name.equals(service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}







