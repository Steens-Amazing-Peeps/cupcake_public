package cupcake.constants;

public interface FormParam
{ //Alle CTX Form Param (Når vi sender en form med HTML/Thymeleaf), bruges I controller og thymeleaf
    
    String cupcakeSend = "cupcakeSend";
    String newOrderlineTop = "newOrderlineTop";
    String newOrderlineBottom = "newOrderlineBottom";
    String newOrderlineAmount = "newOrderlineAmount";

    String deleteCupcake = "deleteCupcake";

    String email = "email";
    String password = "password";
    String verifyPassword = "verify-password";
    
    String orderOwnerId = "orderOwnerId";
    String orderId = "orderId";
    String filter = "filter";
    String filterAll = "all";
    String filterMe = "me";

    
    
    
    
    //Fra vores chatServer project, brug som inspiration :)
    
//    String c2Send =  "c2Send"; //Submit form-action
//    String c2NewMessageMessage =  "c2NewMessageMessage";
//    String c2NewMessageSender =  "c2NewMessageSender";
//    String c2NewMessageReceiver =  "c2NewMessageReceiver";
}
