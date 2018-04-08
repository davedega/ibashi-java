package com.dega.ibashi;

import com.dega.ibashi.model.Datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by davedega on 08/04/18.
 */

class TimeUtil {
    static String formatTime(Datetime datetime) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("HH:mm");
        isoFormat.setTimeZone(TimeZone.getTimeZone(datetime.getTz()));
        return isoFormat.format(new Date(datetime.getTimestamp()));
    }
}
