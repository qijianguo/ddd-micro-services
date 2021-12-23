package com.qijianguo.micro.services.base.model.specification;

public class AndSpecification<T> extends AbstractSpecification<T>{

    private final Specification<T> one;

    private final Specification<T> other;

    public AndSpecification(Specification<T> a, Specification<T> b) {
        this.one = a;
        this.other = b;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return one.isSatisfiedBy(candidate) && other.isSatisfiedBy(candidate);
    }
}
