package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

class AluResultCode {
    public static int FAIL = -1;
    public static int SUCCESS = 0;
    public static int NO_RESULT = 65535;
}

class AluStatusCode {
    public static int INVALID_OPERAND_1 = 1;
    public static int INVALID_OPERAND_2 = 2;
    public static int NOT_SELECTED_OP_CODE = 3;
}

public class Alu {
    private int operand1 = -1;
    private int operand2 = -1;
    private String OPCODE = "";
    private final Map<String, BiFunction<Integer, Integer, Integer>> OPMAP = new HashMap<>();
    private boolean isExistOp = false;

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
        this.OPCODE = OPCODE;
        this.isExistOp = OPMAP.containsKey(OPCODE);
    }

    public void enableSignal(Result r) {
        if (!isValidOperands(r)){
            return;
        }

        int result = getCalculator(OPCODE).apply(operand1, operand2);
        int resultCode = AluResultCode.SUCCESS;

        setAluResult(r, result);
        setAluStatus(r, resultCode);
    }

    private boolean isValidOperands(Result r) {
        if (!this.isExistOp) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluStatusCode.NOT_SELECTED_OP_CODE);
            return false;
        }
        if (isMinus(this.operand1)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluStatusCode.INVALID_OPERAND_1);
            return false;
        }
        if (isMinus(this.operand2)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluStatusCode.INVALID_OPERAND_2);
            return false;
        }
        return true;
    }

    private BiFunction<Integer, Integer, Integer> getCalculator(String OPCODE) {
        return this.OPMAP.get(OPCODE.toUpperCase());
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