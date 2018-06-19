package org.andy.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class AbstractHibernateDao<E extends Serializable> extends DefaultHibernateDao {

	private Class<E> entityClass;

	public AbstractHibernateDao() {

	}

	protected AbstractHibernateDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getCurrentSession() {
		return super.getSession();
	}

	public E findById(Serializable id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	public void save(E e) {
		getCurrentSession().save(e);
	}

	public void update(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	/*
	 * @SuppressWarnings("unchecked") public List<E> query(String hql, Object[]
	 * args) { Query query=getCurrentSession().createQuery(hql); if(args!=null){
	 * for (int i = 0; i < args.length; i++) { query.setParameter(i, args[i]); }
	 * } return query.list(); }
	 */

	@SuppressWarnings("unchecked")
	public List<E> query(String hql, Object... args) {
		Query query = getCurrentSession().createQuery(hql);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> queryAll(String hql) {
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<E> findAll() {
		String hql = "from " + entityClass.getName();
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}

}
