package com.roochi.petflowinventory.stock.domain;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author farzane.rahmani
 * @created 7/29/2026
 */
@Component
public class AverageCostCalculator {
    public BigDecimal calculate(BigDecimal currentQuantity, BigDecimal currentAverageCost,
                                BigDecimal purchaseQuantity, BigDecimal purchaseUnitCost) {
        if (currentQuantity.compareTo(BigDecimal.ZERO) == 0) {
            return purchaseUnitCost;
        }
        BigDecimal currentValue = currentQuantity.multiply(currentAverageCost);
        BigDecimal purchaseValue = purchaseQuantity.multiply(purchaseUnitCost);
        BigDecimal totalQuantity = currentQuantity.add(purchaseQuantity);
        BigDecimal totalValue = currentValue.add(purchaseValue);

        return totalValue.divide(totalQuantity, 4, RoundingMode.HALF_UP);
    }
}
