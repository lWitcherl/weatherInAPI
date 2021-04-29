package com.dut.education.repositorys;

import com.dut.education.entitys.UserFromDB;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;


@Repository
public class UserFromDBRepositoryImp implements UserFromDBRepository{
    private SessionFactory sessionFactory;

    public UserFromDBRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserFromDB findUserFromDBByUsername(String name) {
        UserFromDB user ;
        try {
            user = sessionFactory.getCurrentSession()
                    .createQuery("from UserFromDB where username ='"+name+"'",UserFromDB.class).getSingleResult();
        }catch (NoResultException e ){
            user=null;
        }
        return user;
    }

    @Override
    public UserFromDB findUserFromDBByEmail(String email) {
        UserFromDB user;
        try {
            user = sessionFactory.getCurrentSession()
                    .createQuery("from UserFromDB where email ='"+email+"'",UserFromDB.class).getSingleResult();
        }catch (NoResultException e ){
            user=null;
        }
        return user;
    }

    @Override
    public void save(UserFromDB user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(UserFromDB user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public boolean existsById(int id) {
        UserFromDB user = sessionFactory.getCurrentSession()
                .createQuery("from UserFromDB where id ="+id,UserFromDB.class).getSingleResult();
        System.out.println(user);
        return user!=null;
    }
}
