package wusc.edu.pay.api.merchant.utils;

import java.io.IOException;
import java.io.InputStream;

public interface HttpResponseCallBack {

    void processResponse(InputStream responseBody) throws IOException;

}
