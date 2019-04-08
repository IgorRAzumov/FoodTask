package com.example.data.local.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.math.BigDecimal;

public final class BigDecimalConverter {
    //наверно не лучший вариант в строке хранить
    @TypeConverter
    public static BigDecimal moneyFromSting(String value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public static String stringFromMoney(BigDecimal value) {
        return value == null ? null : value.toString();
    }

}
