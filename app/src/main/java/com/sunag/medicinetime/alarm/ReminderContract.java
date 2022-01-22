package com.sunag.medicinetime.alarm;

import com.sunag.medicinetime.BasePresenter;
import com.sunag.medicinetime.BaseView;
import com.sunag.medicinetime.data.source.History;
import com.sunag.medicinetime.data.source.MedicineAlarm;



public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
