package me.mical.signature.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArrayUtil {

    public static String toString(@NotNull final String[] args, @Nullable final String c) {
        return String.join(c == null ? " " : c, args);
    }
}
