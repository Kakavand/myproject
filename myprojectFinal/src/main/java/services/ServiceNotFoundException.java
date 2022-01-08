package services;

class ServiceNotFoundException extends RuntimeException {

    ServiceNotFoundException(Long Id) {
        super("Could not find the requested service " + Id);
    }
}
