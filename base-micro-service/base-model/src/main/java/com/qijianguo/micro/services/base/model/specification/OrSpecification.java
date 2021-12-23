package com.qijianguo.micro.services.base.model.specification;

/**
 * @author qijianguo
 */
public class OrSpecification<T> extends AbstractSpecification<T>{

    private final Specification<T> one;

    private final Specification<T> other;

    public OrSpecification(Specification<T> a, Specification<T> b) {
        this.one = a;
        this.other = b;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return one.isSatisfiedBy(candidate) || other.isSatisfiedBy(candidate);
    }

}
