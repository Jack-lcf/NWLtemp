package service;

public class ServiceLocatorFactory {

    public static ServiceLocator getServiceLocator() {
        return new ServiceLocatorImpl();
    }
}
