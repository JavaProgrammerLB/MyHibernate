/**
 * 
 */
package hibernateTest;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.joda.time.DateTime;

/**
 * @author liubei
 *
 */
public class InsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		@SuppressWarnings("deprecation")
//		创建serviceRegistry的hibernate老版本采用的方法：buildSessionFactory
//  	会话工厂，名字后面有Factory是因为采用了Factory设计模式
//		SessionFactory factory  = cfg.buildSessionFactory();
		
		
//		创建SessionFactory对象的第二个方法
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry(); 
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		Session session = null;
		try {
			session = factory.openSession();
//			开始事物
			session.beginTransaction();
			User user = new User();
			user.setName("liubei");
			user.setPassword("1234");
			user.setCreateTime(new DateTime(2015,2,12,15,57,0,0).toDate());
			user.setExpireTime(new DateTime().toDate());
//			user.setCreateTime(new Date());
//			user.setExpireTime(new Date());
			session.save(user);
			//
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}	finally{
			if(session != null){
				if(session != null){
					if(session.isOpen()){
						session.close();
					}
				}
			}
		}
	}

}
