package service;

import dao.DaoFactory;

public class ServiceImpl implements Service {
    private DaoFactory factory = null;

    public ServiceImpl(DaoFactory daoFactory) {
        factory = daoFactory;
    }

    public DaoFactory getDaoFactory() {
        return factory;
    }

}
