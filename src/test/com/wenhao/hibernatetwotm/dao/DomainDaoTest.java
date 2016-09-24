package com.wenhao.hibernatetwotm.dao;

import com.wenhao.hibernatetwotm.domain.Department;
import com.wenhao.hibernatetwotm.domain.Employee;
import com.wenhao.hibernatetwotm.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lenovo on 2016/09/24.
 */
public class DomainDaoTest {
    @Test
    public void delete() throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        /*Employee employee = new Employee();
        employee.setId(6L);
        session.delete(employee);*/
       /* Department department = new Department();
        department.setId(7L);
        session.delete(department);*/
        String hql = "delete from Employee e where e.dep.id=?";
        Query query = session.createQuery(hql);
        query.setParameter(0, 7L);
        System.out.println("delete:" + query.executeUpdate());
        transaction.commit();
        session.close();
    }

    @Test
    public void save() throws Exception {
        Session session = HibernateUtils.getSession();
        Department department = new Department();
        Employee employee = new Employee();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        department.setName("采购部");
        employee.setName("wenhao");
        Set<Employee> emp = new HashSet<Employee>();
        emp.add(employee);
        department.setEmployees(emp);
        employee.setDep(department);
        session.save(department);
        session.save(employee);
        transaction.commit();
        session.close();
    }

}