package gaidadym.javaForTesters.addressbook.appmanager;

import gaidadym.javaForTesters.addressbook.model.ContactData;
import gaidadym.javaForTesters.addressbook.model.Contacts;
import gaidadym.javaForTesters.addressbook.model.GroupData;
import gaidadym.javaForTesters.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

    public Groups groups(boolean withDeleted){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result;
        if (withDeleted){
            result = session.createQuery( "from GroupData" ).list();
        }else{
            result = session.createQuery( "from GroupData where deprecated = 0000-00-00" ).list();
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(boolean withDeleted){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result;
        if (withDeleted) {
            result = session.createQuery("from ContactData").list();
        }
        else{
            result = session.createQuery("from ContactData where deprecated = 0000-00-00").list();
        }
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
}
