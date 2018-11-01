package org.tyaa.spring.rest.hibernate.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProductFilter {
    
    public enum OrderBy {
        sortPriceDesc
        , sortPriceAsc
    }

    private Integer[] categories;
    public OrderBy sort;

    public ProductFilter() {
        super();
    }

    public ProductFilter(Integer[] categories, OrderBy sort) {
        this.categories = categories;
        this.sort = sort;
    }

    public Integer[] getCategories() {
        return categories;
    }

    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    public OrderBy getSort() {
        return sort;
    }

    public void setSort(OrderBy sort) {
        this.sort = sort;
    }
}
