import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTABustMiddleModel {
    private List<CTABustPredictionModel> prd = new ArrayList<CTABustPredictionModel>();
    public List<CTABustPredictionModel> getPrd() {
        return prd;
    }
    public void setPrd(List<CTABustPredictionModel> prd) {
        this.prd = prd;
    }
}

