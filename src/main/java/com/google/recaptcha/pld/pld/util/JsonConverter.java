package com.google.recaptcha.pld.pld.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.google.recaptcha.pld.pld.model.Messages;
import com.google.recaptchaenterprise.v1.Assessment;
import org.apache.commons.text.StringEscapeUtils;

public class JsonConverter {
  public static Assessment fromJsonString(String json) throws IllegalArgumentException {
    Assessment.Builder assessment = Assessment.newBuilder();
    try {
      JsonFormat.parser().ignoringUnknownFields().merge(json, assessment);
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalArgumentException(Messages.JSON_TO_ASSESSMENT_ERROR);
    }
    return assessment.build();
  }

  public static String toJsonString(Assessment assessment) throws IllegalArgumentException {
    try {
      return StringEscapeUtils.escapeJson(JsonFormat.printer().print(assessment));
    } catch (InvalidProtocolBufferException e) {
      throw new IllegalArgumentException(Messages.ASSESSMENT_TO_JSON_ERROR);
    }
  }
}
