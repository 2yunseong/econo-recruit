package com.econovation.recruitdomain.domains.record.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.record.domain.Record;
import com.econovation.recruitdomain.domains.record.domain.RecordRepository;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecordAdaptor implements RecordLoadPort, RecordRecordPort {
    private final RecordRepository recordRepository;

    @Override
    public Record save(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Optional<Record> findByApplicantId(String applicantId) {
        return recordRepository.findByApplicantId(applicantId);
    }
}
