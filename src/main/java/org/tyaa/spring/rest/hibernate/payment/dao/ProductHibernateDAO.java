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

        //Создание корня критериа-запроса
        CriteriaBuilder builder
                = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery
                = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        //Добавление ограничения выборки по массиву ИД категорий,
        //если он присутствует в модели фильтрации
        if (filter.getCategories() != null
                && filter.getCategories().length > 0) {
            List<Integer> categoryList =
                Arrays.asList(filter.getCategories());
            Expression<String> categoryExpression = root.get("category");
            Predicate categoryPredicate = categoryExpression.in(categoryList);
            criteriaQuery.where(categoryPredicate);
        }
        //Добавление сортировки товаров по цене,
        //если она присутствует в модели фильтрации
        if (filter.sort != null) {
            if (filter.sort == ProductFilter.OrderBy.sortPriceAsc) {
                criteriaQuery.orderBy(builder.asc(root.get("price")));
            } else {
                criteriaQuery.orderBy(builder.desc(root.get("price")));
            }
        }
        //Создание запроса, пригодного для выполнения,
        //из критериа-запроса
        Query<Product> query = getSession().createQuery(criteriaQuery);
        //Выполнение запроса к БД
        return query.list();
    }
}
