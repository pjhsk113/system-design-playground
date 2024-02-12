package study.playground.springboot.core.api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Repayments {
    private List<Repayment> repayments;

    private Repayments(List<Repayment> repayments) {
        this.repayments = repayments;
    }

    public static Repayments from(List<Repayment> repayments) {
        return new Repayments(repayments);
    }

    public static Repayments of(Long requestUserId, Money totalSettlementAmount, List<Long> targetUserIds) {
        Money dutchAmount = distribute(totalSettlementAmount, targetUserIds.size());
        return new Repayments(mapToRepayment(requestUserId, dutchAmount, targetUserIds));
    }

    private static Money distribute(Money totalSettlementAmount, int distributorCount) {
        return totalSettlementAmount.divide(distributorCount);
    }

    private static List<Repayment> mapToRepayment(Long requestUserId, Money settlementAmount, List<Long> targetUserIds) {
        List<Repayment> repayments = new ArrayList<>();

        for (Long targetUserId : targetUserIds) {
            repayments.add(
                    new Repayment(
                            new User.UserId(requestUserId),
                            new User.UserId(targetUserId),
                            settlementAmount,
                            requestUserId.equals(targetUserId) ? settlementAmount : Money.ZERO
                    ));
        }

        return repayments;
    }

    public Repayment getSourceUserRepayment(Long userId) {
        return repayments.stream()
                .filter(repayment -> repayment.isSettlementTargetUser(new User.UserId(userId)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("정산 대상자가 아닙니다."));
    }

    public Repayment getOwnerUserRepayment() {
        return repayments.stream()
                .filter(Repayment::isSettlementOwner)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("정산 대상자가 아닙니다."));
    }

    public boolean hasRemainSettlement() {
        return repayments.stream()
                .anyMatch(repayment -> repayment.getSettlementStatus() != SettlementStatus.COMPLETE);
    }

    public Repayments updateRepayments(Repayment updateRepayment) {
        List<Repayment> updateRepayments =
                repayments.stream()
                        .map(repayment -> repayment.isSettlementTargetUser(updateRepayment.getTargetUserId()) ? updateRepayment : repayment)
                        .collect(Collectors.toList());

        return Repayments.from(updateRepayments);
    }

    public int size() {
        return repayments.size();
    }

    public List<Repayment> getRepayments() {
        return Collections.unmodifiableList(repayments);
    }
}
