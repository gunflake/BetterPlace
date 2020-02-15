package com.dongisarang.user.kakao;

public class KakaoError {

    private Extras extras;
    private int code;
    private String msg;

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Extras {
        private String method_result_message;
        private String method_result_code;

        public String getMethod_result_message() {
            return method_result_message;
        }

        public void setMethod_result_message(String method_result_message) {
            this.method_result_message = method_result_message;
        }

        public String getMethod_result_code() {
            return method_result_code;
        }

        public void setMethod_result_code(String method_result_code) {
            this.method_result_code = method_result_code;
        }
    }
}
