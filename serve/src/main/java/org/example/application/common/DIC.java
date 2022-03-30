package org.example.application.common;

public class DIC {
    public final static int AUTH = 0;
    public final static int UN_AUTH = 1;

    public final static int CHECK_PASS = 0;
    public final static int CHECK_WAIT = 1;
    public final static int CHECK_FAIL = 2;

    public static final int USER_LOCK = 1;
    public static final int USER_UNLOCK = 0;

    public final static int IS_DEL = 1;
    public final static int NOT_DEL = 0;

    public final static int IS_ROOT = 0;
    public final static int NOT_ROOT = 1;

    public final static int PAGE_SIZE = 10;
    public final static String BASE_SUCCESS_MESSAGE = "操作成功";

    public final static int MENUS_TYPE_MENUS = 1;
    public final static int MENUS_TYPE_BUTTON = 2;
    public final static int MENUS_TYPE_HREF = 3;
}
