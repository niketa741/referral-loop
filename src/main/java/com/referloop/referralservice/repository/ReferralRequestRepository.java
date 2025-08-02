package com.referloop.referralservice.repository;

import com.referloop.referralservice.model.ReferralRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReferralRequestRepository extends MongoRepository<ReferralRequest, String> {
    List<ReferralRequest> findByToUserId(String toUserId);
}