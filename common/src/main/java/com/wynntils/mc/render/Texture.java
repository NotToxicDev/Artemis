/*
 * Copyright © Wynntils 2022.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.mc.render;

import net.minecraft.resources.ResourceLocation;

public enum Texture {
    HIGHLIGHT("highlight.png", 256, 256),
    ARC("arc.png", 1024, 64);

    private final ResourceLocation resource;
    private final int width;
    private final int height;

    Texture(String name, int width, int height) {
        this.resource = new ResourceLocation("wynntils", "textures/" + name);
        this.width = width;
        this.height = height;
    }

    public ResourceLocation resource() {
        return resource;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
