package org.gift.backoffice.dao.user_dao;

import org.gift.backoffice.dao.Dao;
import org.gift.backoffice.model.user.CustomerEntity;

import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<CustomerEntity, Long> {

    @Override
    public Optional<CustomerEntity> get(Long id) {
        return Optional.empty();
    }

    public Optional<CustomerEntity> getByEmail(String email) {
        return Optional.empty();
    }

    public Optional<CustomerEntity> getByPassword(String password) {
        return Optional.empty();
    }

    @Override
    public List<CustomerEntity> getAll() {
        return null;
    }

    @Override
    public void save(CustomerEntity customerEntity) {

    }

    @Override
    public void update(CustomerEntity customerEntity, String[] params) {

    }

    @Override
    public void delete(CustomerEntity customerEntity) {

    }
}
