package org.vsr.drishya.apiCall.bin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TMRReceivedDetails {

    @SerializedName("AllocationOrderNumber")
    @Expose
    private String allocationOrderNumber;

    @SerializedName("CommCode")
    @Expose
    private String commCode;

    @SerializedName("CommName")
    @Expose
    private String commName;

    @SerializedName("ExtraAllocQty")
    @Expose
    private String extraAllocQty;

    @SerializedName("Unit")
    @Expose
    private String unit;

    @SerializedName("OpeningBalance")
    @Expose
    private String openingBalance;

    @SerializedName("AllocQty")
    @Expose
    private String allocQty;

    @SerializedName("TotalTCqty")
    @Expose
    private String totalTCqty;

    @SerializedName("PercentageReceived")
    @Expose
    private String percentageReceived;

    @SerializedName("RatePerUnit")
    @Expose
    private String ratePerUnit;

    @SerializedName("TotalAmt")
    @Expose
    private String totalAmt;

    @SerializedName("Remarks")
    @Expose
    private String remarks;

    public String getAllocationOrderNumber() {
        return allocationOrderNumber;
    }

    public String getCommName() {
        return commName;
    }

    public String getExtraAllocQty() {
        return extraAllocQty;
    }

    public String getUnit() {
        return unit;
    }
      public String getOpeningBalance() {
        return openingBalance;
    }

    public String getAllocQty() {
        return allocQty;
    }
    public String getTotalTCqty() {
        return totalTCqty;
    }

    public String getPercentageReceived() {
        return percentageReceived;
    }

    public String getRemarks() {
        return remarks;
    }

}
