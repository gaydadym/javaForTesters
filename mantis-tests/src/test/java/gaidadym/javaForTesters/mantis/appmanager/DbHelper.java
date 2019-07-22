package gaidadym.javaForTesters.mantis.appmanager;

import gaidadym.javaForTesters.mantis.model.MantisUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }



    public List<MantisUsers> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<MantisUsers> result;
        result = session.createQuery( "from MantisUsers where id >1").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }




}
