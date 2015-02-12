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
//		����serviceRegistry��hibernate�ϰ汾���õķ�����buildSessionFactory
//  	�Ự���������ֺ�����Factory����Ϊ������Factory���ģʽ
//		SessionFactory factory  = cfg.buildSessionFactory();
		
		
//		����SessionFactory����ĵڶ�������
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry(); 
		SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);
		Session session = null;
		try {
			session = factory.openSession();
//			��ʼ����
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
