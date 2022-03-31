package com.ttd.sdk.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author wt
 * @time 2021/10/29
 */
public enum Language {
    EN("English(US)", "EN_US", "en-US"),
    ZH("简体中文", "ZH_CN", "zh-CN"),
    RU("русский", "RU_RU", "ru-RU"),
    JP("日本語", "JA_JP", "ja-JP"),
    KR("한국인", "KO_KR", "ko-KR"),
    ES("Español", "ES_ES", "es-ES");

    public String name;
    public String abbreviation;
    public String abbreviationH5;

    Language(String name, String abbreviation, String abbreviationH5) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.abbreviationH5 = abbreviationH5;
    }

    public static List<Language> languageList() {
        return Arrays.asList(ZH, EN, RU, JP, KR, ES);
    }

//    public static List<Language> convert(List<BaseUserInfo.Language> languages) {
//        List<Language> languageList = new ArrayList<>();
//        for (BaseUserInfo.Language language : languages) {
//            for (Language lang : languageList()) {
//                if (language.code.equals(lang.abbreviation)) {
//                    languageList.add(lang);
//                    break;
//                }
//            }
//        }
//        return languageList;
//    }
//
//    public static Language convert(BaseUserInfo.Language language) {
//        for (Language lang : languageList()) {
//            if (language.code.equals(lang.abbreviation)) {
//                return lang;
//            }
//        }
//        return ZH;
//    }


    public static Language convert(String localeCode) {
        for (Language language : Language.values()) {
            if (localeCode.equalsIgnoreCase(language.abbreviation)) {
                return language;
            }
        }
        return null;
    }
}
