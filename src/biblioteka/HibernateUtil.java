package biblioteka;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
 private static final SessionFactory sessionFactory = buildSessionFactory();
 
 private static SessionFactory buildSessionFactory()
 {
    try
    {
        return new Configuration().configure("biblioteka/hibernate.cfg.xml").buildSessionFactory();
    }
    catch (Throwable ex)
     {
         System.out.println(ex.getMessage());
        //System.err.println("Initial SessionFactory creation failed." + ex);
        throw new ExceptionInInitializerError(ex);
     }
 }

 private static SessionFactory getSessionFactory() {
 return sessionFactory;
 }
 public static void saveEntity(Object entity) {
 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction transaction = session.beginTransaction();
 session.save(entity);
 transaction.commit();
 }
 public static List getEntities(Class criteria) {
 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
 Transaction transaction = session.beginTransaction();
 List result = session.createCriteria(criteria).list();
 transaction.commit();
 return result;
 }
 public static void updateEntity(Object entity)
 {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    session.update(entity);
    transaction.commit();
 }
 
 public static void update(Long idKsiazki, Long idOsoby)
 {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
      Transaction transaction = session.beginTransaction();
     Query query = session.createQuery("update Wydane set zwrocono = :zwrocono" +
    				" where idKsiazki = :idKsiazki" +
             " and idOsoby = :idOsoby");
     
query.setParameter("zwrocono", "tak");
query.setParameter("idKsiazki", idKsiazki);
query.setParameter("idOsoby", idOsoby);
int result = query.executeUpdate();

 
      transaction.commit();
 }
 
 public static List borrowedBooks()
 {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery("from wydane w join ksiazka k on (w.id_ksiazki = k.id) join osoba o on (w.id_osoby = o.id)");
    List<?> list = query.list();
    transaction.commit();
    return list;
 }
 
 public static List showBooks()
 {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery("from wydane w join ksiazka k on (w.id_ksiazki = k.id) join osoba o on (w.id_osoby = o.id) where w.wydano is null");
    List<?> list = query.list();
    transaction.commit();
    return list;
 }
 
 public static List availableBooks()
 {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery("from wydane w join ksiazka k on (w.id_ksiazki = k.id) where w.zwrot is not null");
    List<?> list = query.list();
    transaction.commit();
    return list;
 }
 
 public static List zestawienie()
 {
     Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery("select imie,nazwisko, (select count(id_ksiazki) from wydane w where w.id_osoby=o.id) as wypozyczone, (select count(zwrot) from wydane w where w.id_osoby=o.id) as posiadane from osoba o");
    List<?> list = query.list();
    transaction.commit();
    return list;
 }
}
