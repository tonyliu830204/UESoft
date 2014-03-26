package com.syuesoft.bas.daoimpl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.syuesoft.bas.dao.BasJudgmentWordDao;
import com.syuesoft.model.BasJudgmentWord;

public class BasJudgmentWordDaoImpl extends BaseDaoImpl<BasJudgmentWord> implements BasJudgmentWordDao {

	
	public void Add(BasJudgmentWord basJudgmentWord) {
		this.getHibernateTemplate().save(basJudgmentWord);
	}

	
	public void Delete(BasJudgmentWord basJudgmentWord) {
		this.getHibernateTemplate().delete(basJudgmentWord);
	}

	
	public void Update(BasJudgmentWord basJudgmentWord) {
		this.getHibernateTemplate().update(basJudgmentWord);
	}

	@SuppressWarnings("unchecked")
	
	public List<BasJudgmentWord> findAll(final int page, final int rows) {
		List<BasJudgmentWord> list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			
			public Object doInHibernate(org.hibernate.Session session)
                            throws HibernateException, SQLException {
				String queryString = "from BasJudgmentWord";
				org.hibernate.Query query = session.createQuery(queryString);
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}

		});
		return list;
	}
	/*
	 * (non-Javadoc)
	 * 条件查询
	 * @see com.syuesoft.DataBase.Dao.BasJudgmentWordDao#findByCondition(int, int, com.syuesoft.model.BasJudgmentWord)
	 */
	@SuppressWarnings("unchecked")
	
	public List<BasJudgmentWord> findByCondition(final int page, final int rows,
			final BasJudgmentWord basJudgmentWord) {

		List<BasJudgmentWord> list = (List)this.getHibernateTemplate().execute(new HibernateCallback(){

			
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				String queryString = "from BasJudgmentWord ";//
				if(basJudgmentWord.getWordName()!=null && !basJudgmentWord.getWordName().trim().equals("")){
					queryString += "where wordName like '%"+StringEscapeUtils.escapeSql(basJudgmentWord.getWordName().trim())+"%'";
				}
				org.hibernate.Query query = session.createQuery(queryString);
				int totlesize =query.list().size();
				HttpSession sesion = ServletActionContext.getRequest().getSession();
				sesion.setAttribute("totlesize", totlesize);
				int beginRow = (page-1)*rows;
				query.setFirstResult(beginRow);
				query.setMaxResults(rows);
				return query.list();
			}

		});
		return list;
	
	}

	
	public List<BasJudgmentWord> getTotle() {
		String hql ="from BasJudgmentWord";
		return this.getHibernateTemplate().find(hql);
	}
}