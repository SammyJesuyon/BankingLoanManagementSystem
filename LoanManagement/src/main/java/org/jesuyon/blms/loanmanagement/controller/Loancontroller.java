package org.jesuyon.blms.loanmanagement.controller;

import org.jesuyon.blms.loanmanagement.domain.response.BaseResponse;
import org.jesuyon.blms.loanmanagement.domain.response.ResponseBuilder;
import org.jesuyon.blms.loanmanagement.dto.ApproveLoanDto;
import org.jesuyon.blms.loanmanagement.dto.LoanApplicationCreationDto;
import org.jesuyon.blms.loanmanagement.dto.LoanApplicationDto;
import org.jesuyon.blms.loanmanagement.dto.LoanDto;
import org.jesuyon.blms.loanmanagement.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/blms/loans")
public class Loancontroller {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<BaseResponse<LoanApplicationDto>> applyForLoan(@RequestBody LoanApplicationCreationDto loanApplicationCreationDto) {
        try {
            LoanApplicationDto loanApplicationDto = loanService.applyForLoan(loanApplicationCreationDto);
            return ResponseBuilder.buildResponse("Loan application submitted successfully", loanApplicationDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<BaseResponse<LoanDto>> approveLoan(@RequestBody ApproveLoanDto approveLoanDto) {
        try {
            LoanDto loanDto = loanService.approveLoanApplication(approveLoanDto);
            return ResponseBuilder.buildResponse("Loan application approved successfully", loanDto, 200);
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(e.getMessage(), null, 400);
        }
    }
}
