package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Alu {
    private int operand1 = -1;
    private int operand2 = -1;
    private final Map<String, BiFunction<Integer, Integer, Integer>> OPMAP = new HashMap<>();
    private BiFunction<Integer, Integer, Integer> calculator = null;
    private boolean isSelectedCalculator = false;

    public Alu() {
        OPMAP.put("ADD", Integer::sum);
        OPMAP.put("MUL", (a, b) -> a * b);
        OPMAP.put("SUB", (a, b) -> a - b);
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setOPCODE(String OPCODE) {
        this.isSelectedCalculator = OPMAP.containsKey(OPCODE);
        this.calculator = OPMAP.get(OPCODE.toUpperCase());
    }

    public void enableSignal(Result r) {
        if (!isValidOperands(r)){
            return;
        }

        int result = this.calculator.apply(operand1, operand2);
        int resultCode = AluResultCode.SUCCESS;

        setAluResult(r, result);
        setAluStatus(r, resultCode);
    }

    private boolean isValidOperands(Result r) {
        if (!this.isSelectedCalculator) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.NOT_SELECTED_OPERAND_CODE);
            return false;
        }
        if (isMinus(this.operand1)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.INVALID_OPERAND_1);
            return false;
        }
        if (isMinus(this.operand2)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.INVALID_OPERAND_2);
            return false;
        }
        return true;
    }

    private boolean isMinus(int operand) {
        return operand == -1;
    }

    private void setAluResult(Result r, int result) {
        r.setResult(result);
    }
    private void setAluStatus(Result r, int statusCode) {
        r.setStatus(statusCode);
    }
}