//package websockets;
//
//import com.google.gson.Gson;
//import domain.Kweet;
//
//import javax.websocket.EncodeException;
//import javax.websocket.Encoder;
//import javax.websocket.EndpointConfig;
//
//public class KweetEncoder implements Encoder.Text<Kweet>{
//
//    private Gson gson = new Gson();
//
//    @Override
//    public String encode(Kweet kweet) throws EncodeException {
//        String json = gson.toJson(kweet);
//        return json;
//    }
//
//    @Override
//    public void init(EndpointConfig endpointConfig) {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
