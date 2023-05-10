package com.piramal.lms.simple.batch;

import com.piramal.lms.simple.model.ActiveLoan;
import com.piramal.lms.simple.model.Loan;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class FileItemProcessor implements ItemProcessor<Loan, ActiveLoan> {
    @Override
    public ActiveLoan process(Loan loan) throws Exception {
        ActiveLoan activeLoan = new ActiveLoan();
        activeLoan.setLoanNumber(loan.getLoanNumber());
        activeLoan.setProductId(loan.getProductId());
        activeLoan.setDueAmount(loan.getDueAmount());
        activeLoan.setSanctionAmount(loan.getSanctionAmount());
        return activeLoan;
    }
}
