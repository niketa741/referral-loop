package com.referloop.referralservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "referral_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferralRequest {
    @Id
    private String id;
    private String fromUserId;
    private String toUserId;
    private String jobRole;
    private String status; // Pending, Accepted
}