package com.qijianguo.micro.services.user.domain.user.service;

import com.qijianguo.micro.services.user.domain.user.entity.User;
import com.qijianguo.micro.services.user.domain.user.repository.po.UserPo;
import com.qijianguo.micro.services.user.infrastructure.util.TimeUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    CriteriaBuilder criteriaBuilder;

    public Specification<UserPo> newcomer() {
        Specification<UserPo> specification = (Specification<UserPo>) (root, query, criteriaBuilder1) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder1.greaterThan(root.get("createTime"), TimeUtils.localDate2Date(LocalDate.now())));
            predicates.add(criteriaBuilder1.equal(root.get("state"), User.Status.NORMAL.name()));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        return specification;
    }

}
