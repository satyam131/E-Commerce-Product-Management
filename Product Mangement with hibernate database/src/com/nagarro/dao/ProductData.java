package com.nagarro.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.nagarro.Database.DatabaseConnectionProvider;
import com.nagarro.model.Product;
import com.nagarro.model.UserDetail;

public class ProductData {

	public static List<Product> getProductList(String userNameOrEmail) {
		DatabaseConnectionProvider connection = new DatabaseConnectionProvider();
		connection.createConnection();
		Session session = connection.getSession();
		String hql = "from Product where userNameOrEmail=:userNameOrEmail";
		Query query = session.createQuery(hql);
		query.setParameter("userNameOrEmail", userNameOrEmail);
		List<Product> list = query.list();
		return list;
	}
}
