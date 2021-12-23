package com.qijianguo.micro.services.base.model.specification;

/**
 * @author qijianguo
 */
public class NotSpecification<T> extends AbstractSpecification<T>{

    private Specification<T> wrapped;

    public NotSpecification(Specification a) {
        this.wrapped = a;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !wrapped.isSatisfiedBy(candidate);
    }
}
