package com.experientialetc.Hr.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPrefs {
    public static final String KEY_Event_ID = "id";
    public static final String KEY_Event_DATE = "date";
    public static final String KEY_QUESTION_ID = "quesId";
    public static final String KEY_File_Name = "fileName";
    public static final String KEY_USER_Token = "token";
    private static final String PrefsName = "Presenter";
    private static final String PrefsFinal = "PresenterApp";

    public static void clearPrefsData(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsName,
                Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public static void clearFinalPrefsData(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public static void setFinalStringValue(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringFinalValue(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void setBooleanFinalKeyValue(Context ctx, String key, boolean value) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public static boolean getBooleanFinalKeyValue(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void setStringKeyValuePrefs(Context ctx, String key,
                                              String value) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsName,
                Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringKeyValuePrefs(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsName,
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void setIntFinalValuePrefs(Context ctx, String key, int value) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();

    }

    public static int getIntFinalValuePrefs(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsFinal,
                Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static void setBooleanKeyvaluePrefs(Context ctx, String key,
                                               boolean value) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsName,
                Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public static boolean getBooleanKeyvaluePrefs(Context ctx, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences(PrefsName,
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }


}
