package com.econovation.recruit.api.record.usecase;

import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.dto.UpdateRecordDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;

public interface RecordUseCase {
    Record createRecord(CreateRecordDto record);

    List<Record> findAll();

    Record findByApplicantId(String applicantId);

    void updateRecordUrl(String applicantId, String url);

    void updateRecordContents(String applicantId, String contents);

    void updateRecord(String applicantId, UpdateRecordDto updateRecordDto);
}
