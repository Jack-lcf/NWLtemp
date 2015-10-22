package service;

public abstract class ServiceLocator {
    abstract public <T extends Service> T getService(Class<T> key) throws ServiceException;
}
