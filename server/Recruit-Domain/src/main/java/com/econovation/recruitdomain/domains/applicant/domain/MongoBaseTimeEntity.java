package com.econovation.recruitdomain.domains.applicant.domain;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Field;

public abstract class MongoBaseTimeEntity {
    @Field("created_date")
    private String createdDate;

    @Field("modified_date")
    private String modifiedDate;

    public MongoBaseTimeEntity() {
        this.createdDate = LocalDateTime.now().toString();
        this.modifiedDate = LocalDateTime.now().toString();
    }
}
