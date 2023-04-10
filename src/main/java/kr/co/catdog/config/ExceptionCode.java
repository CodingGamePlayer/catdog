package kr.co.catdog.config;

public enum ExceptionCode {
    PAY_CANCEL("PAY-001", "결제가 취소되었습니다."), PAY_FAILED("PAY-002", "결제가 실패했습니다.");;

    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
