package com.dut.education.repositorys;

import com.dut.education.entitys.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRoleRepositoryImp implements UserRoleRepository{
    private SessionFactory sessionFactory;

    public UserRoleRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserRole userRole) {
        sessionFactory.getCurrentSession().saveOrUpdate(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserRole ",UserRole.class).getResultList();
    }
}
