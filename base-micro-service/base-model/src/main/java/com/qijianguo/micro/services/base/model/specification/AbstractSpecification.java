package com.qijianguo.micro.services.base.model.specification;

/**
 * @author qijianguo
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public Specification and(Specification other) {
        return new AndSpecification<T>(this, other);
    }

    @Override
    public Specification<T> or(Specification other) {
        return new OrSpecification<>(this, other);
    }

    @Override
    public Specification<T> not() {
        return new NotSpecification<>(this);
    }
}
