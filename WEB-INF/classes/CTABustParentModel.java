import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;


public class CTABustParentModel {
    @SerializedName("bustime-response")
    private CTABustMiddleModel bustimeResponse;
    public CTABustMiddleModel getBustimeResponse() {
        return bustimeResponse;
    }
    public void setBustimeResponse(CTABustMiddleModel bustimeResponse) {
        this.bustimeResponse = bustimeResponse;
    }
}