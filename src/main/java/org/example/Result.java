package org.example;

public class Result {
    private int status = -1;
    private int result = 65535;

    //status -1 : 결과 안나옴
    //status 0 : 성공
    //status 1 : Operand1이 잘못됨
    //status 2 : Operand2가 잘못됨
    //status 3 : OPCODE가 잘못되었음

    //result 65535 : 결과 없음

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public int getResult() {
        return result;
    }
}