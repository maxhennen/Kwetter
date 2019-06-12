//package websockets;
//
//import com.google.gson.Gson;
//import domain.Kweet;
//
//import javax.websocket.DecodeException;
//import javax.websocket.Decoder;
//import javax.websocket.EndpointConfig;
//
//public class KweetDecoder implements Decoder.Text<Kweet> {
//
//    private static Gson gson = new Gson();
//
//    @Override
//    public Kweet decode(String s) throws DecodeException {
//        Kweet kweet = gson.fromJson(s, Kweet.class);
//        return kweet;
//    }
//
//    @Override
//    public boolean willDecode(String s) {
//        return (s != null);
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
