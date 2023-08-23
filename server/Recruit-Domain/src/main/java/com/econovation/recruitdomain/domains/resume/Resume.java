package com.econovation.recruitdomain.domains.resume;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resume_id")
    private Integer id;

    @Column(name = "applicant_id")
    private UUID applicantId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "answer")
    private String answer;
}
