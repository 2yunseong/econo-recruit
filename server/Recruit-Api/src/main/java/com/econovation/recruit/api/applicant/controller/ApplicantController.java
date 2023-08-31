package com.econovation.recruit.api.applicant.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.APPLICANT_SUCCESS_REGISTER_MESSAGE;

import com.econovation.recruit.api.applicant.docs.CreateApplicantExceptionDocs;
import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.ApplicantRegisterUseCase;
import com.econovation.recruit.api.applicant.usecase.QuestionRegisterUseCase;
import com.econovation.recruit.api.applicant.usecase.TimeTableLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.TimeTableRegisterUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import com.econovation.recruitdomain.domains.applicant.dto.TimeTableVo;
import com.econovation.recruitdomain.domains.dto.QuestionRequestDto;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "[1.0]. 지원서 API", description = "지원서 관련 API")
public class ApplicantController {
    private final ApplicantRegisterUseCase applicantRegisterUseCase;
    private final TimeTableRegisterUseCase timeTableRegisterUseCase;
    private final TimeTableLoadUseCase timeTableLoadUseCase;
    private final QuestionRegisterUseCase questionRegisterUseCase;
    private final AnswerLoadUseCase answerLoadUseCase;

    @Operation(summary = "지원자가 지원서를 작성합니다.", description = "반환 값은 생성된 지원자의 ID입니다.")
    @ApiErrorExceptionsExample(CreateApplicantExceptionDocs.class)
    @PostMapping("/applicants")
    public ResponseEntity registerApplicant(@RequestBody List<BlockRequestDto> blockElements) {
        UUID applicantId = applicantRegisterUseCase.execute(blockElements);
        return new ResponseEntity<>(applicantId, HttpStatus.OK);
    }

    @Operation(summary = "지원자 id로 지원서를 조회합니다.")
    @GetMapping("/applicants/{applicant-id}")
    public ResponseEntity<Map<String, String>> getApplicantById(
            @PathVariable(value = "applicant-id") String applicantId) {
        return new ResponseEntity<>(answerLoadUseCase.execute(applicantId), HttpStatus.OK);
    }

    @Operation(summary = "모든 지원자의 지원서를 조회합니다.")
    @GetMapping("/applicants")
    public ResponseEntity<List<Map<String, String>>> getApplicants() {
        return new ResponseEntity<>(answerLoadUseCase.execute(), HttpStatus.OK);
    }

    @Operation(summary = "지원자가 면접 가능 시간을 작성합니다.")
    @ApiErrorExceptionsExample(CreateApplicantExceptionDocs.class)
    @PostMapping("/applicants/{applicant-id}/timetables")
    public ResponseEntity registerApplicantTimeTable(
            @PathVariable(value = "applicant-id") UUID applicantId,
            @RequestBody List<Integer> startTimes) {
        timeTableRegisterUseCase.execute(applicantId.toString(), startTimes);
        return new ResponseEntity<>(APPLICANT_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "면접관이 면접 질문을 추가합니다.")
    @PostMapping("/questions")
    public ResponseEntity registerInterviewQuestion(
            @RequestBody List<QuestionRequestDto> questions) {
        questionRegisterUseCase.execute(questions);
        return new ResponseEntity<>(APPLICANT_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "모든 면접 가능 시간을 조회합니다.")
    @GetMapping("/timetables")
    public ResponseEntity<List<Map<String, List<TimeTableVo>>>> getTimeTables() {
        return new ResponseEntity(timeTableLoadUseCase.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "지원자의 면접 가능 시간을 조회합니다.")
    @GetMapping("/applicants/{applicant-id}/timetables")
    public ResponseEntity<List<TimeTable>> getTimeTables(
            @PathVariable(name = "applicant-id") String applicantId) {
        List<Integer> timeTableDto = timeTableLoadUseCase.getTimeTableByApplicantId(applicantId);
        return new ResponseEntity(timeTableDto, HttpStatus.OK);
    }

    @Operation(summary = "면접 가능 시간마다 일치하는 지원자의 정보(희망분야, 이름)를 조회합니다.")
    @GetMapping("/timetables/applicants")
    public ResponseEntity<Map<Integer, List<String>>> getApplicantsByTimeTable() {
        return new ResponseEntity(
                timeTableLoadUseCase.findAllSimpleApplicantWithTimeTable(), HttpStatus.OK);
    }
}
