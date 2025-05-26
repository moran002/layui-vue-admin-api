package com.moran.conf.constant;

import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author moran
 * 项目通用常量池
 **/
public final class CommonConstant {


    private CommonConstant() {
        throw new RuntimeException("can not init constant class");
    }

    /**
     * 请求头
     */
    public static final String HEADER = "Authorization";
    public static final String START_WITH = "Bearer ";
    public static final Long EXPIRE = 5 * 60L;

    /**
     * 公钥与私钥
     */
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOmzvhJuWHj4QbU1aaXrnkGUwEk/2JaAqqLXxdwCs6LmT1NgrKFTPgxxu1qGWqCFoI0IUP/JZAnNp2HhTfORDRRZ70yeiK6rzJcm4iJTIyyltThjketYkUFnh6iTxSEzTANH2+0qIavTZz79mgPR4qWs560P29qzOLmsuf4vqmTVAgMBAAECgYBPxbF7NBxSCpfPRZCu+Lr38tEiD7+cPAZC+LlKrMdpswjf0o+Kr9HtyIKl11enFIXlrjGL5lAWApOAlzoXCmu2YA4unL/QsbR/eOXg4E+V5Lm5aHcSZTNGcDEjMPDFfULJOHZFBFlUEe0eDh4EJbxL9XtEtryZDBU838rERLo8PwJBAOq6tLO5dCCUS8o39gAbmF50r3UhSZKiZg3KOFz7MNkdH/yA39viOWJ4lvx6HoD+63mx3oILq2qyvyBpGGuc5/cCQQD+4TUYsBjOx+1e7TN92ia98EAf8gVkh00+Z+7Kj5399Kg4LR4QQmf5F5/tn7IBagKys4Heu748NmN/VlmHn96TAkAvwej+IB9meYWqERS2FOc9YJRKSomDkhMdfyVUla7snaZiY34oaLCwPkTJ/bazHCqgyfWxk9o+jTuhxoA3dOclAkEAnPnBBNp3/Oq/NAas/ubbk4GjTMl3Lsnx9ex28kRvjtgevrJy4Jq1hIFbWzNOo3ZSARNzeeVxLY5lyyg4bcI1hwJBAL4byA8EjnxfBD2b9df7gpdsZ+Yp9AjAs9eHm3mwGKrEvi62dc7TXdTePnA5JPh+5Nwy9dlJbASBuA0qIzfBav8=";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDps74Sblh4+EG1NWml655BlMBJP9iWgKqi18XcArOi5k9TYKyhUz4McbtahlqghaCNCFD/yWQJzadh4U3zkQ0UWe9Mnoiuq8yXJuIiUyMspbU4Y5HrWJFBZ4eok8UhM0wDR9vtKiGr02c+/ZoD0eKlrOetD9vaszi5rLn+L6pk1QIDAQAB";
    public static final RSA RSA = new RSA(PRIVATE_KEY, PUBLIC_KEY);

    /**
     * 成功字符串
     */
    public static final String SUCCESS = "success";
    /**
     * 用户信息key
     */
    public static final String USER_INFO = "user-info";
    public static final String MENUS = "menus";

    /**
     * redis key
     */
    public static final String PWD_FAIL_COUNT = "user-pwd-fail-count:%s";
}
