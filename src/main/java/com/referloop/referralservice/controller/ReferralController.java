package com.referloop.referralservice.controller;


import com.referloop.referralservice.model.ReferralRequest;
import com.referloop.referralservice.repository.ReferralRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referrals")
public class ReferralController {

    @Autowired
    private ReferralRequestRepository referralRepo;

    // POST /referrals/send
    @PostMapping("/send")
    public ReferralRequest sendReferral(@RequestBody ReferralRequest request) {
        System.out.println("🎯 Received POST /referrals/send: " + request);
        request.setStatus("Pending");
        return referralRepo.save(request);
    }

    // GET /referrals/inbox?userId=abc123
    @GetMapping("/inbox")
    public List<ReferralRequest> getInbox(@RequestParam String userId) {
        System.out.println("📬 Fetching inbox for user: " + userId);
        List<ReferralRequest> requests = referralRepo.findByToUserId(userId);
        System.out.println("📨 Found: " + requests.size() + " requests");
        return requests;
    }

    // POST /referrals/{id}/accept
    @PostMapping("/accept/{id}")
    public ReferralRequest acceptReferral(@PathVariable String id) {
        ReferralRequest request = referralRepo.findById(id).orElseThrow();
        request.setStatus("Accepted");
        return referralRepo.save(request);
    }
}