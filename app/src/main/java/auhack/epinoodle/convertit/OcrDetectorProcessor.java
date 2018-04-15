/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package auhack.epinoodle.convertit;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import auhack.epinoodle.convertit.camera.GraphicOverlay;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    private double curr1;
    private double curr2;

    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, double curr1, double curr2) {
        mGraphicOverlay = ocrGraphicOverlay;
        this.curr1 = curr1;
        this.curr2 = curr2;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                if (item.getValue().matches(".?[0-9]+([,.][0-9]{1,2})?.?")) {
                    Log.d("OcrDetectorProcessor", "filteredtest " + item.getValue());
                    OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
                    System.out.println("testing" + curr1);
                    graphic.setCurrencies(curr1,curr2);
                    mGraphicOverlay.add(graphic);
                }
            }
        }
    }

    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
