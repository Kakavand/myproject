/**
 * Shahow Kakavandy, 2022.
 */
package services;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This is my modelassembler, for my Spring boot application.
 */

@Component
class ServiceModelAssembler implements RepresentationModelAssembler<Service, EntityModel<Service>> {

    @Override
    public EntityModel<Service> toModel(Service service) {

        return EntityModel.of(service, //
                linkTo(methodOn(ServiceController.class).one(service.getId())).withSelfRel(),
                linkTo(methodOn(ServiceController.class).all()).withRel("services"));
    }
}
