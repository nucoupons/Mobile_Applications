package org.jclouds.aliyun.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Timestamps {

	private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	private static final SimpleDateFormat df = new SimpleDateFormat(
			ISO8601_DATE_FORMAT);

	public String getCurrent() {
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df.format(new Date());
	}

	public String get(Date date) {
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

}
