package xin.ewenlai.news.utils;

/**
 * description : 枚举请求返回 JSON 数据中的代码。
 *
 * @author lwwen
 * date : 2018-07-12 08:46
 * @version 0.0.0
 */
public enum Code {
    SUCCESS(1),
    FAIL(2);

    private int value;

    Code(int value) {
        this.value = value;
    }

    public static Code valueOf(int value) {
        switch (value) {
            case 1:
                return SUCCESS;
            case 2:
                return FAIL;
            default:
                return null;
        }
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println("SUCCESS = " + SUCCESS);
        System.out.println("FAIL = " + FAIL);
        System.out.println("Code.FAIL.getValue() = " + FAIL.getValue());
        System.out.println("SUCCESS.getValue() = " + SUCCESS.getValue());
    }
}
