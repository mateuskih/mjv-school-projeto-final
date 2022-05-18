package com.mjv.projetofinal.util;

import java.math.BigDecimal;

public class Validators {
	public static BigDecimal add(BigDecimal... addends) {
	    BigDecimal sum = BigDecimal.ZERO;
	    if (addends != null) {
	        for (BigDecimal addend : addends) {
	            if (addend == null) {
	                addend = BigDecimal.ZERO;
	            }
	            sum = sum.add(addend);
	        }
	    }
	    return sum;
	}
}
