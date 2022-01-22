package com.sunag.medicinetime;

import android.content.Context;
import androidx.annotation.NonNull;


import com.sunag.medicinetime.data.source.MedicineRepository;
import com.sunag.medicinetime.data.source.local.MedicinesLocalDataSource;


/**
 * Created by gautam on 13/05/17.
 */

public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}