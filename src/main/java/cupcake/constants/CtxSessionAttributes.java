package cupcake.constants;

public interface CtxSessionAttributes
{ //Alle CTX Session Attributes, bruges I controller og thymeleaf

    String currentUser = "currentUser";
    String currentOrder = "currentOrder";
    
    String currentOrderHistory = "currentOrderHistory";

    
    
    
    
    
    //Fra vores chatServer project, brug som inspiration :)
    
//    String currentUser = "currentUser"; //Made by signe
//    String c2AllMessages = "c2AllMessages"; //LinkedHashMap< Integer, MessageForHtml >
//    String c2LastKnownActivityByUsername = "c2LastKnownActivityByUsername"; //Username, TimeInMS (since 1970)
//    String c2Online = "c2Online";
//    String c2Offline = "c2Offline";
//    String c2Frontpage = "c2Frontpage"; //CurrentlyOnline
    
    
}
