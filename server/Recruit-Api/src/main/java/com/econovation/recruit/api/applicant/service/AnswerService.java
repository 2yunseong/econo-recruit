package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruitdomain.domains.applicant.adaptor.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswerLoadUseCase {
    private final AnswerAdaptor answerAdaptor;

    @Override
    public Map<UUID, Map<String, String>> findAllApplicantVo(List<String> fields) {
        List<Answer> answers = answerAdaptor.findAll();
        return splitByAnswersInApplicantId(fields, answers);
    }

    private Map<UUID, Map<String, String>> splitByAnswersInApplicantId(
            List<String> fields, List<Answer> answers) {
        return answers.stream()
                .filter(answer -> fields.contains(answer.getQuestion().getKey()))
                .collect(
                        Collectors.groupingBy(
                                Answer::getApplicantId,
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getKey(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        HashMap::new)));
    }

    @Override
    public Map<UUID, Map<String, String>> findApplicantVoByApplicantId(
            List<String> fields, List<UUID> applicantIds) {
        List<Answer> answers = answerAdaptor.findByAnswerIds(applicantIds);
        return splitByAnswersInApplicantId(fields, answers);
    }

    @Override
    public Map<String, String> findApplicantVoByApplicantId(List<String> fields, UUID applicantId) {
        List<Answer> answers = answerAdaptor.findByAnswerId(applicantId);
        return splitByAnswersInApplicantId(fields, answers).get(applicantId);
    }
}
