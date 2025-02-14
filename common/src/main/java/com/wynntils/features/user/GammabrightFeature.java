/*
 * Copyright © Wynntils 2022.
 * This file is released under AGPLv3. See LICENSE for full license details.
 */
package com.wynntils.features.user;

import com.wynntils.core.features.UserFeature;
import com.wynntils.core.features.properties.FeatureInfo;
import com.wynntils.core.features.properties.FeatureInfo.Stability;
import com.wynntils.core.features.properties.RegisterKeyBind;
import com.wynntils.core.keybinds.KeyHolder;
import com.wynntils.mc.utils.McUtils;
import org.lwjgl.glfw.GLFW;

@FeatureInfo(stability = Stability.INVARIABLE)
public class GammabrightFeature extends UserFeature {

    private double lastGamma = 1f;

    @RegisterKeyBind
    private final KeyHolder gammabrightKeybind =
            new KeyHolder("Gammabright", GLFW.GLFW_KEY_G, "Wynntils", true, this::onGammabrightKeyPress);

    private void onGammabrightKeyPress() {
        double currentGamma = McUtils.mc().options.gamma;
        if (currentGamma < 1000) {
            lastGamma = currentGamma;
            McUtils.mc().options.gamma = 1000d;
            return;
        }

        McUtils.mc().options.gamma = lastGamma;
    }
}
