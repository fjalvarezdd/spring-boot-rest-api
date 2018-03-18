package com.fjalvarez.review;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') OR @reviewRepository.findOne(#id)?.reviewer?.username == authentication.name")
    void delete(@Param("id") Long aLong);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') OR #review.revierer?.username == authentication.name")
    void delete(@Param("review") Review entity);
}
