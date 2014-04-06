package com.textTI.storeManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.utils.HibernateUtil;

@Repository
public class IndicatorDAO extends BaseDAO {

	public List<Indicator> getAll() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Indicator> indicators = session.createQuery("from Indicator").list();

		session.close();

		return indicators;
	}

	public List<Indicator> getAllByUser(List<Long> storesId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i FROM Indicator i WHERE i.employee.store.id IN (:storesId)";
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}

	public List<Indicator> getAllByUserAndMonth(List<Long> storesId, String selectedMonth, String selectedYear) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i "
				+ "FROM Indicator i "
				+ "WHERE i.employee.store.id IN (:storesId) "
				+ "AND i.month = :month "
				+ "AND i.year = :year "
				+ "ORDER BY i.employee.store.name";
		
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		query.setParameter("month", Integer.parseInt(selectedMonth));
		query.setParameter("year", Integer.parseInt(selectedYear));
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}
	
	public Indicator generateReportResultOfMonthTotals(List<Long> storesId,
			String month, String year) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		String hqlTotal = "SELECT new com.textTI.storeManagement.model.Indicator(i.id, sum(i.workedDays) as workedDays,"
				+ " sum(i.goal) as goal,"
				+ " sum(i.valueOfSales) as valueOfSales,"
				+ " sum(i.numberOfAttendances) as numberOfAttendances,"
				+ " sum(i.numberOfSales) as numberOfSales,"
				+ " sum(i.numberOfItemsSold) as numberOfItemsSold,"
				+ " avg(i.achievementOfGoals) as achievementOfGoals,"
				+ " avg(i.averageValueOfTheProduct) as averageValueOfTheProduct,"
				+ " avg(i.averageTicket) as averageTicket,"
				+ " avg(i.itemsPerSale) as itemsPerSale,"
				+ " avg(i.conversionRate) as conversionRate,"
				+ " avg(i.averageSalesPerDay) as averageSalesPerDay,"
				+ "	i.employee) "
				+ " FROM Indicator i "
				+ " WHERE i.employee.store.id IN (:storesId) "
				+ " AND i.month = :month "
				+ " AND i.year = :year ";
		
		Query query = session.createQuery(hqlTotal);

		query.setParameterList("storesId", storesId);
		query.setParameter("month", Integer.parseInt(month));
		query.setParameter("year", Integer.parseInt(year));
		
		Indicator indicator = (Indicator) query.uniqueResult();
		
		session.close();
		
		return indicator;
	}

	public List<Indicator> getAllByUserAndMonthYearInterval(
			List<Long> storesId, String monthFrom, String yearFrom,
			String monthTo, String yearTo) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i "
				+ "FROM Indicator i "
				+ "WHERE i.employee.store.id IN (:storesId) "
				+ "AND STR_TO_DATE(CONCAT(i.year,CONCAT('-',i.month)), '%Y-%m') BETWEEN STR_TO_DATE(:dateFrom,'%Y-%m') and STR_TO_DATE(:dateTo,'%Y-%m') "
				+ "ORDER BY i.employee.store.name, i.employee.name, i.year, i.month";
		
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		query.setParameter("dateFrom", yearFrom + '-' + monthFrom);
		query.setParameter("dateTo", yearTo + '-' + monthTo);
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}

	@SuppressWarnings("unchecked")
	public List<Indicator> generateReportCumulativeResult(List<Long> storesId,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT new com.textTI.storeManagement.model.Indicator(i.id, sum(i.workedDays) as workedDays,"
				+ " sum(i.goal) as goal,"
				+ " sum(i.valueOfSales) as valueOfSales,"
				+ " sum(i.numberOfAttendances) as numberOfAttendances,"
				+ " sum(i.numberOfSales) as numberOfSales,"
				+ " sum(i.numberOfItemsSold) as numberOfItemsSold,"
				+ " avg(i.achievementOfGoals) as achievementOfGoals,"
				+ " avg(i.averageValueOfTheProduct) as averageValueOfTheProduct,"
				+ " avg(i.averageTicket) as averageTicket,"
				+ " avg(i.itemsPerSale) as itemsPerSale,"
				+ " avg(i.conversionRate) as conversionRate,"
				+ " avg(i.averageSalesPerDay) as averageSalesPerDay,"
				+ "	i.employee) "
				+ " FROM Indicator i "
				+ " WHERE i.employee.store.id IN (:storesId) "
				+ " AND STR_TO_DATE(CONCAT(i.year,CONCAT('-',i.month)), '%Y-%m') BETWEEN STR_TO_DATE(:dateFrom,'%Y-%m') and STR_TO_DATE(:dateTo,'%Y-%m') "
				+ " GROUP BY i.employee.id"
				+ " ORDER BY i.employee.store.name, i.employee.name, i.year, i.month";
		
		Query query = session.createQuery(hql);

		query.setParameterList("storesId", storesId);
		query.setParameter("dateFrom", yearFrom + '-' + monthFrom);
		query.setParameter("dateTo", yearTo + '-' + monthTo);
		
		List<Indicator> indicators = query.list();
		
		session.close();
		
		return indicators;
	}

	public Indicator generateReportCumulativeResultTotals(List<Long> storesId,
			String monthFrom, String yearFrom, String monthTo, String yearTo) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hqlTotal = "SELECT new com.textTI.storeManagement.model.Indicator(i.id, sum(i.workedDays) as workedDays,"
				+ " sum(i.goal) as goal,"
				+ " sum(i.valueOfSales) as valueOfSales,"
				+ " sum(i.numberOfAttendances) as numberOfAttendances,"
				+ " sum(i.numberOfSales) as numberOfSales,"
				+ " sum(i.numberOfItemsSold) as numberOfItemsSold,"
				+ " avg(i.achievementOfGoals) as achievementOfGoals,"
				+ " avg(i.averageValueOfTheProduct) as averageValueOfTheProduct,"
				+ " avg(i.averageTicket) as averageTicket,"
				+ " avg(i.itemsPerSale) as itemsPerSale,"
				+ " avg(i.conversionRate) as conversionRate,"
				+ " avg(i.averageSalesPerDay) as averageSalesPerDay,"
				+ "	i.employee) "
				+ " FROM Indicator i "
				+ " WHERE i.employee.store.id IN (:storesId) "
				+ " AND STR_TO_DATE(CONCAT(i.year,CONCAT('-',i.month)), '%Y-%m') BETWEEN STR_TO_DATE(:dateFrom,'%Y-%m') and STR_TO_DATE(:dateTo,'%Y-%m') ";
		
		Query queryTotals = session.createQuery(hqlTotal);
		
		queryTotals.setParameterList("storesId", storesId);
		queryTotals.setParameter("dateFrom", yearFrom + '-' + monthFrom);
		queryTotals.setParameter("dateTo", yearTo + '-' + monthTo);
		
		Indicator indicator = (Indicator) queryTotals.uniqueResult();
		
		session.close();
		
		return indicator;
	}

	public List<String> getDistinctYears() {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String sql = "Select DISTINCT i.year from Indicator i";
		
		Query query = session.createQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<String> distinctYears = query.list();
		
		return distinctYears;
	}

	public List<Indicator> getIndicatorsByStoreMonthAndYear(long storeId,
			int month, int year) {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i "
				+ " FROM Indicator i"
				+ " WHERE i.employee.store.id = :storeId "
				+ " AND i.month = :month "
				+ " AND i.year = :year";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("storeId", storeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		@SuppressWarnings("unchecked")
		List<Indicator> indicators = query.list();
		
		return indicators;
	}

	public Indicator getIndicatorsByStoreMonthAndYearTotals(int storeId,
			int month, int year) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hqlTotal = "SELECT new com.textTI.storeManagement.model.Indicator(i.id, sum(i.workedDays) as workedDays,"
				+ " sum(i.goal) as goal,"
				+ " sum(i.valueOfSales) as valueOfSales,"
				+ " sum(i.numberOfAttendances) as numberOfAttendances,"
				+ " sum(i.numberOfSales) as numberOfSales,"
				+ " sum(i.numberOfItemsSold) as numberOfItemsSold,"
				+ " avg(i.achievementOfGoals) as achievementOfGoals,"
				+ " avg(i.averageValueOfTheProduct) as averageValueOfTheProduct,"
				+ " avg(i.averageTicket) as averageTicket,"
				+ " avg(i.itemsPerSale) as itemsPerSale,"
				+ " avg(i.conversionRate) as conversionRate,"
				+ " avg(i.averageSalesPerDay) as averageSalesPerDay,"
				+ "	i.employee) "
				+ " FROM Indicator i "
				+ " WHERE i.employee.store.id = :storeId "
				+ " AND i.month = :month "
				+ " AND i.year = :year";
		
		Query queryTotals = session.createQuery(hqlTotal);
		
		queryTotals.setParameter("storeId", storeId);
		queryTotals.setParameter("month", month);
		queryTotals.setParameter("year", year);
		
		Indicator indicator =  (Indicator) queryTotals.uniqueResult();
		
		return indicator;
	}

	public Indicator getByMonthAndYear(Long employeeId, int month, int year) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "SELECT DISTINCT i "
				+ " FROM Indicator i"
				+ " WHERE i.employee.id = :employeeId "
				+ " AND i.month = :month "
				+ " AND i.year = :year";
		
		Query query = session.createQuery(hql);
		
		query.setParameter("employeeId", employeeId);
		query.setParameter("month", month);
		query.setParameter("year", year);
		
		Indicator indicator = (Indicator) query.uniqueResult();
		
		return indicator;
	}
}
