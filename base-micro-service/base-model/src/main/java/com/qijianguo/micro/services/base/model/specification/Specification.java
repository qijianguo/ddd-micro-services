package com.qijianguo.micro.services.base.model.specification;

/**
 * @author qijianguo
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T candidate);

    Specification<T> and(Specification other);

    Specification<T> or(Specification other);

    Specification<T> not();

}
