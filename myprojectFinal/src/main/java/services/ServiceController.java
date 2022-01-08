/**
 * Shahow Kakavandy, 2022.
 */
package services;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static services.Service.validateUrl;

/**
 * This is the class for the RestController in our spring boot application that performs
 * all the Create, Read, Update, Delete functionality.
 */


// tag::constructor[]
@RestController
class ServiceController {

    public ServicesRepository repository;

    public ServiceModelAssembler assembler;

    ServiceController(ServicesRepository repository, ServiceModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }
    // end::constructor[]

    // Aggregate root

    // tag::get-aggregate-root[]
    @GetMapping("/services")
    List<EntityModel<Service>> all() {

        List<EntityModel<Service>> services = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return services;
    }
    // end::get-aggregate-root[]

    // tag::post[]
    @PostMapping("/services")
    ResponseEntity<?> newService(@RequestBody Service newService) {
        validateUrl(newService.getUrl());
        newService.setCreationTime(newService.getCreationTime());
        EntityModel<Service> entityModel = assembler.toModel(repository.save(newService));

        return ResponseEntity.ok(entityModel);
    }
    // end::post[]

    // Single item

    // tag::get-single-item[]
    @GetMapping("/services/{id}")
    Service one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));
    }
    // end::get-single-item[]

    // tag::put[]

    @PutMapping("/services/{id}")
    ResponseEntity<?> replaceService(@RequestBody Service newService, @PathVariable Long id) {

        Service updatedService = repository.findById(id) //
                .map(service -> {
                    service.setName(newService.getName());
                    service.setUrl(newService.getUrl());
                    return repository.save(service);
                }) //
                .orElseGet(() -> {
                    newService.setId(id);
                    return repository.save(newService);
                });

        EntityModel<Service> entityModel = assembler.toModel(updatedService);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    // end::put[]

    // tag::delete[]
    @DeleteMapping("/services/{id}")
    ResponseEntity<?> deleteService(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    // end::delete[]
}
