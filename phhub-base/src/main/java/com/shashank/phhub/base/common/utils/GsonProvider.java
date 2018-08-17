
package com.shashank.phhub.base.common.utils;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

	public static Gson gsonDisplayExposed = new GsonBuilder().setDateFormat(DateFormat.LONG).setPrettyPrinting()
			.excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping().create();

	public static Gson gsonDisplayAll = new GsonBuilder().setDateFormat(DateFormat.LONG).setPrettyPrinting()
			.disableHtmlEscaping().create();
}
