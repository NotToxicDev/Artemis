/*
 * Copyright © Wynntils 2021.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.wc.utils.parsers;

import com.wynntils.core.Reference;
import com.wynntils.mc.utils.ItemUtils;
import com.wynntils.utils.reference.EmeraldSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.world.item.ItemStack;

/** Tools for retrieving information about emerald pouches */
public class EmeraldPouchParser {

    private static final Pattern POUCH_USAGE_PATTERN =
            Pattern.compile("§6§l(\\d* ?\\d* ?\\d*)" + EmeraldSymbols.E_STRING);
    private static final Pattern POUCH_CAPACITY_PATTERN =
            Pattern.compile("\\((\\d+)(" + EmeraldSymbols.EB + "|" + EmeraldSymbols.LE + "|stx) Total\\)");

    public static int getPouchUsage(ItemStack stack) {
        String lore = ItemUtils.getStringLore(stack);
        Matcher usageMatcher = POUCH_USAGE_PATTERN.matcher(lore);
        if (!usageMatcher.find()) {
            if (lore.contains("§7Empty")) {
                return 0;
            }

            Reference.LOGGER.error(
                    "EmeraldPouchParser#getPouchUsage was called on an ItemStack that wasn't an emerald pouch");
            return -1;
        }
        return Integer.parseInt(usageMatcher.group(1).replaceAll("\\s", ""));
    }

    public static int getPouchCapacity(ItemStack stack) {
        String lore = ItemUtils.getStringLore(stack);
        Matcher capacityMatcher = POUCH_CAPACITY_PATTERN.matcher(lore);
        if (!capacityMatcher.find()) {
            Reference.LOGGER.error(
                    "EmeraldPouchParser#getPouchCapacity was called on an ItemStack that wasn't an emerald pouch");
            return -1;
        }
        int capacity = Integer.parseInt(capacityMatcher.group(1)) * 64;
        if (capacityMatcher.group(2).equals(EmeraldSymbols.LE)) capacity *= 64;
        if (capacityMatcher.group(2).equals("stx")) capacity *= 4096;
        return capacity;
    }
}
