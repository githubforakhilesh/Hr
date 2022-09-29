package com.experientialetc.Hr.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenApiResponse {
  @SerializedName("status")
  @Expose
  public String status;
  @SerializedName("key")
  @Expose
  public String key;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
