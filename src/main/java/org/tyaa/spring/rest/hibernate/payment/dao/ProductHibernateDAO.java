package org.tyaa.spring.rest.hibernate.payment.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.tyaa.spring.rest.hibernate.payment.entity.Category;
import org.tyaa.spring.rest.hibernate.payment.entity.Product;
import org.tyaa.spring.rest.hibernate.payment.model.ProductFilter;

@Repository
public class ProductHibernateDAO
        extends AbstractHibernateGeneralizedDAO<Product> {

    public List<Product> getFiltered(ProductFilter filter) {

        CriteriaBuilder builder
                = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery
                = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        List<Integer> categoryList =
                Arrays.asList(filter.getCategories());
        Expression<String> categoryExpression = root.get("category");
        Predicate categoryPredicate = categoryExpression.in(categoryList);
        criteriaQuery.where(categoryPredicate);
        //q.orderBy(cb.asc(root.get(Employee_.Parent));

        Query<Product> query = getSession().createQuery(criteriaQuery);
        return query.list();
    }
}
