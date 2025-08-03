package com.referloop.referralservice.controller;


import com.referloop.referralservice.model.ReferralRequest;
import com.referloop.referralservice.repository.ReferralRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referrals")
@Slf4j
public class ReferralController {

    @Autowired
    private ReferralRequestRepository referralRepo;

    // POST /referrals/send
    @PostMapping("/send")
    public ReferralRequest sendReferral(@RequestBody ReferralRequest request) {
        log.debug("Received POST /referrals/send: " + request);
        request.setStatus("Pending");
        return referralRepo.save(request);
    }

    // GET /referrals/inbox?userId=abc123
    @GetMapping("/inbox")
    public List<ReferralRequest> getInbox(@RequestParam String userId) {
        log.debug("Fetching inbox for user: " + userId);
        List<ReferralRequest> requests = referralRepo.findByToUserId(userId);
        log.debug("Found: " + requests.size() + " requests");
        return requests;
    }

    @GetMapping("/all")
    public List<ReferralRequest> getAllReferrals() {
        return referralRepo.findAll();
    }

    // POST /referrals/accept/{id}
    @PostMapping("/accept/{id}")
    public ReferralRequest acceptReferral(@PathVariable String id) {
        ReferralRequest request = referralRepo.findById(id).orElseThrow();
        request.setStatus("Accepted");
        return referralRepo.save(request);
    }
}